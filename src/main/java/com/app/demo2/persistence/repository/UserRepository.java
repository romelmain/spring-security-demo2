package com.app.demo2.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.demo2.persistence.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
