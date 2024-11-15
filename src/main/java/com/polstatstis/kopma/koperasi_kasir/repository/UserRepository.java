package com.polstatstis.kopma.koperasi_kasir.repository;

import com.polstatstis.kopma.koperasi_kasir.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}
