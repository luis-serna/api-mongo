package com.example.apimongo.repositories;

import com.example.apimongo.models.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, String> {

    Page<Profile> findAll(Pageable pageable);

}
