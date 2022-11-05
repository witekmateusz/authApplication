package com.example.auth.repository;

import com.example.auth.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserAccountRepo extends JpaRepository<UserAccount, Long> {
}
