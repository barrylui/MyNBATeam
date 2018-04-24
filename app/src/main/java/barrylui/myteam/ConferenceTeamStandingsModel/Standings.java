package barrylui.myteam.ConferenceTeamStandingsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Standings{

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