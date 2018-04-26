package barrylui.myteam.RankingsModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("GamesPlayed")
    @Expose
    private GamesPlayed gamesPlayed;
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
    private PtsPerGame ptsPerGame;
    @SerializedName("PtsAgainstPerGame")
    @Expose
    private PtsAgainstPerGame ptsAgainstPerGame;

    public GamesPlayed getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(GamesPlayed gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
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

    public PtsPerGame getPtsPerGame() {
        return ptsPerGame;
    }

    public void setPtsPerGame(PtsPerGame ptsPerGame) {
        this.ptsPerGame = ptsPerGame;
    }

    public PtsAgainstPerGame getPtsAgainstPerGame() {
        return ptsAgainstPerGame;
    }

    public void setPtsAgainstPerGame(PtsAgainstPerGame ptsAgainstPerGame) {
        this.ptsAgainstPerGame = ptsAgainstPerGame;
    }

}
