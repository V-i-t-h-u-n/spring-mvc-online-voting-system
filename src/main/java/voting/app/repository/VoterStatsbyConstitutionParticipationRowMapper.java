package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.VoterStatsbyConstitutionParticipation;

public class VoterStatsbyConstitutionParticipationRowMapper implements RowMapper<VoterStatsbyConstitutionParticipation>{

	@Override
	public VoterStatsbyConstitutionParticipation mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		VoterStatsbyConstitutionParticipation participation = new VoterStatsbyConstitutionParticipation();
		
		participation.setConstitution(rs.getString(1));
		participation.setTotalVotersRegistered(rs.getInt(2));
		participation.setTotalVotesCasted(rs.getInt(3));		
		return participation;
	}

}
