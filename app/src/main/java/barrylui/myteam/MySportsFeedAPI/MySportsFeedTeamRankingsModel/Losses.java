package barrylui.myteam.MySportsFeedAPI.MySportsFeedTeamRankingsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Losses {

    @SerializedName("@category")
    @Expose
    private String category;
    @SerializedName("@abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("#text")
    @Expose
    private String text;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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
