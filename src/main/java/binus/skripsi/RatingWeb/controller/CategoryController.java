package binus.skripsi.RatingWeb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import binus.skripsi.RatingWeb.model.Category;
import binus.skripsi.RatingWeb.service.CategoryService;

@Controller
@RequestMapping("/admin")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/category-list")
	public String viewCategory(Model model) {
		List<Category> result = categoryService.getAllCategory();
		model.addAttribute("category", result);
		return "admin/CategoryList";
	}
	
	@GetMapping("/add-category")
	public String viewAddCategory(Model model) {
		model.addAttribute("category", new Category());
		return "admin/AddCategory";
	}
	
	@PostMapping("/add-category")
	public String addCategory(@Valid Category category) {
		categoryService.addCategory(category);
//		model.addAttribute("message", new "Data Kategori berhasil ditambahkan!!!");
		return "redirect:/admin/category-list?add=true";
	}
	
	@RequestMapping("delete-category/{id}")
	public String deleteCategory(@PathVariable("id") long id, Model model) {
		
		try {
//			Batch batch = batchRepository.findById(id)
//			.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//	batchRepository.delete(batch);
			categoryService.deleteCategory(id);
			return "redirect:/admin/category-list?delete=true";
		} catch (Exception ex) {
			return "redirect:/admin/category-lists";
		}
	}
	
	@RequestMapping("update-category/{id}")
	public String updateBatch(@PathVariable("id") long id, Model model) {
		Category category = categoryService.getCategoryById(id);
		model.addAttribute("category", category);
		return "admin/UpdateCategory";
	}
	
	@PostMapping("update-category")
	public String updateTrainee(@Valid Category category) {
		categoryService.updateCategory(category);
		return "redirect:/admin/category-list?update=true";
	}
	
	@RequestMapping("/dashboard")
	public String viewCategoryaa() {
		
		return "admin/Dashboard";
	}
	
	@RequestMapping("/buttons")
	public String viewCategoryaaaa() {
		
		return "admin/buttons";
	}
}
