package barrylui.myteam.ConferenceTeamStandingsModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("GamesPlayed")
    @Expose
    private GamesPlayed gamesPlayed;
    @SerializedName("Fg3PtAttPerGame")
    @Expose
    private Fg3PtAttPerGame fg3PtAttPerGame;
    @SerializedName("Fg3PtMadePerGame")
    @Expose
    private Fg3PtMadePerGame fg3PtMadePerGame;
    @SerializedName("RebPerGame")
    @Expose
    private RebPerGame rebPerGame;
    @SerializedName("AstPerGame")
    @Expose
    private AstPerGame astPerGame;
    @SerializedName("PtsPerGame")
    @Expose
    private List<PtsPerGame> ptsPerGame = null;
    @SerializedName("PtsAgainstPerGame")
    @Expose
    private PtsAgainstPerGame ptsAgainstPerGame;
    @SerializedName("Wins")
    @Expose
    private Wins wins;
    @SerializedName("Losses")
    @Expose
    private Losses losses;

    public GamesPlayed getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(GamesPlayed gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Fg3PtAttPerGame getFg3PtAttPerGame() {
        return fg3PtAttPerGame;
    }

    public void setFg3PtAttPerGame(Fg3PtAttPerGame fg3PtAttPerGame) {
        this.fg3PtAttPerGame = fg3PtAttPerGame;
    }

    public Fg3PtMadePerGame getFg3PtMadePerGame() {
        return fg3PtMadePerGame;
    }

    public void setFg3PtMadePerGame(Fg3PtMadePerGame fg3PtMadePerGame) {
        this.fg3PtMadePerGame = fg3PtMadePerGame;
    }

    public RebPerGame getRebPerGame() {
        return rebPerGame;
    }

    public void setRebPerGame(RebPerGame rebPerGame) {
        this.rebPerGame = rebPerGame;
    }

    public AstPerGame getAstPerGame() {
        return astPerGame;
    }

    public void setAstPerGame(AstPerGame astPerGame) {
        this.astPerGame = astPerGame;
    }

    public List<PtsPerGame> getPtsPerGame() {
        return ptsPerGame;
    }

    public void setPtsPerGame(List<PtsPerGame> ptsPerGame) {
        this.ptsPerGame = ptsPerGame;
    }

    public PtsAgainstPerGame getPtsAgainstPerGame() {
        return ptsAgainstPerGame;
    }

    public void setPtsAgainstPerGame(PtsAgainstPerGame ptsAgainstPerGame) {
        this.ptsAgainstPerGame = ptsAgainstPerGame;
    }

    public Wins getWins() {
        return wins;
    }

    public void setWins(Wins wins) {
        this.wins = wins;
    }

    public Losses getLosses() {
        return losses;
    }

    public void setLosses(Losses losses) {
        this.losses = losses;
    }

}