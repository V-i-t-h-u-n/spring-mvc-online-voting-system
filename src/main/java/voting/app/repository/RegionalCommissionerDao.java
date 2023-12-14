package voting.app.repository;

import java.util.List;

import voting.app.entities.Candidate;
import voting.app.entities.ElectionAdmin;
import voting.app.entities.LoginCredentials;
import voting.app.entities.NewElection;
import voting.app.entities.RegionalCommissioner;
import voting.app.entities.Voter;

public interface RegionalCommissionerDao {
	
	
	int candidateApproval(int candidateId, String access);
	boolean voterApproval(Voter voter);
	public int addElection(NewElection newElection);
	public LoginCredentials login(String  userName);
	ElectionAdmin getStatus(String userName);
	int voterApproval(int parseInt, String access);
	List<RegionalCommissioner> displayRegionalManagerList();
	int updateRegAdminDetails(ElectionAdmin electionAdmin);
	int addRegmanager(ElectionAdmin regManager);

//	int candidateApprovalPending(int candidate)


}
