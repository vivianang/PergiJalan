package binus.skripsi.RatingWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import binus.skripsi.RatingWeb.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	@Query(value = "SELECT * FROM TBL_USER_ROLE WHERE NAME = :roleName", nativeQuery = true)
	UserRole findByName(String roleName);
}
