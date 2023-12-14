package voting.app.repository;

import java.sql.Blob;
import java.util.List;

import voting.app.entities.ElectionAdmin;
import voting.app.entities.ElectionCommissioner;
import voting.app.entities.LoginCredentials;
import voting.app.entities.NewElection;
import voting.app.entities.RegionalCommissioner;

public interface ElectionCommissionerDao {

	/*
	 * Roles of Election Commissioner 1. Can Add Election 2. Toggle access to sub
	 * commissioners 3. Announce Winners of each Election
	 */
	public LoginCredentials login(String userName);

	public int addElection(NewElection newElection);

	public List<ElectionAdmin> displayProfile(String userName);

	public int regManagerApproval(int parseInt, String access);

//	int addRegmanager(RegionalCommissioner regManger);

	public int setParty(int electionId, String partyName, Blob partyLogoImg,Blob partyManifesto);

	public List<NewElection> getAllElections();

	public List<NewElection> getAllActiveElections();

	public int updateEleCommDetails(ElectionAdmin elecCommAdmin);

	public int deleteElection(int parseInt);

	public List<NewElection> viewDeletedElection();

	public List<NewElection> viewDeleteElection();

//	public int addRegmanager(ElectionAdmin regManager);

	

}
