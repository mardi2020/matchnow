package com.example.matchnow.user;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Query(value="UPDATE matchnow.users SET matchnow.users.modified_date=?2 WHERE matchnow.users.email=?1", nativeQuery = true)
    void updateLoginDate(String email, String date);

    Optional<User> findByUsername(String username);
}
