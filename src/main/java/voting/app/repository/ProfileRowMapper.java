package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.ElectionAdmin;
import voting.app.entities.ElectionCommissioner;

public class ProfileRowMapper implements RowMapper<ElectionAdmin>{

	@Override
	public ElectionAdmin mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ElectionAdmin commissioner = new ElectionAdmin();
		
		commissioner.setId(rs.getInt("id"));
		commissioner.setDateOfbirth(rs.getDate("dob"));
		commissioner.setIdProof(rs.getBlob("id_proof"));
		commissioner.setAdmin(rs.getBoolean("isAdmin"));
		commissioner.setNationality(rs.getString("nationality"));
		commissioner.setHashedPassword(rs.getString("hashedPassword"));
		commissioner.setSalt((rs.getString("salt")));
		commissioner.setAdmin(rs.getBoolean("isAdmin"));
		commissioner.setLoggedIn(rs.getBoolean("status"));
		commissioner.setUserName(rs.getString("userName"));
		commissioner.setEmail(rs.getString("emailId"));
		return commissioner;
	}

}
