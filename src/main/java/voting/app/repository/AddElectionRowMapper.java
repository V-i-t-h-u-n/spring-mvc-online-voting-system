package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.NewElection;

public class AddElectionRowMapper implements RowMapper<NewElection> {
//	@Autowired
//	NewElection election;
	
	@Override
	public NewElection mapRow(ResultSet rs, int rowNum) throws SQLException {
		NewElection election = new NewElection();
		election.setElectionId(rs.getInt("id"));
		election.setElectionName(rs.getString("election_name"));
		election.setElectionDate(rs.getDate("start_date"));
		election.setStatus(rs.getBoolean("status"));

		return election;
	}

}
