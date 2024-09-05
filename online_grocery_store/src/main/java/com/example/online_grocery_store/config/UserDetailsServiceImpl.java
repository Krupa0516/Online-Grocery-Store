package com.example.online_grocery_store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.online_grocery_store.Entity.UserDtls;
import com.example.online_grocery_store.Repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserDtls user = userRepo.findByEmail(email);

		if (user != null) {
			return new CustomUserDetails(user);
		}

		throw new UsernameNotFoundException("user not available");
	}

}
