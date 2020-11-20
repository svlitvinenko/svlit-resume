package ru.svlit.core.user.adapter.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataSource extends MongoRepository<UserModel, String> {

    Optional<UserModel> findByUsername(String username);
}
