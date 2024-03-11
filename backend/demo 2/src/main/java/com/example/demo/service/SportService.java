package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sport;
import com.example.demo.repository.SportRepository;

@Service
public class SportService {
    @Autowired
    private SportRepository sportRepository;

    public List<Sport> getSportsByUserId(Long userId) {
        List<Sport> allSports = sportRepository.findAll();
        List<Sport> userSports = allSports.stream()
                .filter(sp -> sp.getUser() != null && sp.getUser().getId().equals(userId))
                .collect(Collectors.toList());

        return userSports;
    }

    public List<Sport> getAllSports() {
        return sportRepository.findAll();
    }

    public Optional<Sport> getSportById(Long id) {
        return sportRepository.findById(id);
    }

    public Sport saveSport(Sport sport) {
        return sportRepository.save(sport);
    }

    public void deleteSportById(Long id) {
        sportRepository.deleteById(id);
    }

    public List<Sport> saveAllSports(List<Sport> sports) {
        return sportRepository.saveAll(sports);
    }

    public Sport updateSport(Long id, Sport updatedSport) {
        Optional<Sport> existingSportOptional = sportRepository.findById(id);
        if (existingSportOptional.isPresent()) {
            Sport existingSport = existingSportOptional.get();
            existingSport.setName(updatedSport.getName());
            // Update other fields if necessary
            return sportRepository.save(existingSport);
        } else {
            throw new RuntimeException("Sport not found with id: " + id);
        }
    }
}
