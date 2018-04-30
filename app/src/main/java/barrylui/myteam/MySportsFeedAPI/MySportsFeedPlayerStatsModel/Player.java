package barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerStatsModel;

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

}
