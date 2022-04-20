package cat.itb.m09.apirestarian.model.repositories;

import cat.itb.m09.apirestarian.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RepoUsers extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}