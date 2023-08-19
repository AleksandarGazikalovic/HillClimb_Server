package RMT.HillClimb_Server.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name = "ArrayOfPlayerDTO")
public class ArrayOfPlayersDTO {

    private List<PlayerDTO> playerDTO;

    @XmlElement(name = "PlayerDTO")
    public List<PlayerDTO> getPlayerDTO() {
        return playerDTO;
    }

    public void setPlayerDTO(List<PlayerDTO> playerDTO) {
        this.playerDTO = playerDTO;
    }

    public ArrayOfPlayersDTO() {
        playerDTO = new ArrayList<>();
    }
}
