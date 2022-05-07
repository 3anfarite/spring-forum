package org.example.again.services;

import org.example.again.Entities.Role;
import org.example.again.Entities.User;
import org.example.again.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceimpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	 

	// public UserService(UserRepository userRepository, BCryptPasswordEncoder
	// Encoder) {
	// this.userRepository = userRepository;
	// this.Encoder = Encoder;
	// }
	public User findUserByUserName(String username) {
		return userRepository.findByusername(username);
	}
	
	public Optional<User> findUserByuserName(String username) {
		return userRepository.findByUsername(username);
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByusername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));

	}

	private List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}


//	public User save (User user) {
//
//		user.setPassword(Encoder.encode(user.getPassword()));
////		Role userRole = new Role("USER");
//		Arrays.asList(new Role("ROLE_USER"));
////		roles.add(userRole);
////		user.setRoles(roles);
//		userRepository.save(user);
//		return userRepository.save(user);
//
//	}
	public User save(User reguser) {
		BCryptPasswordEncoder Encoder = new BCryptPasswordEncoder();
		User user = new User( reguser.getUsername(),
		reguser.getEmail(),Encoder.encode(reguser.getPassword()),
		Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}


	public User getById(int id) {
		return userRepository.findById(id).orElse(null);
	}
//    public User findUserById(int id) { 
//    	List<User> users = new ArrayList<>();
//    	User userf = null;
//    	users = userRepository.findAll();
//    	for (User user : users) {
//    		if (id == user.getId()) {
//    			user = userf;
//    		}
//    	} return userf;
//    }

	public void savePost(String content,User user) {
		userRepository.save(user);
	}
}
