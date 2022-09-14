package com.jordan.service;
//package com.jordan.service;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.jordan.model.Roles;
//import com.jordan.model.User;
//import com.jordan.repository.UserRepository;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@SuppressWarnings("serial")
//@Service
//@Getter
//@Setter
//public class UserService implements UserDetailsService, UserDetails {
//
//	@Autowired
//	UserRepository repo;
//	private String username;
//	private String password;
//	private Set<Roles> roles;
//	private List<SimpleGrantedAuthority> authorities;
//
//	
//	public UserService() {
//		
//	}
//	
//	public UserService(User user) {
//		this.username = user.getUsername();
//		this.password = user.getPassword();
//		this.setRoles(user.getRoles());
//		List<SimpleGrantedAuthority> getAuthorities = new ArrayList<SimpleGrantedAuthority>();
//		roles.stream().forEach(role -> getAuthorities.add(new SimpleGrantedAuthority(role.getRoleName())));
//		authorities = getAuthorities;
//	}
//	
//	public void save(User user) {
//		repo.save(user);
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//		Optional<User> user = repo.findByUsername(username);
//		return user.map(UserService::new).orElseThrow(()-> new UsernameNotFoundException(username + "none"));
//	}
//	
//	
//	public List<User> getAllUsers() {
//		return repo.findAll();
//	}
//
//	public Optional<User> getUserById(int id) {
//		return repo.findById(id);
//	}
//	
//	public Optional<User> getUserByUsername(String username){
//		return repo.findByUsername(username);
//	}
//
//	public void deleteUser(int id) {
//		repo.deleteById(id);
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return this.password;
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return this.username;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	
//	public User findByUsername(String username) {
//		return repo.findByUsername(username).get();
//	}
//
//
//}
