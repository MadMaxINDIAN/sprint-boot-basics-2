package tech.namankhater.journal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tech.namankhater.journal.entity.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findByUserName(String userName);
}
