package binus.skripsi.RatingWeb.service;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import binus.skripsi.RatingWeb.model.User;
import binus.skripsi.RatingWeb.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User getDetailUser(String username) {

		return userRepository.getUserByUsername2(username); 
	}
	
	public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() ->
            new ValidationException("Invalid User id: " + id)
        );
    }
}
