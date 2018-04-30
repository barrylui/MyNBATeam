package barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerInfoModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rosterplayers {

    @SerializedName("lastUpdatedOn")
    @Expose
    private String lastUpdatedOn;
    @SerializedName("playerentry")
    @Expose
    private List<Playerentry> playerentry = null;

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public List<Playerentry> getPlayerentry() {
        return playerentry;
    }

    public void setPlayerentry(List<Playerentry> playerentry) {
        this.playerentry = playerentry;
    }

}