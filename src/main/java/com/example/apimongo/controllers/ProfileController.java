package com.example.apimongo.controllers;

import com.example.apimongo.exceptions.NoResultException;
import com.example.apimongo.models.Profile;
import com.example.apimongo.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<Profile> createProfile(@Valid @RequestBody Profile profile) {
        return ResponseEntity.ok(this.profileService.saveProfile(profile));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable String id, @Valid @RequestBody Profile profile) throws NoResultException {
        return ResponseEntity.ok(this.profileService.updateProfile(id, profile));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable String id) throws NoResultException {
        return ResponseEntity.ok(this.profileService.findProfileById(id));
    }

    @GetMapping("/list/{page}")
    public Map<String, Object> getProfile(@PathVariable Integer page) {
        return this.profileService.listProfiles(page);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProfile(@PathVariable String id) {
        this.profileService.deleteProfile(id);
    }

}
