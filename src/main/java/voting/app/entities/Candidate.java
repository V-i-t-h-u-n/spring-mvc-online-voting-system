package voting.app.entities;

import java.sql.Blob;
import java.sql.Date;

public class Candidate {

	private int candidateId;
	private String candidateName;
	private Date candidateDob;
	private String partyName;
	private Blob partyManifesto;
	private Blob partyLogo;
	private Blob idProof;
	private int electionId;
	private String electionName;
	private boolean status;
	private String constitution;
	private int voteCount = 0;
	private String email;
	private Blob candidateProfilePic;

	public Candidate() {
		super();
	}

	public Candidate(int candidateId, String candidateName, Date candidateDob, String partyName, Blob partyManifesto,
			Blob partyLogo, Blob idProof, int electionId, String electionName, boolean status, String constitution,
			int voteCount, String email, Blob candidateProfilePic) {
		super();
		this.candidateId = candidateId;
		this.candidateName = candidateName;
		this.candidateDob = candidateDob;
		this.partyName = partyName;
		this.partyManifesto = partyManifesto;
		this.partyLogo = partyLogo;
		this.idProof = idProof;
		this.electionId = electionId;
		this.status = status;
		this.constitution = constitution;
		this.voteCount = voteCount;
		this.electionName = electionName;
		this.email = email;
		this.candidateProfilePic = candidateProfilePic;
	}

	public Candidate(String candidateName, Date candidateDob, String partyName, Blob partyManifesto, Blob partyLogo,
			Blob idProof, int electionId, String electionName, boolean status, String constitution, int voteCount,
			String email, Blob candidateProfilePic) {
		super();
		this.candidateName = candidateName;
		this.candidateDob = candidateDob;
		this.partyName = partyName;
		this.partyManifesto = partyManifesto;
		this.partyLogo = partyLogo;
		this.idProof = idProof;
		this.electionId = electionId;
		this.status = status;
		this.constitution = constitution;
		this.voteCount = voteCount;
		this.electionName = electionName;
		this.email = email;
		this.candidateProfilePic = candidateProfilePic;

	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public Date getCandidateDob() {
		return candidateDob;
	}

	public void setCandidateDob(Date candidateDob) {
		this.candidateDob = candidateDob;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Blob getPartyManifesto() {
		return partyManifesto;
	}

	public void setPartyManifesto(Blob partyManifesto) {
		this.partyManifesto = partyManifesto;
	}

	public Blob getPartyLogo() {
		return partyLogo;
	}

	public void setPartyLogo(Blob partyLogo) {
		this.partyLogo = partyLogo;
	}

	public Blob getIdProof() {
		return idProof;
	}

	public void setIdProof(Blob idProof) {
		this.idProof = idProof;
	}

	public int getElectionId() {
		return electionId;
	}

	public void setElectionId(int electionId) {
		this.electionId = electionId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getConstitution() {
		return constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public String getElectionName() {
		return electionName;
	}

	public void setElectionName(String electionName) {
		this.electionName = electionName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Blob getCandidateProfilePic() {
		return candidateProfilePic;
	}

	public void setCandidateProfilePic(Blob candidateProfilePic) {
		this.candidateProfilePic = candidateProfilePic;
	}

	
	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", candidateName=" + candidateName + ", candidateDob="
				+ candidateDob + ", partyName=" + partyName + ", partyManifesto=" + partyManifesto + ", partyLogo="
				+ partyLogo + ", idProof=" + idProof + ", electionId=" + electionId + ", electionName=" + electionName
				+ ", status=" + status + ", constitution=" + constitution + ", voteCount=" + voteCount + ", email="
				+ email + ", candidateProfilePic=" + candidateProfilePic + "]";
	}

	

}
