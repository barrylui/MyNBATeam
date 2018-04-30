package barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerStatsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("GamesPlayed")
    @Expose
    private GamesPlayed gamesPlayed;
    @SerializedName("RebPerGame")
    @Expose
    private RebPerGame rebPerGame;
    @SerializedName("AstPerGame")
    @Expose
    private AstPerGame astPerGame;
    @SerializedName("PtsPerGame")
    @Expose
    private PtsPerGame ptsPerGame;
    @SerializedName("StlPerGame")
    @Expose
    private StlPerGame stlPerGame;
    @SerializedName("BlkPerGame")
    @Expose
    private BlkPerGame blkPerGame;

    public GamesPlayed getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(GamesPlayed gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
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

    public StlPerGame getStlPerGame() {
        return stlPerGame;
    }

    public void setStlPerGame(StlPerGame stlPerGame) {
        this.stlPerGame = stlPerGame;
    }

    public BlkPerGame getBlkPerGame() {
        return blkPerGame;
    }

    public void setBlkPerGame(BlkPerGame blkPerGame) {
        this.blkPerGame = blkPerGame;
    }

}
