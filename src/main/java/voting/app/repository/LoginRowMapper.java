package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.LoginCredentials;

public class LoginRowMapper implements RowMapper<LoginCredentials>{

//	@Autowired
//	LoginCredentials credentials;
	@Override
	public LoginCredentials mapRow(ResultSet rs, int rowNum) throws SQLException {

		LoginCredentials credentials = new LoginCredentials();
		credentials.setSalt(rs.getString("salt"));
		credentials.setHashedPassword(rs.getString("hashedPassword"));
		return credentials;
	}

}
