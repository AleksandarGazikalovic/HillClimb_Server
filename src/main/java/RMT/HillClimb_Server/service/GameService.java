package RMT.HillClimb_Server.service;

import RMT.HillClimb_Server.dto.ArrayOfPlayersDTO;
import RMT.HillClimb_Server.dto.DTO;
import RMT.HillClimb_Server.dto.PlayerDTO;

public interface GameService {

    DTO loadPlayers();

    DTO loadSelected();

    void AddPlayer(PlayerDTO playerDTO, ArrayOfPlayersDTO playerDTOs);

    void saveCoins(PlayerDTO playerDTO);

    void saveScore(PlayerDTO playerDTO);
}
