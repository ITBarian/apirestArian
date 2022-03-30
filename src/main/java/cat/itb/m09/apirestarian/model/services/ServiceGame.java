package cat.itb.m09.apirestarian.model.services;

import cat.itb.m09.apirestarian.model.entities.GameItem;
import cat.itb.m09.apirestarian.model.repositories.RepoGameItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceGame {

    private final RepoGameItem repoGameItem;

    public List<GameItem> allGamesList(){
        return repoGameItem.findAll();
    }
    public GameItem addGameItem(GameItem gameItem) {
        return repoGameItem.save(gameItem);
    }
    public GameItem updateGameItem(long idGameItem, GameItem gameItem) { gameItem.setId(idGameItem); return repoGameItem.save(gameItem); }
    public Optional<GameItem> deleteGameItem(long idGameItem) {
        Optional<GameItem> todoList = repoGameItem.findById(idGameItem);
        repoGameItem.deleteById(idGameItem);
        return todoList;
    }

}
