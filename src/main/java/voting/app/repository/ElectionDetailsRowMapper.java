package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.NewElection;

public class ElectionDetailsRowMapper implements RowMapper<NewElection>{

	@Override
	public NewElection mapRow(ResultSet rs, int rowNum) throws SQLException {

		NewElection election = new NewElection();
		
//		election.set
		
		return election;
	}

}
