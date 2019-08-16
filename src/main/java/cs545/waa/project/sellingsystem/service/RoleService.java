package cs545.waa.project.sellingsystem.service;

import java.util.List;

import cs545.waa.project.sellingsystem.domain.Role;

public interface RoleService {

	List<Role> findRolesExceptAdmin();

}
