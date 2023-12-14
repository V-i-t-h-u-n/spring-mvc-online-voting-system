package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.VoterStatsByVotersParticipation;

public class VoterStatsByVotersParticipationRowMapper implements RowMapper<VoterStatsByVotersParticipation>{

	@Override
	public VoterStatsByVotersParticipation mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		VoterStatsByVotersParticipation participation = new VoterStatsByVotersParticipation();
		participation.setTotalVotersRegistered(rs.getInt(1));
		participation.setTotalVotesCasted(rs.getInt(2));
		return participation;
	}

}
