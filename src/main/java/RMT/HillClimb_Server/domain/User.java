package RMT.HillClimb_Server.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(GameSesion.class)
public class User {
    @XmlElement(name = "UserName")
    @JsonProperty("UserName")
    private String Name = "";

    @XmlElement(name = "UserSurname")
    @JsonProperty("UserSurname")
    private String Surname = "";

    @XmlElement(name = "UserId")
    @JsonProperty("UserId")
    private int Id;

    @XmlElement(name = "UserBirthYear")
    @JsonProperty("UserBirthYear")
    private String BirthYear;

    @XmlElement(name = "UserDiagnosis")
    @JsonProperty("UserDiagnosis")
    private String Diagnosis;

    @XmlElement(name = "AcountDate")
    @JsonProperty("AcountDate")
    private String AccountDate;

    @XmlElementWrapper(name = "GameSesionArray")
    @XmlElement(name = "GameSesionObject")
    @JsonProperty("GameSesionArray")
    private List<GameSesion> GameSesions;

    @XmlElementWrapper(name = "AngleProgressArray")
    @XmlElement(name = "AngleProgressObject")
    @JsonProperty("AngleProgressArray")
    private List<AngleProgress> AngleProgressArray;

    private static List<Integer> idList;


    public User() {
    }

    public User(User user) {
        Id = user.Id;
        Name = user.Name;
        Surname = user.Surname;
        BirthYear = user.BirthYear;
        Diagnosis = user.Diagnosis;
        AccountDate = user.AccountDate;
    }

    public User(int generationMode) {
        Id = generateNewId();
        idList.add(Id);
    }

    public User(String birthYear, String diagnosis, String accountDate) {
        Id = generateNewId();
        BirthYear = birthYear;
        Diagnosis = diagnosis;
        AccountDate = accountDate;
    }
    public static void initIdList(List<User> uList) {
        idList = new ArrayList<>();
        if (uList != null) {
            for (User u : uList) {
                idList.add(u.Id);
            }
        }
    }

    private int generateNewId() {
        Random ran = new Random();
        try {
            int ri = ran.nextInt(15000000);
            while (idList.contains(ri)) {
                ri = ran.nextInt(15000000);
            }
            return ri;
        } catch (Exception e) {
            return 0;
        }
    }

    public int compareTo(Object obj) {
        if (obj == null) return 1;
        User otherUser = (User) obj;
        if (otherUser != null) {
            return this.Surname.compareTo(otherUser.Surname);
        } else {
            throw new IllegalArgumentException("Object is not a User");
        }
    }
}

@XmlType(name = "GameSesion")
class GameSesion {
    @XmlElement(name = "Training")
    @JsonProperty("Training")
    String training;

    @XmlElement(name = "Date")
    @JsonProperty("Date")
    String date;

    @XmlElement(name = "Duration")
    @JsonProperty("Duration")
    String duration;

    @XmlElement(name = "NumOfClicks")
    @JsonProperty("NumOfClicks")
    String numOfClicks;

    @XmlElement(name = "Game")
    @JsonProperty("Game")
    String game;

    public GameSesion() {
    }

    public GameSesion(String training, String date, String duration, String numOfClicks, String game) {
        this.training = training;
        this.date = date;
        this.duration = duration;
        this.numOfClicks = numOfClicks;
        this.game = game;
    }
}

@XmlType(name = "AngleProgress")
class AngleProgress {
    @XmlElement(name = "Date")
    @JsonProperty("Date")
    String date;

    @XmlElement(name = "AngleRange")
    @JsonProperty("AngleRange")
    String angleRange;

    public AngleProgress() {
    }

    public AngleProgress(String date, String angleRange) {
        this.date = date;
        this.angleRange = angleRange;
    }
}
