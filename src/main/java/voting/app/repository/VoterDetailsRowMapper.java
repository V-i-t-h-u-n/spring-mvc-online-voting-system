package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.VoterDetails;

public class VoterDetailsRowMapper implements RowMapper<VoterDetails>{

	@Override
	public VoterDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		VoterDetails details = new VoterDetails();
		
		details.setVoterName(rs.getString("voterName"));
		details.setVoterProfilePic(rs.getBlob("voterProfilePic"));
		details.setVoterDateOfBirth(rs.getDate("voterDateOfBirth"));
		details.setConstitution(rs.getString("constitution"));
		details.setElectionName(rs.getString("election_name"));
		details.setElectionStartDate(rs.getDate("start_date"));
		java.sql.Timestamp currentDateTime = rs.getTimestamp("currentDateTime");
	    details.setCurrentDateTime(currentDateTime.toLocalDateTime());
//		details.setCurrentDateTime(rs.getTimestamp(""));
		details.setStatus(rs.getBoolean("status"));
		
		
		
		return details;
	}

}
