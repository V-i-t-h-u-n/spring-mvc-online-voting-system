package voting.app.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import voting.app.entities.Candidate;
import voting.app.entities.LoginCredentials;
import voting.app.entities.NewElection;
import voting.app.entities.Party;
import voting.app.entities.VoteResult;
import voting.app.entities.Voter;
import voting.app.entities.VoterDetails;
import voting.app.entities.VoterStatsByAge;
import voting.app.entities.VoterStatsByGenderParticipation;
import voting.app.entities.VoterStatsByVotersParticipation;
import voting.app.entities.VoterStatsbyConstitutionParticipation;

public class VoterDaoImplementation implements VoterDao { 

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int registerVoter(Voter voter) {
		
//voterId, voterName, voterEmail, voterPhoneNumber,
		//voterDateOfBirth, voterGender, voterNationality,
		//voterIdProof, voterProfilePic, salt, hashedPassword
		String INSERT_VOTER ="INSERT INTO voter "
				+ "(voterName,voterEmail,voterPhoneNumber,voterDateOfBirth,"
				+ "voterGender,voterNationality,voterIdProof,voterProfilePic"
				+ ",salt,hashedPassword,voterAadharNo,status,constitution) "
				+ "VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		return jdbcTemplate.update(INSERT_VOTER,voter.getVoterName(),
				voter.getVoterEmail(),
				voter.getVoterPhoneNumber(),
				voter.getVoterDateOfBirth(), voter.getVoterGender(),voter.getVoterNationality(),
				voter.getVoterIdProof(),voter.getVoterProfilePic(),voter.getSalt(),
				voter.getHashedPassword(),voter.getVoterAadharNo(), false,
				voter.getConstitution());
	}

	@Override
	public List<NewElection> displayElectionDetails(Voter voter) {
		
//		String DISPLAY_ALL_ELECTIONS = "SELECT elec.* "
//				+ " FROM add_election elec "
//				+ " LEFT JOIN voter_voting_status vvs ON "
//				+ " elec.id = vvs.electionId AND vvs.voterId = ? "
//				+ " WHERE vvs.voting_status != 1 "
//				+ " OR vvs.voting_status IS NULL ";
		
		String DISPLAY_ALL_ELECTIONS = "SELECT elec.* FROM add_election elec "
				+ "	LEFT JOIN voter_voting_status vvs ON elec.id = vvs.electionId AND"
				+ " vvs.voterId = ?"
				+ "	WHERE (vvs.voting_status != 1 OR "
				+ " vvs.voting_status IS NULL)"
				+ "	AND elec.start_date = CURDATE()";
	
		List<NewElection> list = jdbcTemplate.query(DISPLAY_ALL_ELECTIONS,
				new AddElectionRowMapper(),voter.getVoterId());
		return list;
	}

	@Override
	public LoginCredentials login(String voterEmail) {
		String getCredentital = "SELECT hashedPassword,salt "
				+ " FROM voter where voterEmail = ?";
			
			return jdbcTemplate
				.queryForObject(
						getCredentital,
						new LoginRowMapper(),
						voterEmail);		
	}

	@Override
	public int castVote(int voterId,int electionId) {

		String SET_VOTE_STATUS = "INSERT INTO voter_voting_status "
				+ "(voterId, voting_status,electionId) "
				+ "VALUES "
				+ "(?,?,?)";
		return jdbcTemplate.update(SET_VOTE_STATUS,
				voterId,
				1,
				electionId);
		
	}

	@Override
	public int updateVoterDetails(Voter voter) {
		
		System.out.println(" update fn - " + voter );
		// 
		String UPDATE_VOTER= "UPDATE voter SET "
				+ "voterName = ?,"
				+ "voterEmail = ?, "
				+ "voterPhoneNumber = ?, "
				+ "voterDateOfBirth = ?,"
				+ "voterGender = ?, "
				+ "voterNationality = ?, "
				+ "voterIdProof = ?, "
				+ "voterProfilePic = ?, "
				+ "voterAadharNo = ?,"
				+ "constitution = ?"
				+ " WHERE voterId = ?";
		
		return jdbcTemplate.update(UPDATE_VOTER,
				voter.getVoterName(),
				voter.getVoterEmail(),
				voter.getVoterPhoneNumber(),
				voter.getVoterDateOfBirth(),
				voter.getVoterGender(),
				voter.getVoterNationality(),
				voter.getVoterIdProof(),
				voter.getVoterProfilePic(),
				voter.getVoterAadharNo(),
				voter.getConstitution(),
				voter.getVoterId()
				);
	}

	@Override
	public Voter displayVoter(String voterEmail) {

		String DISPLAY_VOTER = "SELECT * FROM voter WHERE voterEmail = ?";	
		
		return jdbcTemplate.queryForObject(DISPLAY_VOTER,
				new VoterRowMapper(),
				voterEmail);
		
	}
	
	@Override
	public List<Voter> displayAllVoterList() {
	String showAllCandidates = "SELECT * FROM voter";

	return jdbcTemplate.query(showAllCandidates, new VoterRowMapper());
	}

	@Override
	public int updatePartyVoteCount(String candidateId, String elecName, String partyName, int electionId) {

		String UPDATE_PARTY_VOTE_COUNT = "UPDATE party "
				+ " SET voteCount = voteCount + 1 "
				+ " WHERE id = ? and partyName = ? ";
		
		return jdbcTemplate.update(UPDATE_PARTY_VOTE_COUNT,
				electionId,
				partyName);

	}

	@Override
	public List<NewElection> displayElectionDetails() {

		String DISPLAY_ALL_ELECTION = "SELECT * FROM add_election "
				+ "WHERE start_date > CURDATE() AND status = 1";
		
		return jdbcTemplate.query(DISPLAY_ALL_ELECTION, new AddElectionRowMapper());
	}

	@Override
	public List<VoteResult> displayPartyVoteCount(String electionId) {

		String DISPLAY_PARTY_VOTE_COUNT = "SELECT electionName, "
				+ " partyName, constitution,partyLogo, "
				+ " SUM(candidate_vote_count) AS votes"
				+ "	FROM candidate"
				+ "	WHERE electionId = ? AND status = ?"
				+ "	GROUP BY electionName, partyName, constitution,partyLogo"
				+ " ORDER BY votes DESC";
		
		return jdbcTemplate.query(DISPLAY_PARTY_VOTE_COUNT, 
							new VoteResultRowMapper(),electionId,1);
	}

	@Override
	public List<Candidate> displayCandidateVoteCount(String electionId) {
		String DISPLAY_CANDIDATE_VOTE_COUNT = "SELECT * FROM candidate WHERE electionId = ?"
				+ " AND status = ?"
				+ " ORDER BY candidate_vote_count DESC";
		
		return jdbcTemplate.query(DISPLAY_CANDIDATE_VOTE_COUNT, 
				new CandidateRowMapper(),electionId,1);
	}

	@Override
	public List<NewElection> displayPastElectionDetails(Voter voter) {
		
		String DISPLAY_ALL_ELECTIONS = "SELECT elec.* FROM add_election elec "
				+ "	LEFT JOIN voter_voting_status vvs ON elec.id = vvs.electionId AND"
				+ " vvs.voterId = ?"
				+ "	WHERE (vvs.voting_status != 1 OR "
				+ " vvs.voting_status IS NULL)"
				+ "	AND elec.start_date < CURDATE()";
	
		List<NewElection> list = jdbcTemplate.query(DISPLAY_ALL_ELECTIONS,
				new AddElectionRowMapper(),voter.getVoterId());
		return list;
	
	}

	@Override
	public List<NewElection> displayFutureElectionDetails(Voter voter) {

		String DISPLAY_ALL_ELECTIONS = "SELECT elec.* FROM add_election elec "
				+ "	LEFT JOIN voter_voting_status vvs ON elec.id = vvs.electionId AND"
				+ " vvs.voterId = ?"
				+ "	WHERE (vvs.voting_status != 1 OR "
				+ " vvs.voting_status IS NULL)"
				+ "	AND elec.start_date > CURDATE()";
	
		List<NewElection> list = jdbcTemplate.query(DISPLAY_ALL_ELECTIONS,
				new AddElectionRowMapper(),voter.getVoterId());
		return list;
	
	}

	@Override
	public List<VoterStatsByAge> getVoterStatsByAge() {
		
		String GET_VoterStatsByAge = "SELECT \r\n"
				+ "    CASE \r\n"
				+ "        WHEN age >= 18 AND age < 25 THEN '18-24'\r\n"
				+ "        WHEN age >= 25 AND age < 35 THEN '25-34'\r\n"
				+ "        WHEN age >= 35 AND age < 45 THEN '35-44'\r\n"
				+ "        WHEN age >= 45 AND age < 55 THEN '45-54'\r\n"
				+ "        WHEN age >= 55 THEN '55+'\r\n"
				+ "        ELSE 'Below 18'\r\n"
				+ "    END AS age_range,\r\n"
				+ "    COUNT(*) AS count_of_voters\r\n"
				+ "FROM (\r\n"
				+ "    SELECT TIMESTAMPDIFF(YEAR, voterDateOfBirth, CURDATE()) AS age\r\n"
				+ "    FROM voter\r\n"
				+ ") AS ages\r\n"
				+ "GROUP BY age_range\r\n"
				+ "ORDER BY\r\n"
				+ "    FIELD(age_range, 'Below 18', '18-24', '25-34', '35-44', '45-54', '55+')";
		
		
		return jdbcTemplate.query(GET_VoterStatsByAge, new VoterStatsByAgeRowMapper());
	}

	@Override
	public List<VoterStatsbyConstitutionParticipation> getVoterStatsbyConstitutionParticipation(String electionId) {
		String GET_VoterStatsbyConstitutionParticipation = "SELECT \r\n"
				+ "    v.constitution,\r\n"
				+ "    COUNT(*) AS voters_registered,\r\n"
				+ "    COUNT(vs.voterId) AS votes_casted\r\n"
				+ "FROM voter v\r\n"
				+ "LEFT JOIN voter_voting_status vs ON v.voterId = vs.voterId AND vs.electionId = ?\r\n"
				+ "GROUP BY v.constitution order by v.constitution ASC\r\n";
				
		
		return jdbcTemplate.query(GET_VoterStatsbyConstitutionParticipation, new VoterStatsbyConstitutionParticipationRowMapper(),electionId);
	}

	@Override
	public List<VoterStatsByGenderParticipation> getVoterStatsByGenderParticipation(String electionId) {

		String GET_VoterStatsByGenderParticipation = "SELECT \r\n"
				+ "    SUM(CASE WHEN v.voterGender = 'M' THEN 1 ELSE 0 END) AS male_voters,\r\n"
				+ "    SUM(CASE WHEN v.voterGender = 'F' THEN 1 ELSE 0 END) AS female_voters,\r\n"
				+ "    (SUM(CASE WHEN v.voterGender = 'M' THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS percentage_male_voted,\r\n"
				+ "    (SUM(CASE WHEN v.voterGender = 'F' THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS percentage_female_voted\r\n"
				+ "FROM voter_voting_status vs\r\n"
				+ "JOIN voter v ON vs.voterId = v.voterId\r\n"
				+ "WHERE vs.electionId = ?\r\n"
				+ "  AND vs.voting_status = 1";
		return jdbcTemplate.query(GET_VoterStatsByGenderParticipation, new VoterStatsByGenderParticipationRowMapper(),electionId);
	}

	@Override
	public List<VoterStatsByVotersParticipation> getVoterStatsByVotersParticipation(String electionId) {
		
		String GET_VoterStatsByVotersParticipation = "SELECT \r\n"
				+ "    (SELECT COUNT(*) FROM voter) AS total_registered_voters,\r\n"
				+ "    (SELECT COUNT(*) FROM voter_voting_status \r\n"
				+ "    WHERE voting_status = 1 AND electionId = ?) AS total_votes_casted\r\n"
				+ "";
		return jdbcTemplate.query(GET_VoterStatsByVotersParticipation, new VoterStatsByVotersParticipationRowMapper(),electionId);
	}

	@Override
	public List<VoterDetails> displayVoterDetails(int voterId) {

		String GET_VOTER_DETAILS = 
				" SELECT v.voterName, v.voterDateOfBirth, v.constitution, v.voterProfilePic, v.status, ae.election_name, ae.start_date, NOW() AS currentDateTime\r\n"
				+ "FROM voter v\r\n"
				+ "LEFT JOIN add_election ae ON v.voterId = ae.id\r\n"
				+ "WHERE v.voterId = ?";
		
		return jdbcTemplate.query(GET_VOTER_DETAILS, new VoterDetailsRowMapper(),
					voterId);
	}

	@Override
	public Voter getVoter(int voterId) {
		
		return null;
	}
	
}
