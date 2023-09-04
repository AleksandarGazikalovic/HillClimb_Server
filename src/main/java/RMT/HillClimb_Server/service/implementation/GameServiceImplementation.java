package RMT.HillClimb_Server.service.implementation;

import RMT.HillClimb_Server.domain.User;
import RMT.HillClimb_Server.dto.*;
import RMT.HillClimb_Server.service.GameService;
import RMT.HillClimb_Server.service.XmlService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;


@Getter
@Setter
@Service
public class GameServiceImplementation implements GameService {

    private XmlService xmlService;
    private ArrayOfPlayersDTO playerDTOs = new ArrayOfPlayersDTO();
    private PlayerDTO selectedPlayerDTO;

    @Autowired
    public GameServiceImplementation(XmlService xmlService) {
        this.xmlService = xmlService;
    }


    @Override
    public DTO loadPlayers() {
        String path = System.getProperty("user.home") + "\\Documents\\";
        if (!path.endsWith("\\")) path += "\\";
        path += "Pendulum_v3FullApp\\";
        try {
            File HCRPlayers = new File(path + "HCRPlayers.xml");
            if (HCRPlayers.exists()) {
                File HCRPlayers_Backup = new File(path + "HCRPlayers_Backup.xml");

                if (HCRPlayers_Backup.exists()) {
                    HCRPlayers_Backup.delete();
                }


                Files.copy(HCRPlayers.toPath(), HCRPlayers_Backup.toPath());
                playerDTOs = xmlService.deserializeArray(HCRPlayers.toPath().toString());
                if(playerDTOs==null) {
                    playerDTOs=new ArrayOfPlayersDTO();
                }
                return new LoadPlayersResponseDTO(playerDTOs);
            } else {
                File HCRPlayers_Backup = new File(path + "HCRPlayers_Backup.xml");
                if (HCRPlayers_Backup.exists()) {
                    File HCRPlayers_Backup1 = new File(path + "HCRPlayers_Backup1.xml");
                    if (HCRPlayers_Backup1.exists()) {
                        HCRPlayers_Backup1.delete();
                    }

                    Files.copy(HCRPlayers_Backup.toPath(), HCRPlayers_Backup1.toPath());
                    playerDTOs = xmlService.deserializeArray(HCRPlayers_Backup.toPath().toString());
                    if(playerDTOs==null) {
                        playerDTOs=new ArrayOfPlayersDTO();
                    }
                    return new LoadPlayersResponseDTO(playerDTOs);
                }
                return new ErrorResponseDTO("Error loading players!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResponseDTO("Error loading players!");
        }

    }

    @Override
    public DTO loadSelected() {
        String path = System.getProperty("user.home") + "\\Documents\\";
        if (!path.endsWith("\\")) path += "\\";
        path += "Pendulum_v3FullApp\\";
        try {
            File SelectedUser = new File(path + "SelectedUser.xml");
            if (SelectedUser.exists()) {
                User selectedUser = xmlService.deserializeUser(SelectedUser.toPath().toString());

                if (selectedUser != null) {
                    if (playerDTOs != null) {
                        Optional<PlayerDTO> tempPlayer = playerDTOs.getPlayerDTO().stream().filter(playerDTO -> playerDTO.getId() == selectedUser.getId()).findFirst();
                        if (tempPlayer.isPresent()) {
                            selectedPlayerDTO = tempPlayer.get();
                            return new LoadSelectedResponseDTO(tempPlayer.get());
                        } else {
                            selectedPlayerDTO = new PlayerDTO(selectedUser.getName(),selectedUser.getSurname(),selectedUser.getId());
                            AddPlayer(selectedPlayerDTO,playerDTOs);
                            return new LoadSelectedResponseDTO(selectedPlayerDTO);

                        }
                    } else {
                        selectedPlayerDTO = new PlayerDTO(selectedUser.getName(),selectedUser.getSurname(),selectedUser.getId());
                        AddPlayer(selectedPlayerDTO,playerDTOs);
                        return new LoadSelectedResponseDTO(selectedPlayerDTO);
                    }

                } else {
                    return new ErrorResponseDTO("User not selected!");
                }
            } else {
                return new ErrorResponseDTO("No such file found!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResponseDTO("Error loading selected user!");
        }
    }

    @Override
    public void AddPlayer(PlayerDTO playerDTO, ArrayOfPlayersDTO playerDTOs) {
        String path = System.getProperty("user.home") + "\\Documents\\";
        if (!path.endsWith("\\")) path += "\\";
        path += "Pendulum_v3FullApp\\";
        File HCRPlayers = new File(path + "HCRPlayers.xml");
            if (playerDTO != null) {
                playerDTOs.getPlayerDTO().add(playerDTO);
                xmlService.serializeArray(HCRPlayers.toPath().toString(), playerDTOs);
            }
        }

    @Override
    public void saveCoins(PlayerDTO playerDTO) {
        String path = System.getProperty("user.home") + "\\Documents\\";
        if (!path.endsWith("\\")) path += "\\";
        path += "Pendulum_v3FullApp\\";
        File HCRPlayers = new File(path + "HCRPlayers.xml");
        if(HCRPlayers.exists()) {
            playerDTOs.getPlayerDTO().stream()
                    .filter(obj -> obj.getId()==playerDTO.getId())
                    .forEach(obj -> {
                        obj.setCoins(playerDTO.getCoins());
                    });

            xmlService.serializeArray(HCRPlayers.getPath().toString(),playerDTOs);
            //xmlService.serlializeCoins(HCRPlayers.toPath().toString(),playerDTO);
        }
    }

    @Override
    public void saveScore(PlayerDTO playerDTO) {
        String path = System.getProperty("user.home") + "\\Documents\\";
        if (!path.endsWith("\\")) path += "\\";
        path += "Pendulum_v3FullApp\\";
        File HCRPlayers = new File(path + "HCRPlayers.xml");
        if(HCRPlayers.exists()) {
            playerDTOs.getPlayerDTO().stream()
                    .filter(obj -> obj.getId()==playerDTO.getId())
                    .forEach(obj -> { obj.setBestScore(playerDTO.getBestScore());
                    });

            xmlService.serializeArray(HCRPlayers.getPath().toString(),playerDTOs);
            //xmlService.serlializeCoins(HCRPlayers.toPath().toString(),playerDTO);
        }
    }

    @Override
    public void saveUnlockedCar(PlayerDTO playerDTO) {
        String path = System.getProperty("user.home") + "\\Documents\\";
        if (!path.endsWith("\\")) path += "\\";
        path += "Pendulum_v3FullApp\\";
        File HCRPlayers = new File(path + "HCRPlayers.xml");
        if(HCRPlayers.exists()) {
            playerDTOs.getPlayerDTO().stream()
                    .filter(obj -> obj.getId()==playerDTO.getId())
                    .forEach(obj -> {
                        obj.setUnlockedCars(playerDTO.getUnlockedCars());
                    });

            xmlService.serializeArray(HCRPlayers.getPath().toString(),playerDTOs);
        }
    }

    @Override
    public void saveSelectedCar(PlayerDTO playerDTO) {
        String path = System.getProperty("user.home") + "\\Documents\\";
        if (!path.endsWith("\\")) path += "\\";
        path += "Pendulum_v3FullApp\\";
        File HCRPlayers = new File(path + "HCRPlayers.xml");
        if(HCRPlayers.exists()) {
            playerDTOs.getPlayerDTO().stream()
                    .filter(obj -> obj.getId()==playerDTO.getId())
                    .forEach(obj -> {
                        obj.setSelectedCar(playerDTO.getSelectedCar());
                    });

            xmlService.serializeArray(HCRPlayers.getPath().toString(),playerDTOs);
            //xmlService.serlializeScore(HCRPlayers.toPath().toString(),playerDTO);
        }
    }

    public void nzm(){

    }
}




