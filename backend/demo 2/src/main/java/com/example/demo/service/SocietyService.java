package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Society;
import com.example.demo.repository.SocietyRepository;

@Service
public class SocietyService {
    @Autowired
    private SocietyRepository societyRepository ;

    public List<Society> getSocietiesByUserId(Long userId) {
        return societyRepository.findByUserId(userId);
    }

    public List<Society> getAllSocieties() {
        return societyRepository.findAll();
    }

    public Optional<Society> getSocietyById(Long id) {
        return societyRepository.findById(id);
    }

    public List<Society> saveAllSocieties(List<Society> societies) {
        return societyRepository.saveAll(societies);
    }

    public void deleteSocietyById(Long id) {
        societyRepository.deleteById(id);
    }

    public Society updateSociety(Long id, Society updatedSociety) {
        Optional<Society> existingSocietyOptional = societyRepository.findById(id);
        if (existingSocietyOptional.isPresent()) {
            Society existingSociety = existingSocietyOptional.get();
            existingSociety.setName(updatedSociety.getName());
            // Update other fields if necessary
            return societyRepository.save(existingSociety);
        } else {
            throw new RuntimeException("Society not found with id: " + id);
        }
    }
}
