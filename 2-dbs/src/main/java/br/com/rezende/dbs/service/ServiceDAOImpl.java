package br.com.rezende.dbs.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class ServiceDAOImpl implements ServiceDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("appDataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<ServiceEntity> getServices() {
		String sql = "select id, name, price from oficial.service";
		return jdbcTemplate.query(sql, new ServiceEntityMapper());
	}

	private static final class ServiceEntityMapper implements RowMapper<ServiceEntity> {
		public ServiceEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			ServiceEntity service = new ServiceEntity();
			service.setId(UUID.fromString(rs.getString("id")));
			service.setName(rs.getString("name"));
			service.setPrice(rs.getDouble("price"));
			return service;
		}
	}

}
