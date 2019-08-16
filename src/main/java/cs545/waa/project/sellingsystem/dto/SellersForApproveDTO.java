package cs545.waa.project.sellingsystem.dto;

import cs545.waa.project.sellingsystem.domain.User;

public class SellersForApproveDTO {
	
	private User seller;
	
	private boolean isFollowee;
	
	public SellersForApproveDTO(User seller, boolean isFollowee) {
		this.seller = seller;
		this.isFollowee = isFollowee;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public boolean getIsFollowee() {
		return isFollowee;
	}

	public void setIsFollowee(boolean isFollowee) {
		this.isFollowee = isFollowee;
	}
}
