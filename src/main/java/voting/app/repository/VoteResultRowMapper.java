package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.VoteResult;

public class VoteResultRowMapper implements RowMapper<VoteResult>{

	@Override
	public VoteResult mapRow(ResultSet rs, int rowNum) throws SQLException {

		VoteResult result = new VoteResult();
		
		result.setElectionName(rs.getString("electionName"));
		result.setpartyName(rs.getString("partyName"));
		result.setConstitution(rs.getString("constitution"));
		result.setPartyLogo(rs.getBlob("partyLogo"));
		result.setVotes(rs.getInt("votes"));
		
		return result;
	}
	

}
