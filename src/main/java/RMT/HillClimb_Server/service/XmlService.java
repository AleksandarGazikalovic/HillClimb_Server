package RMT.HillClimb_Server.service;

import RMT.HillClimb_Server.domain.User;
import RMT.HillClimb_Server.dto.ArrayOfPlayersDTO;
import RMT.HillClimb_Server.dto.DTO;
import RMT.HillClimb_Server.dto.PlayerDTO;

import java.util.List;

public interface XmlService {

    public void serializeArray(String filePath, ArrayOfPlayersDTO playerDTOs);

    public ArrayOfPlayersDTO deserializeArray(String filePath);

    public User deserializeUser(String filePath);

}
