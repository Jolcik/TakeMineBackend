package com.pkostrzenski.takemine.repository.jpa;

import com.pkostrzenski.takemine.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Integer id);

    @Query(
            value = "SELECT u.* " +
                    "FROM takemine.user u " +
                    "WHERE u.username = :username OR u.email = :username",
            nativeQuery = true
    )
    Optional<User> findByUsernameOrEmail(@Param("username") String username);
}
