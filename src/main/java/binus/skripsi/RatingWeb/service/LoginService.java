package binus.skripsi.RatingWeb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import binus.skripsi.RatingWeb.repository.UserDetailRepository;
import binus.skripsi.RatingWeb.repository.UserRepository;
 
@Service
public class LoginService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserDetailRepository userDetailRepository;
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

    	binus.skripsi.RatingWeb.model.User user = userRepository.getUserByUsername2(userName);
        System.out.println("coba-coba : "+user);
 
        if (user == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
 
        System.out.println("Found User: " + user);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleName = userDetailRepository.getRoleNames(user.getId());
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleName != null) {
            for (String role : roleName) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(user.getUsername(),
        		user.getPassword(), grantList);
 
        return userDetails;
    }
 
}