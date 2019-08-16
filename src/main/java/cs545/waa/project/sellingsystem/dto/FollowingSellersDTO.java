package cs545.waa.project.sellingsystem.dto;

import cs545.waa.project.sellingsystem.domain.User;

public class FollowingSellersDTO {
	
	private User followee;
	
	public FollowingSellersDTO(User followee) {
		this.followee = followee;
	}

	public User getFollowee() {
		return followee;
	}

	public void setFollowee(User followee) {
		this.followee = followee;
	}

	public Integer getProductSize() {
		return (followee.getProducts()== null || followee.getProducts().size() == 0) ?
				0 : followee.getProducts().size();
	}

}
