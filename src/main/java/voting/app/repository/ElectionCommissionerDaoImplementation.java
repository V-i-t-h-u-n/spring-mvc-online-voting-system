package voting.app.repository;

import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import voting.app.entities.ElectionAdmin;
import voting.app.entities.LoginCredentials;
import voting.app.entities.NewElection;
import voting.app.entities.RegionalCommissioner;

public class ElectionCommissionerDaoImplementation implements ElectionCommissionerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int addElection(NewElection newElection) {

		String addNewElectionQuery = "INSERT INTO add_election "
				+ "(election_name, start_date, status)"
				+ " VALUES "
				+ "(?,?,?)";

		return jdbcTemplate.update(addNewElectionQuery, 
							newElection.getElectionName(),
							newElection.getElectionDate(),
							newElection.isStatus()
							);

	}

	@Override
	public List<ElectionAdmin> displayProfile(String email) {
		String selectQuery = "SELECT * FROM admin WHERE emailId = ? AND status = ?"; 
		return jdbcTemplate.query(selectQuery, new ProfileRowMapper(),
				email,
				0);
		
	}

	@Override
	public LoginCredentials login(String email) {

		String getCredentital = "SELECT hashedPassword,salt "
				+ " FROM admin where emailId = ?";
		
		
//		if(jdbcTemplate
//				.update(
//						getCredentital,
//						new LoginRowMapper(),
//						userName) == 1)
			
			return jdbcTemplate
				.queryForObject(
						getCredentital,
						new LoginRowMapper(),
						email);
//		else
//			 return new LoginCredentials();
	}

	//@Override
//	`id`, `dob`, `nationality`, `id_proof`, `hashedPassword`, 
//	`salt`, `isAdmin`, `status`, `userName`
//	public int addRegmanager(ElectionAdmin regManger) {
//		
//		String INSERT_REG_MANAGER="INSERT into admin "
//				+ "(dob,nationality,id_proof,hashedPassword,salt,isAdmin,status,userName,emailId) "
//				+ "value (?,?,?,?,?,?,?,?,?)";
//		return jdbcTemplate.update(INSERT_REG_MANAGER,
//				regManger.getDateOfbirth(),
//				regManger.getNationality(),
//				regManger.getIdProof(),
//				regManger.getHashedPassword(),
//				regManger.getSalt(),
//				regManger.isAdmin(),
//				true,
//				regManger.getUserName(),
//				regManger.getEmail());
//	}

	@Override
	public int regManagerApproval(int regManagerId, String access) {
		String UPDATE_STATUS = "UPDATE admin SET isAdmin= ? WHERE id = ?";
		return jdbcTemplate.update(UPDATE_STATUS,
				access.equalsIgnoreCase("grant")?1:0,
				regManagerId);
	}

	@Override
	public int setParty(int electionId, 
			String partyName, 
			Blob partyLogoImg,
			Blob partyManifesto) {
		
		String ADD_PARTY = "INSERT INTO party "
				+ "(`partyName`, `id`, `voteCount`, `partyLogo`, `partyManifesto`) "
				+ "VALUES "
				+ "(?,?,?,?,?)";
		
		return jdbcTemplate.update(ADD_PARTY,
							partyName,
							electionId,
							0,
							partyLogoImg,
							partyManifesto);
	}

	@Override
	public List<NewElection> getAllElections() {

		String DISPLAY_ALL_ELECTION = "SELECT * FROM add_election";
		return jdbcTemplate.query(DISPLAY_ALL_ELECTION, 
				new AddElectionRowMapper());
	}

	@Override
	public List<NewElection> getAllActiveElections() {
		
		String DISPLAY_ALL_ELECTION = "SELECT * FROM add_election "
				+ "WHERE start_date > CURDATE()";
		
		return jdbcTemplate.query(DISPLAY_ALL_ELECTION, new AddElectionRowMapper());
		
	}

	@Override
	public int updateEleCommDetails(ElectionAdmin electionCommisioner) {
		String UPDATE_REGADMIN= "UPDATE admin SET userName=?,dob=?,"
				+ "nationality=?,"
				+ "id_proof=?, emailId = ? where id=?";
		
		return jdbcTemplate.update(UPDATE_REGADMIN,
				electionCommisioner.getUserName(),electionCommisioner.getDateOfbirth(),
				electionCommisioner.getNationality(),electionCommisioner.getIdProof(),
				electionCommisioner.getEmail(),
				electionCommisioner.getId()
				);
	}

	@Override
	public List<NewElection> viewDeleteElection() {
		String DISPLAY_ALL_ELECTION_TO_DELETE = "SELECT * FROM add_election where status=1";
		return jdbcTemplate.query(DISPLAY_ALL_ELECTION_TO_DELETE, 
				new AddElectionRowMapper());
	}

	@Override
	public int deleteElection(int electionId) {
		String DELETE_ELECTION="UPDATE add_election "
				+ "SET status= false where id=?";
		return jdbcTemplate.update(DELETE_ELECTION,electionId);
	}


	@Override
	public List<NewElection> viewDeletedElection() {
		String DISPLAY_ALL_ELECTION_DELETED = "SELECT * FROM add_election where status=0";
		return jdbcTemplate.query(DISPLAY_ALL_ELECTION_DELETED, 
				new AddElectionRowMapper());
	}

//	@Override
//	public int addRegmanager(ElectionAdmin regManager) {
//
//		
//		return 0;
//	}
	
	


}
