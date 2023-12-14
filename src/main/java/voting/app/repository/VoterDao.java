package voting.app.repository;

import java.util.List;

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

public interface VoterDao {

	int registerVoter(Voter voter);

	int updateVoterDetails(Voter voter);

	int castVote(int voterId, int electionId);

	List<NewElection> displayElectionDetails(Voter voter);

	List<NewElection> displayElectionDetails();

	public LoginCredentials login(String userName);

	Voter displayVoter(String userName);

	// ---------------------------------------------

	List<Voter> displayAllVoterList();
	// ---------------------------------------------

	int updatePartyVoteCount(String candidateId, String elecName, String partyName, int electionId);

	List<VoteResult> displayPartyVoteCount(String electionId);

	List<Candidate> displayCandidateVoteCount(String electionId);

	List<NewElection> displayPastElectionDetails(Voter voter);

	List<NewElection> displayFutureElectionDetails(Voter voter);

	List<VoterStatsByAge> getVoterStatsByAge();

	List<VoterStatsbyConstitutionParticipation> getVoterStatsbyConstitutionParticipation(String electionId);

	List<VoterStatsByGenderParticipation> getVoterStatsByGenderParticipation(String electionId);

	List<VoterStatsByVotersParticipation> getVoterStatsByVotersParticipation(String electionId);

	List<VoterDetails> displayVoterDetails(int voterId);
	
	Voter getVoter(int voterId);
	

}
