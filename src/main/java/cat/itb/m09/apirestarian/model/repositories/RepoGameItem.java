package cat.itb.m09.apirestarian.model.repositories;

import cat.itb.m09.apirestarian.model.entities.GameItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoGameItem extends JpaRepository<GameItem, Long> {



}
