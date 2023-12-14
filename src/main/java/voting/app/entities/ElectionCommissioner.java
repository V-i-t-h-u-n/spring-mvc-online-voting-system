package voting.app.entities;

import java.sql.Blob;
import java.sql.Date;

public class ElectionCommissioner {

	private int id;
	private String userName;
	private Date dateOfbirth;
	private String nationality;
	private Blob idProof;
	private String password;
	private String hashedPassword;
	private String salt;
	private boolean isAdmin;
	private boolean isLoggedIn;

	public ElectionCommissioner() {
		super();
	}

	public ElectionCommissioner(String password, String hashedPassword, String salt, boolean isLoggedIn) {
		super();
		this.password = password;
		this.hashedPassword = hashedPassword;
		this.salt = salt;
		this.isLoggedIn = isLoggedIn;
	}

	public ElectionCommissioner(int id, String userName, Date dateOfbirth, String nationality, Blob idProof,
			String password, String hashedPassword, String salt, boolean isAdmim, boolean isLoggedIn) {
		super();
		this.id = id;
		this.userName = userName;
		this.dateOfbirth = dateOfbirth;
		this.nationality = nationality;
		this.idProof = idProof;
		this.password = password;
		this.hashedPassword = hashedPassword;
		this.salt = salt;
		this.isAdmin = isAdmim;
		this.isLoggedIn = isLoggedIn;
	}

	public ElectionCommissioner(String userName, Date dateOfbirth, String nationality, Blob idProof, String password,
			String hashedPassword, String salt, boolean isAdmim, boolean isLoggedIn) {
		super();
		this.userName = userName;
		this.dateOfbirth = dateOfbirth;
		this.nationality = nationality;
		this.idProof = idProof;
		this.password = password;
		this.hashedPassword = hashedPassword;
		this.salt = salt;
		this.isAdmin = isAdmim;
		this.isLoggedIn = isLoggedIn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDateOfbirth() {
		return dateOfbirth;
	}

	public void setDateOfbirth(Date dateOfbirth) {
		this.dateOfbirth = dateOfbirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Blob getIdProof() {
		return idProof;
	}

	public void setIdProof(Blob idProof) {
		this.idProof = idProof;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmim) {
		this.isAdmin = isAdmim;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	@Override
	public String toString() {
		return "ElectionCommissioner \n id=" + id + "\n userName=" + userName + "\n dateOfbirth=" + dateOfbirth
				+ "\n nationality=" + nationality;
	}

}
