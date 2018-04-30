package barrylui.myteam.TeamRankingsModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Conferenceteamstandings {

    @SerializedName("lastUpdatedOn")
    @Expose
    private String lastUpdatedOn;
    @SerializedName("conference")
    @Expose
    private List<Conference> conference = null;

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public List<Conference> getConference() {
        return conference;
    }

    public void setConference(List<Conference> conference) {
        this.conference = conference;
    }

}


