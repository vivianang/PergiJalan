package binus.skripsi.RatingWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import binus.skripsi.RatingWeb.model.UserDetail;
import binus.skripsi.RatingWeb.repository.UserDetailRepository;

@Service
public class UserAdminService {

	@Autowired
	UserDetailRepository userDetailRepository;
	
	public List<UserDetail> getAllUserAdmin() {

		return userDetailRepository.getAdminUserDetail();
	}
}
