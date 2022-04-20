package cat.itb.m09.apirestarian.controller;

import cat.itb.m09.apirestarian.model.entities.GameItem;
import cat.itb.m09.apirestarian.model.services.ServiceGame;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GameItemController {

    private final ServiceGame serviceGame;

    //Main Branch of Games
    @GetMapping("/gamesList") @CrossOrigin(origins="*")
    List<GameItem> printAllGamesList(){ return serviceGame.allGamesList(); }

    //Games CRUD
    @PostMapping("/gamesList") @CrossOrigin(origins="*")
    GameItem addGameItem(@RequestBody GameItem gameItem){ return serviceGame.addGameItem(gameItem); }
    @DeleteMapping("/gamesList/{idGameItem}") @CrossOrigin(origins="*")
    Optional<GameItem> deleteGameItem(@PathVariable long idGameItem){ return serviceGame.deleteGameItem(idGameItem); }
    @PostMapping("/gamesList/{idGameItem}") @CrossOrigin(origins="*")
    GameItem updateGameItem(@PathVariable long idGameItem, @RequestBody GameItem gameItem){ return serviceGame.updateGameItem(idGameItem, gameItem); }

}