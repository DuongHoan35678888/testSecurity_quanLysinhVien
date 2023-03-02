package com.example.chuongtrinhquanlysinhvien.main;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
   public User findDistinctById(Long id);

}
