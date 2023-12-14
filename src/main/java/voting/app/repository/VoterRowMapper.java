package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.Voter;

public class VoterRowMapper implements RowMapper<Voter>{

	@Override
	public Voter mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Voter voter = new Voter();
		
		voter.setVoterId(rs.getInt("voterId"));
		voter.setVoterName(rs.getString("voterName"));
		voter.setVoterEmail(rs.getString("voterEmail"));
		voter.setVoterPhoneNumber(rs.getString("voterPhoneNumber"));
		voter.setVoterDateOfBirth(rs.getDate("voterDateOfBirth"));
		voter.setVoterGender(rs.getString("voterGender"));
		voter.setVoterNationality(rs.getString("voterNationality"));
		voter.setVoterIdProof(rs.getBlob("voterIdProof"));
		voter.setVoterProfilePic(rs.getBlob("voterProfilePic"));
		voter.setSalt(rs.getString("salt"));
		voter.setHashedPassword(rs.getString("hashedPassword"));
		voter.setVoterAadharNo(rs.getString("voterAadharNo"));
		voter.setStatus(rs.getBoolean("status"));
		voter.setConstitution(rs.getString("constitution"));
		
		return voter;
	}

}
