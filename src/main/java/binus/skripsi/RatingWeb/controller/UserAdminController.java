package binus.skripsi.RatingWeb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import binus.skripsi.RatingWeb.model.Category;
import binus.skripsi.RatingWeb.model.User;
import binus.skripsi.RatingWeb.model.UserDetail;
import binus.skripsi.RatingWeb.service.UserAdminService;
import binus.skripsi.RatingWeb.service.CategoryService;
import binus.skripsi.RatingWeb.service.LoginService;
import binus.skripsi.RatingWeb.service.SignUpService;

@Controller
@RequestMapping("/admin")
public class UserAdminController {
	
	@Autowired
	UserAdminService userAdminService;
	@Autowired
	SignUpService signUpService;
	
	@RequestMapping("/user-admin-list")
	public String viewUserAdmin(Model model) {
		List<UserDetail> result = userAdminService.getAllUserAdmin();
		model.addAttribute("adminUserDetail", result);
		return "admin/UserAdminList";
	}
	
	@GetMapping("/add-user-admin")
	public String viewAddUserAdmin(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("userDetail", new UserDetail());
		return "admin/AddUserAdmin";
	}
	
	@PostMapping("/add-user-admin")
	public String addUserAdmin(User user, UserDetail userDetail, Model model) {
		String role = "ROLE_ADMIN";
		String errorMessage = signUpService.registerUser(user, userDetail, role);
		
		if ("UsernameExists".equals(errorMessage)) {
			model.addAttribute("errorMessage","Username sudah terdaftar");
			return "login/SignUp";
		}
		
		return "redirect:/success";
	}
//	
//	@RequestMapping("delete-category/{id}")
//	public String deleteCategory(@PathVariable("id") long id, Model model) {
//		
//		try {
////			Batch batch = batchRepository.findById(id)
////			.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
////	batchRepository.delete(batch);
//			categoryService.deleteCategory(id);
//			return "redirect:/admin/category-list";
//		} catch (Exception ex) {
//			System.out.println("adsadas");
//			return "redirect:/admin/category-lists";
//		}
//	}
//	
//	@RequestMapping("update-category/{id}")
//	public String updateBatch(@PathVariable("id") long id, Model model) {
//		Category category = categoryService.getCategoryById(id);
//		model.addAttribute("category", category);
//		return "admin/UpdateCategory";
//	}
//	
//	@PostMapping("update-category")
//	public String updateTrainee(@Valid Category trainee) {
//		categoryService.updateMember(trainee);
//		return "redirect:/admin/category-list";
//	}
//	
//	@RequestMapping("/dashboard")
//	public String viewCategoryaa() {
//		
//		return "admin/Dashboard";
//	}
//	
//	@RequestMapping("/buttons")
//	public String viewCategoryaaaa() {
//		
//		return "admin/buttons";
//	}
}
