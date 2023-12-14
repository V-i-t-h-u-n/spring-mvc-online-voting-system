package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.Party;

public class PartyRowMapper implements RowMapper<Party>{

	@Override
	public Party mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Party newParty = new Party();
		newParty.setPartyName(rs.getString("partyName"));
		newParty.setElectionId(rs.getInt("id"));
		newParty.setVoteCount(rs.getInt("voteCount"));
		newParty.setPartyLogo(rs.getBlob("partyLogo"));
		newParty.setPartyManifesto(rs.getBlob("partyManifesto"));
		
		return newParty;
	}

}
