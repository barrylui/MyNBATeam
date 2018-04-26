package barrylui.myteam.RankingsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teamentry {

    @SerializedName("team")
    @Expose
    private Team team;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("stats")
    @Expose
    private Stats stats;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

}