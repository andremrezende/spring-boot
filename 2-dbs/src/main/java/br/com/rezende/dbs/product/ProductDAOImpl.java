package br.com.rezende.dbs.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl implements ProductDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<ProductEntity> getProducts() {
		String sql = "select id, name, price from migration.product";
		return jdbcTemplate.query(sql, new ProductEntityMapper());
	}

	private static final class ProductEntityMapper implements RowMapper<ProductEntity> {
		public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductEntity product = new ProductEntity();
			product.setId(UUID.fromString(rs.getString("id")));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			return product;
		}
	}
}
