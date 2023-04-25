package com.app.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.blog.config.AppConstants;
import com.app.blog.entities.Role;
import com.app.blog.repos.RoleRepo;

import jakarta.validation.constraints.AssertFalse.List;

@SpringBootApplication
public class BloggingApplicationApisApplication implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BloggingApplicationApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));
		try {
			
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");
			
			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_NORMAL");
			
			java.util.List<Role> roles = java.util.List.of(role, role1);
			java.util.List<Role> result = this.roleRepo.saveAll(roles);
			result.forEach(res->{
				System.out.println(res.getName());
			});
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
