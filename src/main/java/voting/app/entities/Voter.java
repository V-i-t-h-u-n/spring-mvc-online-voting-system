package voting.app.entities;

import java.sql.Blob;
import java.sql.Date;
import java.util.Arrays;

public class Voter {

	private int voterId;
	private String voterName;
	private String voterEmail;
	private String voterPhoneNumber;
	private Date voterDateOfBirth;
	private String voterGender;
	private String voterNationality;
	private Blob voterIdProof;
	private Blob voterProfilePic;
	private String salt;
	private String hashedPassword;
	private String voterAadharNo;
	private boolean status;
	private String constitution;

	public Voter() {
		super();
	}

	public Voter(int voterId, String voterName, String voterEmail, String voterPhoneNumber, Date voterDateOfBirth,
			String voterGender, String voterNationality, Blob voterIdProof, Blob voterProfilePic, String salt,
			String hashedPassword, String voterAadharNo, boolean status,String constitution) {
		super();
		this.voterId = voterId;
		this.voterName = voterName;
		this.voterEmail = voterEmail;
		this.voterPhoneNumber = voterPhoneNumber;
		this.voterDateOfBirth = voterDateOfBirth;
		this.voterGender = voterGender;
		this.voterNationality = voterNationality;
		this.voterIdProof = voterIdProof;
		this.voterProfilePic = voterProfilePic;
		this.salt = salt;
		this.hashedPassword = hashedPassword;
		this.voterAadharNo = voterAadharNo;
		this.status = status;
		this.constitution = constitution;
	}

	public Voter(String voterName, String voterEmail, String voterPhoneNumber, Date voterDateOfBirth,
			String voterGender, String voterNationality, Blob voterIdProof, Blob voterProfilePic, String salt,
			String hashedPassword, String voterAadharNo, boolean status,String constitution) {
		super();
		this.voterName = voterName;
		this.voterEmail = voterEmail;
		this.voterPhoneNumber = voterPhoneNumber;
		this.voterDateOfBirth = voterDateOfBirth;
		this.voterGender = voterGender;
		this.voterNationality = voterNationality;
		this.voterIdProof = voterIdProof;
		this.voterProfilePic = voterProfilePic;
		this.salt = salt;
		this.hashedPassword = hashedPassword;
		this.voterAadharNo = voterAadharNo;
		this.status = status;
		this.constitution = constitution;

	}

	public int getVoterId() {
		return voterId;
	}

	public void setVoterId(int voterId) {
		this.voterId = voterId;
	}

	public String getVoterName() {
		return voterName;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	public String getVoterEmail() {
		return voterEmail;
	}

	public void setVoterEmail(String voterEmail) {
		this.voterEmail = voterEmail;
	}

	public String getVoterPhoneNumber() {
		return voterPhoneNumber;
	}

	public void setVoterPhoneNumber(String voterPhoneNumber) {
		this.voterPhoneNumber = voterPhoneNumber;
	}

	public Date getVoterDateOfBirth() {
		return voterDateOfBirth;
	}

	public void setVoterDateOfBirth(Date voterDateOfBirth) {
		this.voterDateOfBirth = voterDateOfBirth;
	}

	public String getVoterGender() {
		return voterGender;
	}

	public void setVoterGender(String voterGender) {
		this.voterGender = voterGender;
	}

	public String getVoterNationality() {
		return voterNationality;
	}

	public void setVoterNationality(String voterNationality) {
		this.voterNationality = voterNationality;
	}

	public Blob getVoterIdProof() {
		return voterIdProof;
	}

	public void setVoterIdProof(Blob voterIdProof) {
		this.voterIdProof = voterIdProof;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public Blob getVoterProfilePic() {
		return voterProfilePic;
	}

	public void setVoterProfilePic(Blob voterProfilePic) {
		this.voterProfilePic = voterProfilePic;
	}

	public String getVoterAadharNo() {
		return voterAadharNo;
	}

	public void setVoterAadharNo(String voterAadharNo) {
		this.voterAadharNo = voterAadharNo;
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

	@Override
	public String toString() {
		return "Voter [voterId=" + voterId + ", voterName=" + voterName + ", voterEmail=" + voterEmail
				+ ", voterPhoneNumber=" + voterPhoneNumber + ", voterDateOfBirth=" + voterDateOfBirth + ", voterGender="
				+ voterGender + ", voterNationality=" + voterNationality + ", voterIdProof=" + voterIdProof
				+ ", voterProfilePic=" + voterProfilePic + ", salt=" + salt + ", hashedPassword=" + hashedPassword
				+ ", voterAadharNo=" + voterAadharNo + ", status=" + status + ", constitution=" + constitution + "]";
	}

	public Voter(int voterId, String voterName, String voterEmail, String voterPhoneNumber, Date voterDateOfBirth,
			String voterGender, String voterNationality, String salt, String hashedPassword, String voterAadharNo,
			boolean status, String constitution) {
		super();
		this.voterId = voterId;
		this.voterName = voterName;
		this.voterEmail = voterEmail;
		this.voterPhoneNumber = voterPhoneNumber;
		this.voterDateOfBirth = voterDateOfBirth;
		this.voterGender = voterGender;
		this.voterNationality = voterNationality;
		this.salt = salt;
		this.hashedPassword = hashedPassword;
		this.voterAadharNo = voterAadharNo;
		this.status = status;
		this.constitution = constitution;
	}

	
	

}
