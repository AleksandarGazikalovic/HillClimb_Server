package RMT.HillClimb_Server.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class LoadSelectedResponseDTO extends DTO{

    private PlayerDTO playerDTO;
}
