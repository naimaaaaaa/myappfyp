package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Hobby;
import com.example.demo.repository.HobbyRepository;

@Service
public class HobbyService {
    @Autowired
    private HobbyRepository hobbyRepository;

    public List<Hobby> getHobbiesByUserId(Long userId) {
        List<Hobby> allHobbies = hobbyRepository.findAll();
        List<Hobby> userHobbies = allHobbies.stream()
                .filter(hobby -> hobby.getUser() != null && hobby.getUser().getId().equals(userId))
                .collect(Collectors.toList());


        return userHobbies;
    }

    public List<Hobby> getAllHobbies() {
        return hobbyRepository.findAll();
    }

    public Optional<Hobby> getHobbyById(Long id) {
        return hobbyRepository.findById(id);
    }

    public Hobby saveHobby(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    public void deleteHobbyById(Long id) {
        hobbyRepository.deleteById(id);
    }

    public List<Hobby> saveAllHobbies(List<Hobby> hobbies) {
        return hobbyRepository.saveAll(hobbies);
    }

    public Hobby updateHobby(Long id, Hobby updatedHobby) {
        Optional<Hobby> existingHobbyOptional = hobbyRepository.findById(id);
        if (existingHobbyOptional.isPresent()) {
            Hobby existingHobby = existingHobbyOptional.get();
            existingHobby.setName(updatedHobby.getName());
            // Update other fields if necessary
            return hobbyRepository.save(existingHobby);
        } else {
            throw new RuntimeException("Hobby not found with id: " + id);
        }
    }
}
