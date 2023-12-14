package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.Vote;

public class VoteCountRowMapper implements RowMapper<Vote>{

	@Override
	public Vote mapRow(ResultSet rs, int rowNum) throws SQLException {
		Vote vote = new Vote();
		
		vote.setVoteCount(rs.getInt("vote_count"));
		
		return vote;
	}

}
