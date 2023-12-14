package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.VoterStatsByAge;

public class VoterStatsByAgeRowMapper implements RowMapper<VoterStatsByAge>{

	@Override
	public VoterStatsByAge mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		VoterStatsByAge statsByAge = new VoterStatsByAge();
		
		statsByAge.setAgeRange(rs.getString(1));
		statsByAge.setTotalCountOfVoters(rs.getInt(2));
		
		return statsByAge;
	}

}
