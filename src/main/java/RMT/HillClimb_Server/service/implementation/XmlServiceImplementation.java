package RMT.HillClimb_Server.service.implementation;

import RMT.HillClimb_Server.domain.User;
import RMT.HillClimb_Server.dto.ArrayOfPlayersDTO;
import RMT.HillClimb_Server.dto.DTO;
import RMT.HillClimb_Server.dto.LoadPlayersResponseDTO;
import RMT.HillClimb_Server.dto.PlayerDTO;
import RMT.HillClimb_Server.service.XmlService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Getter
@Setter
@Service
public class XmlServiceImplementation implements XmlService {

    public void serializeArray(String filePath, ArrayOfPlayersDTO playerDTOs) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            JAXBContext context = JAXBContext.newInstance(ArrayOfPlayersDTO.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(playerDTOs, writer);
            writer.close();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayOfPlayersDTO deserializeArray(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(ArrayOfPlayersDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            ArrayOfPlayersDTO playerDTOs = (ArrayOfPlayersDTO) unmarshaller.unmarshal(new File(filePath));
            return playerDTOs;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User deserializeUser(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            User user = (User) unmarshaller.unmarshal(new File(filePath));
            return user;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

}
