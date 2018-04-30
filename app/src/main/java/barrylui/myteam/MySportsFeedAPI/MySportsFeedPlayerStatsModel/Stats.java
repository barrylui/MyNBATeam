package barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerStatsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("GamesPlayed")
    @Expose
    private GamesPlayed gamesPlayed;
    @SerializedName("FgAtt")
    @Expose
    private FgAtt fgAtt;
    @SerializedName("FgMade")
    @Expose
    private FgMade fgMade;
    @SerializedName("FtAtt")
    @Expose
    private FtAtt ftAtt;
    @SerializedName("FtMade")
    @Expose
    private FtMade ftMade;
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
    @SerializedName("MinSecondsPerGame")
    @Expose
    private MinSecondsPerGame minSecondsPerGame;

    public GamesPlayed getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(GamesPlayed gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public FgAtt getFgAtt() {
        return fgAtt;
    }

    public void setFgAtt(FgAtt fgAtt) {
        this.fgAtt = fgAtt;
    }

    public FgMade getFgMade() {
        return fgMade;
    }

    public void setFgMade(FgMade fgMade) {
        this.fgMade = fgMade;
    }

    public FtAtt getFtAtt() {
        return ftAtt;
    }

    public void setFtAtt(FtAtt ftAtt) {
        this.ftAtt = ftAtt;
    }

    public FtMade getFtMade() {
        return ftMade;
    }

    public void setFtMade(FtMade ftMade) {
        this.ftMade = ftMade;
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

    public MinSecondsPerGame getMinSecondsPerGame() {
        return minSecondsPerGame;
    }

    public void setMinSecondsPerGame(MinSecondsPerGame minSecondsPerGame) {
        this.minSecondsPerGame = minSecondsPerGame;
    }

}
