package voting.app.entities;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Date;

public class VoterDetails {
	private String voterName;
	private Date voterDateOfBirth;
	private String constitution;
	private Blob voterProfilePic; 
	private boolean status;
	private String electionName;
	private Date electionStartDate;
	private LocalDateTime currentDateTime;


	
	public VoterDetails() {
	}

	public VoterDetails(String voterName, Date voterDateOfBirth, String constitution, Blob voterProfilePic,
			boolean status, String electionName, Date electionStartDate,LocalDateTime currentDateTime) {
		this.voterName = voterName;
		this.voterDateOfBirth = voterDateOfBirth;
		this.constitution = constitution;
		this.voterProfilePic = voterProfilePic;
		this.status = status;
		this.electionName = electionName;
		this.electionStartDate = electionStartDate;
		this.currentDateTime = currentDateTime;
	}

	public String getVoterName() {
		return voterName;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	public Date getVoterDateOfBirth() {
		return voterDateOfBirth;
	}

	public void setVoterDateOfBirth(Date voterDateOfBirth) {
		this.voterDateOfBirth = voterDateOfBirth;
	}

	public String getConstitution() {
		return constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public Blob getVoterProfilePic() {
		return voterProfilePic;
	}

	public void setVoterProfilePic(Blob voterProfilePic) {
		this.voterProfilePic = voterProfilePic;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getElectionName() {
		return electionName;
	}

	public void setElectionName(String electionName) {
		this.electionName = electionName;
	}

	public Date getElectionStartDate() {
		return electionStartDate;
	}

	public void setElectionStartDate(Date electionStartDate) {
		this.electionStartDate = electionStartDate;
	}

	public LocalDateTime getCurrentDateTime() {
		return currentDateTime;
	}

	public void setCurrentDateTime(LocalDateTime currentDateTime) {
		this.currentDateTime = currentDateTime;
	}

	@Override
	public String toString() {
		return "VoterDetails [voterName=" + voterName + ", voterDateOfBirth=" + voterDateOfBirth + ", constitution="
				+ constitution + ", voterProfilePic=" + voterProfilePic + ", status=" + status + ", electionName="
				+ electionName + ", electionStartDate=" + electionStartDate + ", currentDateTime=" + currentDateTime
				+ "]";
	}
	
	
	
}
