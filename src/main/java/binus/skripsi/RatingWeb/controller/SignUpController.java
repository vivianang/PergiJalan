package binus.skripsi.RatingWeb.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import binus.skripsi.RatingWeb.model.User;
import binus.skripsi.RatingWeb.model.UserDetail;
import binus.skripsi.RatingWeb.service.LoginService;
import binus.skripsi.RatingWeb.service.SignUpService;

@Controller
@RequestMapping("")
public class SignUpController {

	@Autowired
	SignUpService signUpService;

	@GetMapping("/sign-up")
	public String registerPage(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("userDetail", new UserDetail());
		return "login/SignUp";
	}

	@PostMapping("/sign-up")
	public String submitUserForm(@ModelAttribute User user, @ModelAttribute UserDetail userDetail, Model model,
			RedirectAttributes redirectAttributes) {
		String role = "ROLE_USER";
		String errorMessage = signUpService.registerUser(user, userDetail, role);
		
		if ("UsernameExists".equals(errorMessage)) {
			model.addAttribute("errorMessage","Username sudah terdaftar");
			return "login/SignUp";
		}
		redirectAttributes.addFlashAttribute("signUpMessage", "User berhasil didaftarkan. Silahkan melakukan Sign In");
		return "redirect:/";
	}

}
