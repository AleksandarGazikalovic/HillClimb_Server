package RMT.HillClimb_Server.service;

import RMT.HillClimb_Server.domain.User;
import RMT.HillClimb_Server.dto.ArrayOfPlayersDTO;
import RMT.HillClimb_Server.dto.DTO;
import RMT.HillClimb_Server.dto.PlayerDTO;

import java.util.List;

public interface XmlService {

    void serializeArray(String filePath, ArrayOfPlayersDTO playerDTOs);

    ArrayOfPlayersDTO deserializeArray(String filePath);

    User deserializeUser(String filePath);

    void serlializeCoins(String filePath, PlayerDTO playerDTO);

    void serlializeScore(String filePath, PlayerDTO playerDTO);
}
