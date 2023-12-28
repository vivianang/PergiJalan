package binus.skripsi.RatingWeb.service.util;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtil {
	private final int logRounds;

	public BCryptUtil(int logRounds) {
		this.logRounds = logRounds;
	}

	public String hash(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
	}

	public boolean verifyHash(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}
}
