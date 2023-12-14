package voting.app.entities;

import java.sql.Blob;
import java.sql.Date;

public class ElectionAdmin {

	private int id;
	private String userName;
	private String email;
	private Date dateOfbirth;
	private String nationality;
	private Blob idProof;
	private String password;
	private String hashedPassword;
	private String salt;
	private boolean isAdmin = true;
	private boolean isLoggedIn = false;

	public ElectionAdmin() {
		super();
	}

	public ElectionAdmin(int id, String userName,String email, Date dateOfbirth, String nationality, Blob idProof, String password,
			String hashedPassword, String salt, boolean isAdmin, boolean isLoggedIn) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.dateOfbirth = dateOfbirth;
		this.nationality = nationality;
		this.idProof = idProof;
		this.password = password;
		this.hashedPassword = hashedPassword;
		this.salt = salt;
		this.isAdmin = isAdmin;
		this.isLoggedIn = isLoggedIn;
	}

	public ElectionAdmin(String userName,String email, Date dateOfbirth, String nationality, Blob idProof, String password,
			String hashedPassword, String salt, boolean isAdmin, boolean isLoggedIn) {
		super();
		this.userName = userName;
		this.email = email;
		this.dateOfbirth = dateOfbirth;
		this.nationality = nationality;
		this.idProof = idProof;
		this.password = password;
		this.hashedPassword = hashedPassword;
		this.salt = salt;
		this.isAdmin = isAdmin;
		this.isLoggedIn = isLoggedIn;
	}

	public ElectionAdmin(String password, String hashedPassword, String salt, boolean isAdmin) {
		super();
		this.password = password;
		this.hashedPassword = hashedPassword;
		this.salt = salt;
		this.isAdmin = isAdmin;
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

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ElectionAdmin [id=" + id + ", userName=" + userName + ", email=" + email + ", dateOfbirth="
				+ dateOfbirth + ", nationality=" + nationality + ", idProof=" + idProof + ", password=" + password
				+ ", hashedPassword=" + hashedPassword + ", salt=" + salt + ", isAdmin=" + isAdmin + ", isLoggedIn="
				+ isLoggedIn + "]";
	}

	

}
