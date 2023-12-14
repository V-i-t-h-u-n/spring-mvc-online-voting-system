package voting.app.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import voting.app.entities.Candidate;
import voting.app.entities.Party;
import voting.app.entities.Vote;

public class CandidateDaoImplementation implements CandidateDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int registerCandidate(Candidate candidate) {

//		String register_query = "INSERT INTO candidate "
//				+ "(candidateName,"
//				+ " candidateDob,"
//				+ " partyName,"
//				+ " partyManifesto,"
//				+ " partyLogo,"
//				+ " idproof,"
//				+ " electionId,"
//				+ " status,"
//				+ " constitution,"
//				+ " candidate_vote_count,"
//				+ " electionName) "
//				+ " VALUES "
//				+ "(?,?,?,?,?,?,?,?,?,?,?)";

		String register_query = "INSERT INTO candidate "
				+ "	(candidateName,"
				+ "  candidateDob,"
				+ "  partyName,"
				+ "	 partyManifesto,"
				+ "	 partyLogo,"
				+ "	 idproof,"
				+ "	 electionId,"
				+ "	 status,"
				+ "	 constitution,"
				+ "	 candidate_vote_count,"
				+ "	 electionName,"
				+ "  emailId,"
				+ "  candidateImage)"
				+" SELECT ?,?,?,?,?,?,?,?,?,?,?,?,? " +
		        "  WHERE NOT EXISTS (" +
		        "    SELECT 1 " +
		        "    FROM candidate " +
		        "    WHERE " +
		        "        electionId = ? " +
		        "        AND electionName = ? " +
		        "        AND partyName = ? " +
		        "        AND constitution = ?" +
		        ")";
		
		
		
		int result = jdbcTemplate.update(register_query,
				candidate.getCandidateName(),
				candidate.getCandidateDob(),
						candidate.getPartyName(),
						candidate.getPartyManifesto(),
						candidate.getPartyLogo(),
						candidate.getIdProof(),
						candidate.getElectionId(),
						candidate.isStatus(),
						candidate.getConstitution(),
						candidate.getVoteCount(),
						candidate.getElectionName(),
						candidate.getEmail(),
						candidate.getCandidateProfilePic(),
						candidate.getElectionId(),
						candidate.getElectionName(),
						candidate.getPartyName(),
						candidate.getConstitution()
						);
		
//		,
//		candidate.getElectionId(),
//		candidate.getElectionName(),
//		candidate.getPartyName(),
//		candidate.getConstitution()
//		electionId = ? " +
//		        "        AND electionName = ? " +
//		        "        AND partyName = ? " +
//		        "        AND constitution = ?"
//		
		
//		String ADD_CANDIDATE_VOTE_TABLE = " INSERT INTO candidate_vote_count "
//				+ "(candidateId,vote_count)"
//				+ " VALUE "
//				+ "(?,?)";
//		
//		int addedResult = jdbcTemplate.update(
//				ADD_CANDIDATE_VOTE_TABLE,
//				candidate.getCandidateId(),
//				0);
//		System.out.println(addedResult);
		
		
		return result;
	}

	@Override
	public List<Candidate> displayCandidateList() {

		String showAllCandidates = "SELECT * FROM candidate where status = ?";
		System.out.println(jdbcTemplate.query(showAllCandidates, new CandidateRowMapper(),1));
		return jdbcTemplate.query(showAllCandidates, new CandidateRowMapper(),1);
	}

	@Override
	public List<Candidate> displayCandidateListRegional() {

		String showAllCandidates = "SELECT * FROM candidate";
		
		return jdbcTemplate.query(showAllCandidates, new CandidateRowMapper());
	}
	
	@Override
	public List<Candidate> displayCandidateListVoter(String electionName) {

		String showAllCandidates = "SELECT * FROM candidate WHERE "
				+ "electionName = ? AND status = ?";
		
		return jdbcTemplate.query(
				showAllCandidates,
				new CandidateRowMapper(),
				electionName,1);
	}

	@Override
	public int updateVote(int candidateId) {
		
		return 0;
//		String UPDATE_CANDIDATE_VOTE = "UPDATE candidate_vote_count SET"
//				+ " vote_count = ? WHERE candidateId = ?";
//		
//		return jdbcTemplate.update(UPDATE_CANDIDATE_VOTE,
//								 1,
//								candidateId);
	}

	@Override
	public int insertStatusTable(int candidateId) {
		System.out.println("test insert status table" + candidateId);
		
		String GET_VOTE_COUNT = "INSERT INTO candidate_vote_count "
				+ "(candidateId,vote_count)"
				+ " VALUES "
				+ "(?,?)";			
		
		return jdbcTemplate.update(GET_VOTE_COUNT,candidateId,0);
	}

	@Override
	public List<Candidate> displayCandidateListbyConstitution
					(String constitution, int electId) {
		String GET_ALL_CANDIDATES_BY_CONSTITUTION = "SELECT * FROM candidate WHERE "
				+ "constitution = ? AND electionId = ? AND status = ?";
		
		return jdbcTemplate.query(
				GET_ALL_CANDIDATES_BY_CONSTITUTION,
				new CandidateRowMapper(),
				constitution,
				electId,
				1);
	
	}

	@Override
	public List<Party> getAllParties() {
		
		String GET_ALL_PARTIES = "SELECT * FROM party";
		
		return jdbcTemplate.query(GET_ALL_PARTIES,
				new PartyRowMapper());
	}

	
	@Override
	public int updateCandidateVoteCount(String candidateId,int electId,String partyName) {

		
		String UPDATE_CANDIDATE_VOTE = "UPDATE candidate "
							+ "SET candidate_vote_count = candidate_vote_count + 1 "
							+ "WHERE candidateId = ? AND electionId = ? AND partyName = ?";
		
		return jdbcTemplate.update(UPDATE_CANDIDATE_VOTE,
				candidateId,
				electId,
				partyName);
	}

	@Override
	public boolean isEmailInDatabase(String email) {
		
		String CHECK_EMAIL = "SELECT * FROM candidate where emailId = ?";
	
		return jdbcTemplate.query
				(CHECK_EMAIL,new CandidateRowMapper(), email)
				.size() == 1 ? true : false;
	}

	@Override
	public Candidate getCandidate(String email) {
		
		String GET_CANDIDATE = "SELECT * FROM candidate WHERE emailId = ?";
		
		return jdbcTemplate.queryForObject(GET_CANDIDATE, new CandidateRowMapper(),email);
	}

	@Override
	public int updateCandidate(Candidate candidate) {
		String UPDATE_CANDIDATE = "UPDATE candidate"
				+ " SET candidateName = ?,"
				+ " candidateDob = ?,"
				+ " partyName = ?, " 
				+ " partyManifesto = ?,"
				+ " partyLogo = ?,"
				+ " idproof = ?,"
				+ " electionId = ?,"
				+ " electionName = ?,"
				+ " status = ?,"
				+ " constitution = ?,"
				+ " candidate_vote_count = ?,"
				+ " emailId = ?,"
				+ " candidateImage = ?"
				+ " WHERE candidateId = ?";
		
		return jdbcTemplate.update(UPDATE_CANDIDATE,
				candidate.getCandidateName(),
				candidate.getCandidateDob(),
				candidate.getPartyName(), 
				candidate.getPartyManifesto(),
				candidate.getPartyLogo(),
				candidate.getIdProof(),
				candidate.getElectionId(),
				candidate.getElectionName(),
				candidate.isStatus(),
				candidate.getConstitution(),
				candidate.getVoteCount(),
				candidate.getEmail(),
				candidate.getCandidateProfilePic(),
				candidate.getCandidateId());
		
	}

	
	

	
	
	
	
	
}
