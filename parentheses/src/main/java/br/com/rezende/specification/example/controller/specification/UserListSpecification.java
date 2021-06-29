package br.com.rezende.specification.example.controller.specification;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.rezende.specification.example.controller.dto.UserListRequest;
import br.com.rezende.specification.example.domain.Address;
import br.com.rezende.specification.example.domain.User;

@Component
public class UserListSpecification extends BaseSpecification<User, UserListRequest> {

	@Override
	public Specification<User> getFilter(UserListRequest request) {
		return (root, query, cb) -> {
			query.distinct(true); // Important because of the join in the addressAttribute specifications
			return where(where(firstNameContains(request.search)).or(lastNameContains(request.search))
					.or(emailContains(request.search))).and(streetContains(request.street))
							.and(cityContains(request.city)).and(isPremium(request.email)).toPredicate(root, query, cb);
		};

	}

	private Specification<User> firstNameContains(String firstName) {
		return userAttributeContains("firstName", firstName);
	}

	private Specification<User> lastNameContains(String lastName) {
		return userAttributeContains("lastName", lastName);
	}

	private Specification<User> emailContains(String email) {
		return userAttributeContains("email", email);
	}

	private Specification<User> userAttributeContains(String attribute, String value) {
		return (root, query, cb) -> {
			if (value == null) {
				return null;
			}

			return cb.like(cb.lower(root.get(attribute)), containsLowerCase(value));
		};
	}

	private Specification<User> cityContains(String city) {
		return addressAttributeContains("city", city);
	}

	private Specification<User> streetContains(String street) {
		return addressAttributeContains("street", street);
	}

	private Specification<User> addressAttributeContains(String attribute, String value) {
		return (root, query, cb) -> {
			if (value == null) {
				return null;
			}

			ListJoin<User, Address> addresses = root.joinList("addresses", JoinType.INNER);

			return cb.like(cb.lower(addresses.get(attribute)), containsLowerCase(value));
		};
	}

	private Specification<User> isPremium(String email) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> orPredicates = new ArrayList<>();
		        Predicate p1 = criteriaBuilder.equal(root.get("email"), email);
		        orPredicates.add(criteriaBuilder.or(p1));
		        Predicate p2 = criteriaBuilder.like(root.get("email"), "%@test.com.br%");
		        orPredicates.add(criteriaBuilder.or(p2));
		        
		        Predicate o = criteriaBuilder.and(criteriaBuilder.equal(root.get("firstName"), "Administrator"), criteriaBuilder.equal(root.get("firstName"), "Administrador"));
		        Predicate p = criteriaBuilder.or(orPredicates.toArray(new Predicate[] {}));
		        
		        return criteriaBuilder.and(p, o);
			}
		};
	}
}
