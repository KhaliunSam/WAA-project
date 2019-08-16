package cs545.waa.project.sellingsystem.helper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cs545.waa.project.sellingsystem.domain.User;
import cs545.waa.project.sellingsystem.dto.FollowingSellersDTO;
import cs545.waa.project.sellingsystem.dto.SellersForApproveDTO;

public class BuyerControllerHelper {
	
	public static List<SellersForApproveDTO> convert(List<User> allSellers, List<User> mySellers) {
		
		if(allSellers == null || allSellers.size() == 0)
			return null;
		
		Set<String> mySellerEmails = (mySellers == null || mySellers.size() == 0) ?
				new HashSet<>() :
				mySellers.stream().map(s -> s.getEmail()).collect(Collectors.toSet());
		
		return allSellers.stream().map(s -> new SellersForApproveDTO(s, mySellerEmails.contains(s.getEmail()))).collect(Collectors.toList());
	}
	
	public static List<FollowingSellersDTO> convert(List<User> followees) {
		
		if(followees == null || followees.size() == 0)
			return null;
		
		return followees.stream().map(f -> new FollowingSellersDTO(f)).collect(Collectors.toList());
	}
}
