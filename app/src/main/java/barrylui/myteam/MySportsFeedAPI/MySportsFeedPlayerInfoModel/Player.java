package barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerInfoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("JerseyNumber")
    @Expose
    private String jerseyNumber;
    @SerializedName("Position")
    @Expose
    private String position;
    @SerializedName("Height")
    @Expose
    private String height;
    @SerializedName("Weight")
    @Expose
    private String weight;
    @SerializedName("BirthDate")
    @Expose
    private String birthDate;
    @SerializedName("Age")
    @Expose
    private String age;
    @SerializedName("BirthCity")
    @Expose
    private String birthCity;
    @SerializedName("BirthCountry")
    @Expose
    private String birthCountry;
    @SerializedName("IsRookie")
    @Expose
    private String isRookie;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(String jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getIsRookie() {
        return isRookie;
    }

    public void setIsRookie(String isRookie) {
        this.isRookie = isRookie;
    }

}