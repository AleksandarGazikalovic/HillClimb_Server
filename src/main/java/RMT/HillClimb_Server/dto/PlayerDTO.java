package RMT.HillClimb_Server.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {


    private String Name;

    private String Surname;

    private int Id;

    private float Coins;

    private float BestScore;

    private List<String> UnlockedCars;

    private float LastScore;

    private String SelectedCar;



    @XmlElement(name="UserName")
    @JsonProperty("UserName")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @XmlElement(name="UserSurname")
    @JsonProperty("UserSurname")
    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }


    @XmlElement(name="UserId")
    @JsonProperty("UserId")
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @XmlElement(name = "Coins")
    @JsonProperty("Coins")
    public float getCoins() {
        return Coins;
    }


    public void setCoins(float coins) {
        Coins = coins;
    }

    @XmlElement(name = "BestScore")
    @JsonProperty("BestScore")
    public float getBestScore() {
        return BestScore;
    }

    public void setBestScore(float bestScore) {
        BestScore = bestScore;
    }

    @XmlElement(name = "UnlockedCars")
    @JsonProperty("UnlockedCars")
    public List<String> getUnlockedCars() {
        return UnlockedCars;
    }

    public void setUnlockedCars(List<String> unlockedCars) {
        UnlockedCars = unlockedCars;
    }

    @JsonIgnore
    @XmlTransient
    public float getLastScore() {
        return LastScore;
    }

    public void setLastScore(float lastScore) {
        LastScore = lastScore;
    }

    @XmlElement(name = "SelectedCar")
    @JsonProperty("SelectedCar")
    public String getSelectedCar() {
        return SelectedCar;
    }

    public void setSelectedCar(String selectedCar) {
        SelectedCar = selectedCar;
    }

    public PlayerDTO(String name, String surname, int id) {
        Name = name;
        Surname = surname;
        Id = id;
        UnlockedCars = new ArrayList<>(Arrays.asList("Car1"));
    }
}
