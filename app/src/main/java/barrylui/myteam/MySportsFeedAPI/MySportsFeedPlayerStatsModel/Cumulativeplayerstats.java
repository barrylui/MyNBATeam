package barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerStatsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cumulativeplayerstats {

    @SerializedName("lastUpdatedOn")
    @Expose
    private String lastUpdatedOn;
    @SerializedName("playerstatsentry")
    @Expose
    private List<Playerstatsentry> playerstatsentry = null;

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public List<Playerstatsentry> getPlayerstatsentry() {
        return playerstatsentry;
    }

    public void setPlayerstatsentry(List<Playerstatsentry> playerstatsentry) {
        this.playerstatsentry = playerstatsentry;
    }

}

