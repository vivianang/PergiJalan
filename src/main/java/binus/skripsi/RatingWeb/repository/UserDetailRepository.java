package binus.skripsi.RatingWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import binus.skripsi.RatingWeb.model.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long>{
	@Query(value = "SELECT * " + 
			"FROM tbl_user_detail ud " +  
			"WHERE user_role_id = (select id from tbl_user_role where name = 'ROLE_ADMIN')", nativeQuery = true)
	List<UserDetail> getAdminUserDetail();
	
	@Query(value = "select ur.name " + 
			"from tbl_user_detail ud " + 
			"inner join tbl_user_role ur on ur.id = ud.user_role_id " +
			"where ud.user_id = :userId ", nativeQuery = true)
	List<String> getRoleNames(@Param("userId") long userId);
}
