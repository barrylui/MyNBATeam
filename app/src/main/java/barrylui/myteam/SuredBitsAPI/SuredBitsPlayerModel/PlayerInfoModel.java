package barrylui.myteam.SuredBitsAPI.SuredBitsPlayerModel;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerInfoModel {

    @SerializedName("lastYear")
    @Expose
    private int lastYear;
    @SerializedName("rookieYear")
    @Expose
    private int rookieYear;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("profileUrl")
    @Expose
    private String profileUrl;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("team")
    @Expose
    private String team;
    @SerializedName("playerId")
    @Expose
    private int playerId;

    public int getLastYear() {
        return lastYear;
    }

    public void setLastYear(int lastYear) {
        this.lastYear = lastYear;
    }

    public int getRookieYear() {
        return rookieYear;
    }

    public void setRookieYear(int rookieYear) {
        this.rookieYear = rookieYear;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

}
