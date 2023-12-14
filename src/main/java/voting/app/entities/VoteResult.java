package voting.app.entities;

import java.sql.Blob;

public class VoteResult {

	
	private String partyName;
	private String electionName;
	private String constitution;
	private int votes;
	private Blob partyLogo;
	
	public VoteResult() {
		super();
	}
	public String getpartyName() {
		return partyName;
	}
	public void setpartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getElectionName() {
		return electionName;
	}
	public void setElectionName(String electionName) {
		this.electionName = electionName;
	}
	public String getConstitution() {
		return constitution;
	}
	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Blob getPartyLogo() {
		return partyLogo;
	}
	public void setPartyLogo(Blob partyLogo) {
		this.partyLogo = partyLogo;
	}
	
	
	
	
	
}
