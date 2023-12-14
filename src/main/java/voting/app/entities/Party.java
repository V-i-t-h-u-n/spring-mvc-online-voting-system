package voting.app.entities;

import java.sql.Blob;

public class Party {
	
	private String partyName;
	private int electionId;
	private int voteCount;
	private Blob partyLogo;
	private Blob partyManifesto;
	
	
	public Party(String partyName, int electionId, int voteCount, Blob partyLogo,Blob partyManifesto) {
		super();
		this.partyName = partyName;
		this.electionId = electionId;
		this.voteCount = voteCount;
		this.partyLogo = partyLogo;
		this.partyManifesto = partyManifesto;
	}
	public Party() {
		super();
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public int getElectionId() {
		return electionId;
	}
	public void setElectionId(int electionId) {
		this.electionId = electionId;
	}
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	public Blob getPartyLogo() {
		return partyLogo;
	}
	public void setPartyLogo(Blob partyLogo) {
		this.partyLogo = partyLogo;
	}
	
	public Blob getPartyManifesto() {
		return partyManifesto;
	}
	public void setPartyManifesto(Blob partyManifesto) {
		this.partyManifesto = partyManifesto;
	}
	@Override
	public String toString() {
		return "Party [partyName=" + partyName + ", electionId=" + electionId + ", voteCount=" + voteCount
				+ ", partyLogo=" + partyLogo + ", partyManifesto=" + partyManifesto + "]";
	}
	
}
