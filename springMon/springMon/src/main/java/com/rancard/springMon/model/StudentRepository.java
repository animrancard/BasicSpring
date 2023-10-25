package com.rancard.springMon.model;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<StudentModel, String> {
    Optional<StudentModel> findStudentByEmail(String email);
}
