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
import binus.skripsi.RatingWeb.service.CheckStatusService;
import binus.skripsi.RatingWeb.service.LoginService;
import binus.skripsi.RatingWeb.service.SignUpService;

@Controller
@RequestMapping("/admin")
public class CheckStatusController {
	
	@Autowired
	CheckStatusService checkStatusService;
	
	@RequestMapping("/check-status")
	public String viewCheckStatus(Model model) {
		return "admin/UserAdminList";
	}
}
