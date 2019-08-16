package cs545.waa.project.sellingsystem.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Role {
    
	@Id
    @Column(name = "role_id")
    private Integer id;
    
	@NotEmpty(message = "*Choose a role")
    private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "Role : {" + id + ", " + role + "}";
	}
       
}
