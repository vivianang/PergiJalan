package binus.skripsi.RatingWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import binus.skripsi.RatingWeb.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query(value = "SELECT * " + 
			"FROM TBL_CATEGORY " +  
			"ORDER BY NAME", nativeQuery = true)
	List<Category> getAllCategory();
}
