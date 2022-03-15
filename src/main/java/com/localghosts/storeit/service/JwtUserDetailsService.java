package com.localghosts.storeit.service;

import java.util.ArrayList;

import com.localghosts.storeit.config.BuyerRepo;
import com.localghosts.storeit.config.SellerRepo;
import com.localghosts.storeit.model.Buyer;
import com.localghosts.storeit.model.Seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private SellerRepo sellerRepo;

	@Autowired
	private BuyerRepo buyerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Buyer buyer = buyerRepo.findByEmail(username);
		if (buyer == null) {
			Seller seller = sellerRepo.findByEmail(username);
			if (seller == null) {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
			return new User(seller.getEmail(), seller.getPassword(), new ArrayList<>());
		}
		return new User(buyer.getEmail(), buyer.getPassword(), new ArrayList<>());
	}
}
