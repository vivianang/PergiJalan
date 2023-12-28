package binus.skripsi.RatingWeb.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;


 
@Controller
@RequestMapping("")
public class LoginController {
	
 
    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        return "login/login";
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model,Principal principal) {
    	
    	String path = "";
    	
    	if(principal!=null) {
    		User loginedUser = (User) ((Authentication) principal).getPrincipal();
    		path = getURL(loginedUser);
    	}else {
    		path = "login/login";
    	}
         
        return path;
    }
       
    
    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public String nextPage(Model model, Principal principal) {
         
    	String path = "";
    	
    	if(principal!=null) {
    		User loginedUser = (User) ((Authentication) principal).getPrincipal();
    		path = getURL(loginedUser);
    	}else {
    		path = "login/login";
    	}
         
        return path;
    }
     
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "redirect:/";
    }
     
 
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        return "login/login";
    }
     
    private String getURL(User user) {
    	User authUser = user;
    	String path = "";
    	Collection<GrantedAuthority> result = authUser.getAuthorities();
		
		for(GrantedAuthority auth : result) {
			if("ROLE_ADMIN".equals(auth.getAuthority())) {
				path="redirect:/admin/category-list";
			}else if("ROLE_USER".equals(auth.getAuthority())) {
				path="redirect:/user/home";
			}else {
				path="redirect:/admin/home";
			}
		}
		
		return path;
    }
 
}
