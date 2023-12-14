package voting.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import voting.app.entities.Candidate;

public class CandidateRowMapper implements RowMapper<Candidate> {

	@Override
	public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
		Candidate candidate = new Candidate();

		candidate.setCandidateId(rs.getInt("candidateId"));
		candidate.setCandidateName(rs.getString("candidateName"));
		candidate.setCandidateDob(rs.getDate("candidateDob"));
		candidate.setPartyName(rs.getString("partyName"));
		candidate.setPartyManifesto(rs.getBlob("partyManifesto"));
		candidate.setPartyLogo(rs.getBlob("partyLogo"));
		candidate.setIdProof(rs.getBlob("idproof"));
		candidate.setElectionId(rs.getInt("electionId"));
		candidate.setElectionName(rs.getString("electionName"));
		candidate.setStatus(rs.getBoolean("status"));
		candidate.setConstitution(rs.getString("constitution"));
		candidate.setVoteCount(rs.getInt("candidate_vote_count"));
		candidate.setEmail(rs.getString("emailId"));
		candidate.setCandidateProfilePic(rs.getBlob("candidateImage"));
		
		return candidate;
	}

}
