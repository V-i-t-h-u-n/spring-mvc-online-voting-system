package voting.app.repository;

import java.util.List;

import voting.app.entities.Candidate;
import voting.app.entities.Party;

public interface CandidateDao {
	
	int registerCandidate(Candidate candidate);
	List<Candidate> displayCandidateList();
	List<Candidate> displayCandidateListRegional();
	List<Candidate> displayCandidateListVoter(String electionName);
	int updateVote(int candidateId);
	int insertStatusTable(int candidateId);
	List<Candidate> displayCandidateListbyConstitution(String constitution
									, int electId);
	List<Party> getAllParties();
	int updateCandidateVoteCount(String candidateId, int electId,String partyName);
	boolean isEmailInDatabase(String email);
	Candidate getCandidate(String email);
	int updateCandidate(Candidate candidate);
}
