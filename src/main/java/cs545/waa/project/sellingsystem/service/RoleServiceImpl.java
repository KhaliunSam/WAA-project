package cs545.waa.project.sellingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs545.waa.project.sellingsystem.domain.Role;
import cs545.waa.project.sellingsystem.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	public List<Role> findRolesExceptAdmin() {

		return roleRepository.findByRoleNot("ROLE_ADMIN");
	}

}
