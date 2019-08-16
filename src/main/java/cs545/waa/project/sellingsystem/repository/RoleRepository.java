package cs545.waa.project.sellingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cs545.waa.project.sellingsystem.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
	Role findByRole(String role);
	
	
	List<Role> findByRoleNot(String admin);
}
