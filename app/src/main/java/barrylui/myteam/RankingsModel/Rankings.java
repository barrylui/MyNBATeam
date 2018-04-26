package barrylui.myteam.RankingsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rankings {

    @SerializedName("conferenceteamstandings")
    @Expose
    private Conferenceteamstandings conferenceteamstandings;

    public Conferenceteamstandings getConferenceteamstandings() {
        return conferenceteamstandings;
    }

    public void setConferenceteamstandings(Conferenceteamstandings conferenceteamstandings) {
        this.conferenceteamstandings = conferenceteamstandings;
    }

}