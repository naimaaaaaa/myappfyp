package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DBRunner implements CommandLineRunner{
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private ProduceRepository produceRepository;
	
	@Autowired
	private ProduceSellerPriceRepository priceRepository;

	
	@Override
	 public void run(String... args) throws Exception {
		priceRepository.deleteAll();
		userRepository.deleteAll();
		produceRepository.deleteAll();
		
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User newUser = new User("alex", "alex@sample.com", encoder.encode("alex_pass"), UserType.SELLER);
		userRepository.save(newUser);
		
		Produce newProduce = new Produce("Apple");
		produceRepository.save(newProduce);
		
		ProduceSellerPrice newPSPrice = new ProduceSellerPrice();
		newPSPrice.setProduce(newProduce);
		newPSPrice.setSeller(newUser);
		newPSPrice.setPrice(new BigDecimal(1));
		priceRepository.save(newPSPrice);
		
		
		Iterable<ProduceSellerPrice> prices= priceRepository.findAll();
		prices.forEach((p)->{
			System.out.println(p);
		});
		
		
	}
}
