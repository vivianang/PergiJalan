package binus.skripsi.RatingWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import binus.skripsi.RatingWeb.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM tbl_user WHERE username = :username", nativeQuery = true)
	Optional<User> getUserByUsername(@Param("username") String username);

	@Query(value = "SELECT * FROM tbl_user WHERE username = :username", nativeQuery = true)
	User getUserByUsername2(@Param("username") String username);
}
