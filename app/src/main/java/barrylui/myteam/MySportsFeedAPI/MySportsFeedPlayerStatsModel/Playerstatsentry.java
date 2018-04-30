package barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerStatsModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playerstatsentry {

    @SerializedName("player")
    @Expose
    private Player player;
    @SerializedName("team")
    @Expose
    private Team team;
    @SerializedName("stats")
    @Expose
    private Stats stats;

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

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

}
