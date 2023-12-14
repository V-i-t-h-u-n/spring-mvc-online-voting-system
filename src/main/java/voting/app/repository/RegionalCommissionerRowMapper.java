package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.RegionalCommissioner;

public class RegionalCommissionerRowMapper implements RowMapper<RegionalCommissioner>{

	@Override
	public RegionalCommissioner mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RegionalCommissioner regionalCommissioner = new RegionalCommissioner();
		
		regionalCommissioner.setId(rs.getInt("id"));
		regionalCommissioner.setDateOfbirth(rs.getDate("dob"));
		regionalCommissioner.setNationality(rs.getString("nationality"));
		regionalCommissioner.setIdProof(rs.getBlob("id_proof"));
		regionalCommissioner.setHashedPassword(rs.getString("hashedPassword"));
		regionalCommissioner.setSalt(rs.getString("salt"));
		regionalCommissioner.setAdmin(rs.getBoolean("isAdmin"));
		regionalCommissioner.setUserName(rs.getString("userName"));
		regionalCommissioner.setLoggedIn(rs.getBoolean("status"));
		return regionalCommissioner;
		
	
	}

}
