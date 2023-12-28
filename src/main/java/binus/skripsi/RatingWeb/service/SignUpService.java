package binus.skripsi.RatingWeb.service;

import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import binus.skripsi.RatingWeb.model.User;
import binus.skripsi.RatingWeb.model.UserDetail;
import binus.skripsi.RatingWeb.model.UserRole;
import binus.skripsi.RatingWeb.repository.UserDetailRepository;
import binus.skripsi.RatingWeb.repository.UserRepository;
import binus.skripsi.RatingWeb.repository.UserRoleRepository;
import binus.skripsi.RatingWeb.service.util.BCryptUtil;

@Service
public class SignUpService {

	private final BCryptUtil passwordUtil = new BCryptUtil(10);
	
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserDetailRepository userDetailRepository;
//	@Autowired
//	PasswordEncoder passwordEncoder;

	public String registerUser(User user, UserDetail userDetail, String role) {
		Optional<User> existingUser = userRepository.getUserByUsername(user.getUsername());
	    
	    if(existingUser.isPresent()){
	    	return "UsernameExists";
	    }
		
		UserRole userRole = userRoleRepository.findByName(role);
		
		user.setPassword(getEncryptedPassword(user.getPassword()));
		userRepository.save(user);

		userDetail.setUser(user);
		userDetail.setUserRole(userRole);
		userDetailRepository.save(userDetail);

		return "Success";
	}
	
	private String getEncryptedPassword(String password) {
		return passwordUtil.hash(password);
	}
	
	private String loginUser(User user) {
		User existingUser = getUserByUsername(user.getUsername());

	    boolean valid = passwordUtil.verifyHash(user.getPassword(), existingUser.getPassword());

	    if(!valid) {
	      throw new ValidationException("Invalid user password");
	    }
		
		return "";
	}
	
	private User getUserByUsername(String uDomain) {
	    return userRepository.getUserByUsername(uDomain)
	      .orElseThrow(() -> new ValidationException("Invalid user domain: " + uDomain));
	  }
}
