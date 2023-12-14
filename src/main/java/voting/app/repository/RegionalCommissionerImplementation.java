package voting.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import voting.app.entities.ElectionAdmin;
import voting.app.entities.LoginCredentials;
import voting.app.entities.NewElection;
import voting.app.entities.RegionalCommissioner;
import voting.app.entities.Voter;

public class RegionalCommissionerImplementation implements RegionalCommissionerDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int candidateApproval(int candidateId, String access) {

		String UPDATE_STATUS = "UPDATE candidate SET status = ? WHERE candidateId = ?";
//		System.out.println(candidateId + " : " + access);
		return jdbcTemplate.update(UPDATE_STATUS,
				access.equalsIgnoreCase("grant")?1:0,
				candidateId);
		
	}
	
	@Override
	public boolean voterApproval(Voter voter) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addElection(NewElection newElection) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public LoginCredentials login(String email) {

		String getCredentital = "SELECT hashedPassword,salt "
				+ " FROM admin where emailId = ? AND status = ?";
		
		
//		if(jdbcTemplate
//				.update(
//						getCredentital,
//						new LoginRowMapper(),
//						userName) == 1)
			
			return jdbcTemplate
				.queryForObject(
						getCredentital,
						new LoginRowMapper(),
						email,1);
//		else
//			 return new LoginCredentials();
	}
	
	@Override
	public ElectionAdmin getStatus(String email) {
		
		String GET_STATUS = "SELECT * FROM admin WHERE emailId = ? AND status = ?";
		
		return jdbcTemplate.query(GET_STATUS,
				new ProfileRowMapper() ,
				email,1)
				.get(0);
	}

	
	//---------------------------------------------
	@Override
	public int voterApproval(int voterId, String access) {
		String UPDATE_STATUS = "UPDATE voter SET status = ? WHERE voterId = ?";
			return jdbcTemplate.update(UPDATE_STATUS,
				access.equalsIgnoreCase("grant")?1:0,
					voterId);
					}

	@Override
	public List<RegionalCommissioner> displayRegionalManagerList() {
		String showAllRegManager = "SELECT * FROM admin where status = ?";
		
		return jdbcTemplate.query(showAllRegManager,
				new RegionalCommissionerRowMapper(),
				1);
	}
	
	//---------------------------------------------
	
	
	@Override
	public int updateRegAdminDetails(ElectionAdmin electionAdmin) {
		String UPDATE_REGADMIN= "UPDATE admin SET userName=?,dob=?,"
				+ "nationality=?,"
				+ "id_proof=? where id=?";
		
		return jdbcTemplate.update(UPDATE_REGADMIN,
				electionAdmin.getUserName(),electionAdmin.getDateOfbirth(),
				electionAdmin.getNationality(),electionAdmin.getIdProof(),electionAdmin.getId()
				);
	}
	
	
	
public int addRegmanager(ElectionAdmin regManger) {
		
		String INSERT_REG_MANAGER="INSERT into admin "
				+ "(dob,nationality,id_proof,hashedPassword,salt,isAdmin,status,userName,emailId) "
				+ "value (?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(INSERT_REG_MANAGER,
				regManger.getDateOfbirth(),
				regManger.getNationality(),
				regManger.getIdProof(),
				regManger.getHashedPassword(),
				regManger.getSalt(),
				regManger.isAdmin(),
				true,
				regManger.getUserName(),
				regManger.getEmail());
	}

}
