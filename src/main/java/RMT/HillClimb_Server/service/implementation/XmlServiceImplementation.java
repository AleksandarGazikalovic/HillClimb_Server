package RMT.HillClimb_Server.service.implementation;

import RMT.HillClimb_Server.domain.User;
import RMT.HillClimb_Server.dto.*;
import RMT.HillClimb_Server.service.XmlService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

    /*@Override
    public void serlializeCoins(String filePath, PlayerDTO playerDTO) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(filePath);
            boolean foundPlayer=false;

            NodeList playerNodes = doc.getElementsByTagName("PlayerDTO");
            for (int i = 0; i < playerNodes.getLength(); i++) {
                Element playerElement = (Element) playerNodes.item(i);
                String userId = playerElement.getElementsByTagName("UserId").item(0).getTextContent();

                if (userId.equals(String.valueOf(playerDTO.getId()))) {
                    Element coinsElement = (Element) playerElement.getElementsByTagName("Coins").item(0);
                    coinsElement.setTextContent(String.valueOf(playerDTO.getCoins()));
                    foundPlayer=true;
                    break;
                }
            }
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(filePath);

                transformer.transform(source, result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/

    /*@Override
    public void serlializeScore(String filePath, PlayerDTO playerDTO) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(filePath);
            boolean foundPlayer=false;

            NodeList playerNodes = doc.getElementsByTagName("PlayerDTO");
            for (int i = 0; i < playerNodes.getLength(); i++) {
                Element playerElement = (Element) playerNodes.item(i);
                String userId = playerElement.getElementsByTagName("UserId").item(0).getTextContent();

                if (userId.equals(String.valueOf(playerDTO.getId()))) {
                    Element bestScoreElement = (Element) playerElement.getElementsByTagName("BestScore").item(0);
                    bestScoreElement.setTextContent(String.valueOf(playerDTO.getBestScore()));
                    foundPlayer=true;
                    break;
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(filePath);

            transformer.transform(source, result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/

}
