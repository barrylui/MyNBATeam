package barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerInfoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerInfo {

    @SerializedName("rosterplayers")
    @Expose
    private Rosterplayers rosterplayers;

    public Rosterplayers getRosterplayers() {
        return rosterplayers;
    }

    public void setRosterplayers(Rosterplayers rosterplayers) {
        this.rosterplayers = rosterplayers;
    }

}
