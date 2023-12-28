package binus.skripsi.RatingWeb.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import binus.skripsi.RatingWeb.model.Category;
import binus.skripsi.RatingWeb.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getAllCategory() {

		return categoryRepository.getAllCategory();
	}
	
	public Category addCategory(Category category){
        return categoryRepository.save(category);
    }
	
	public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
            new ValidationException("Invalid Category id: " + id)
        );
    }
	
	public Category updateCategory(Category category) {
		Category categoryToUpdate = getCategoryById(category.getId());
        categoryToUpdate.setName(category.getName());
        return categoryRepository.saveAndFlush(categoryToUpdate);
    }
	
	public String deleteCategory(long id){
		Category categoryToDelete = getCategoryById(id);
		categoryRepository.delete(categoryToDelete);
        return "Success delete category";
    }
}
