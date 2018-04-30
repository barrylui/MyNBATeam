package barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerInfoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playerentry {

    @SerializedName("player")
    @Expose
    private Player player;
    @SerializedName("team")
    @Expose
    private Team team;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
