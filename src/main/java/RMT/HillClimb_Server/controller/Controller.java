package RMT.HillClimb_Server.controller;
import RMT.HillClimb_Server.dto.DTO;
import RMT.HillClimb_Server.dto.LoadPlayersResponseDTO;
import RMT.HillClimb_Server.dto.LoadSelectedResponseDTO;
import RMT.HillClimb_Server.dto.PlayerDTO;
import RMT.HillClimb_Server.service.GameService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.stereotype.Controller
@Getter
@Setter
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:8000/")
public class Controller {
    private GameService gameService;

    @Autowired
    public Controller(GameService gameService) {
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

}
