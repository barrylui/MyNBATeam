package barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerStatsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerStatsPerGame {

    @SerializedName("cumulativeplayerstats")
    @Expose
    private Cumulativeplayerstats cumulativeplayerstats;

    public Cumulativeplayerstats getCumulativeplayerstats() {
        return cumulativeplayerstats;
    }

    public void setCumulativeplayerstats(Cumulativeplayerstats cumulativeplayerstats) {
        this.cumulativeplayerstats = cumulativeplayerstats;
    }

}
