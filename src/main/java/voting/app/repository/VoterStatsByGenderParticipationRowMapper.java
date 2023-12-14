package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.VoterStatsByGenderParticipation;

public class VoterStatsByGenderParticipationRowMapper implements RowMapper<VoterStatsByGenderParticipation>{

	@Override
	public VoterStatsByGenderParticipation mapRow(ResultSet rs, int rowNum) throws SQLException {
		VoterStatsByGenderParticipation genderParticipation = new VoterStatsByGenderParticipation();
		genderParticipation.setMaleVoters(rs.getInt(1));
		genderParticipation.setFemaleVoters(rs.getInt(2));
		genderParticipation.setPercentageOfMaleVoted(rs.getDouble(3));
		genderParticipation.setPercentageOfFemaleVoted(rs.getDouble(4));
		
		return genderParticipation;
	}

}
