package voting.app.utilities;

import java.security.NoSuchAlgorithmException;

public class PasswordEncryptionWrapper {
	 private final PasswordEncryption passwordEncryption;

	    public PasswordEncryptionWrapper() {
	        this.passwordEncryption = new PasswordEncryption();
	    }

	    public String getHashedPassword(String password, String salt) throws NoSuchAlgorithmException {
	        return passwordEncryption.getHashedPassword(password, salt);
	    }
}
