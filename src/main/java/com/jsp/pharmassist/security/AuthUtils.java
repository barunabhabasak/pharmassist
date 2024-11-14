package com.jsp.pharmassist.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jsp.pharmassist.entity.Admin;
import com.jsp.pharmassist.repository.AdminRepository;

@Component
public class AuthUtils {

	private final AdminRepository adminRepository;

	public AuthUtils(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}
	public boolean isAuthenticated() {
		return this.getAuthentication()!=null;
	}

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public String getCurrentUsername() {
		return this.getAuthentication().getName();

	}

	public Admin getCurrentAdmin() throws UsernameNotFoundException {

		return adminRepository.findByEmail(this.getCurrentUsername())
				.orElseThrow(() -> new UsernameNotFoundException("Failed to find user details"));
	}


}
