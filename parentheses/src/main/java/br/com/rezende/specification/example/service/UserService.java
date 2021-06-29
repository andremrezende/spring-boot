package br.com.rezende.specification.example.service;

import br.com.rezende.specification.example.controller.dto.UserDto;
import br.com.rezende.specification.example.controller.dto.UserListRequest;
import br.com.rezende.specification.example.controller.mapper.UserMapper;
import br.com.rezende.specification.example.controller.specification.UserListSpecification;
import br.com.rezende.specification.example.domain.User;
import br.com.rezende.specification.example.repository.UserRepository;
import br.com.rezende.specification.example.service.errors.ErrorResponse;
import io.vavr.control.Either;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserListSpecification userListSpecification;

    public UserService(UserRepository userRepository, UserMapper userMapper, UserListSpecification userListSpecification) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userListSpecification = userListSpecification;
    }

    public Either<ErrorResponse, Page<UserDto>> findAll(UserListRequest request, Pageable pageable) {
        Page<User> userPage = userRepository.findAll(userListSpecification.getFilter(request), pageable);
        return Either.right(userPage.map(userMapper::map));
    }
}
