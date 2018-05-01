package barrylui.myteam.MySportsFeedAPI.MySportsFeedTeamRankingsModel.TeamRankingsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GamesPlayed {

    @SerializedName("@abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("#text")
    @Expose
    private String text;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
