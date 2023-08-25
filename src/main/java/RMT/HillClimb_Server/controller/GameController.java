package RMT.HillClimb_Server.controller;
import RMT.HillClimb_Server.dto.*;
import RMT.HillClimb_Server.service.GameService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Getter
@Setter
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:8000/")
public class GameController {
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService=gameService;
    }

    @GetMapping("/load-players")
    @CrossOrigin(origins = "http://localhost:8000/")
    public ResponseEntity<List<PlayerDTO>> loadPlayers(){
        DTO response = gameService.loadPlayers();
        if(response instanceof LoadPlayersResponseDTO) {
            return new ResponseEntity<>(((LoadPlayersResponseDTO) response).getPlayers().getPlayerDTO(), HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/load-selected")
    @CrossOrigin(origins = "http://localhost:8000/")
    public ResponseEntity<PlayerDTO> loadSelected(){
        DTO response = gameService.loadSelected();
        if(response instanceof LoadSelectedResponseDTO) {
            return new ResponseEntity<>(((LoadSelectedResponseDTO) response).getPlayerDTO(), HttpStatus.OK);
        }
        return null;
    }

    @PostMapping("/save-coins")
    @CrossOrigin(origins = "http://localhost:8000/")
    public ResponseEntity<PlayerDTO> saveCoins(@RequestBody PlayerDTO playerDTO){
        gameService.saveCoins(playerDTO);
        return null;
    }

    @PostMapping("/save-score")
    @CrossOrigin(origins = "http://localhost:8000/")
    public ResponseEntity<PlayerDTO> saveScore(@RequestBody PlayerDTO playerDTO){
        gameService.saveScore(playerDTO);
        return null;
    }



}
