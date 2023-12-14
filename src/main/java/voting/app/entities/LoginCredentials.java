package voting.app.entities;

public class LoginCredentials {
	
	private String salt;
	private String hashedPassword;

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
	
	
	
	

	public LoginCredentials() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginCredentials(String salt, String hashedPassword) {
		super();
		this.salt = salt;
		this.hashedPassword = hashedPassword;
	}

	@Override
	public String toString() {
		return "LoginCredentials [salt=" + salt + ", hashedPassword=" + hashedPassword + "]";
	}

	
	

}
