package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ProduceSellerPrice;
import com.example.demo.model.User;
import com.example.demo.repository.ProduceSellerPriceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.UserType;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class UserService {
	@Autowired
    UserRepository userRepository;
	
	@Autowired
	private ProduceSellerPriceRepository priceRepository;
	
	public UserService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public List<User> getUsers() {
		return (List<User>) userRepository.findAll();
	}

	
	public void addUser(User newUser) {
		userRepository.save(newUser);
	}
	
	public Optional<User> findByID(Long id) {
		 return userRepository.findById(id);
	}
	
	@Transactional
	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		
		
		if (user.getUserType() == UserType.SELLER || user.getUserType() == UserType.BOTH) {
			Iterable<ProduceSellerPrice> deleted_prices= priceRepository.deleteBySeller(user);
			deleted_prices.forEach((p)->{
				System.out.println(p);
			});
		}
		
		userRepository.delete(user);
	} 
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
