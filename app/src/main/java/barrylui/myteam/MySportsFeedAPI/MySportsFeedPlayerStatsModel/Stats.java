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
    @SerializedName("Reb")
    @Expose
    private Reb reb;
    @SerializedName("RebPerGame")
    @Expose
    private RebPerGame rebPerGame;
    @SerializedName("Ast")
    @Expose
    private Ast ast;
    @SerializedName("AstPerGame")
    @Expose
    private AstPerGame astPerGame;
    @SerializedName("Pts")
    @Expose
    private Pts pts;
    @SerializedName("PtsPerGame")
    @Expose
    private PtsPerGame ptsPerGame;
    @SerializedName("Stl")
    @Expose
    private Stl stl;
    @SerializedName("StlPerGame")
    @Expose
    private StlPerGame stlPerGame;
    @SerializedName("Blk")
    @Expose
    private Blk blk;
    @SerializedName("BlkPerGame")
    @Expose
    private BlkPerGame blkPerGame;
    @SerializedName("MinSeconds")
    @Expose
    private MinSeconds minSeconds;
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

    public Reb getReb() {
        return reb;
    }

    public void setReb(Reb reb) {
        this.reb = reb;
    }

    public RebPerGame getRebPerGame() {
        return rebPerGame;
    }

    public void setRebPerGame(RebPerGame rebPerGame) {
        this.rebPerGame = rebPerGame;
    }

    public Ast getAst() {
        return ast;
    }

    public void setAst(Ast ast) {
        this.ast = ast;
    }

    public AstPerGame getAstPerGame() {
        return astPerGame;
    }

    public void setAstPerGame(AstPerGame astPerGame) {
        this.astPerGame = astPerGame;
    }

    public Pts getPts() {
        return pts;
    }

    public void setPts(Pts pts) {
        this.pts = pts;
    }

    public PtsPerGame getPtsPerGame() {
        return ptsPerGame;
    }

    public void setPtsPerGame(PtsPerGame ptsPerGame) {
        this.ptsPerGame = ptsPerGame;
    }

    public Stl getStl() {
        return stl;
    }

    public void setStl(Stl stl) {
        this.stl = stl;
    }

    public StlPerGame getStlPerGame() {
        return stlPerGame;
    }

    public void setStlPerGame(StlPerGame stlPerGame) {
        this.stlPerGame = stlPerGame;
    }

    public Blk getBlk() {
        return blk;
    }

    public void setBlk(Blk blk) {
        this.blk = blk;
    }

    public BlkPerGame getBlkPerGame() {
        return blkPerGame;
    }

    public void setBlkPerGame(BlkPerGame blkPerGame) {
        this.blkPerGame = blkPerGame;
    }

    public MinSeconds getMinSeconds() {
        return minSeconds;
    }

    public void setMinSeconds(MinSeconds minSeconds) {
        this.minSeconds = minSeconds;
    }

    public MinSecondsPerGame getMinSecondsPerGame() {
        return minSecondsPerGame;
    }

    public void setMinSecondsPerGame(MinSecondsPerGame minSecondsPerGame) {
        this.minSecondsPerGame = minSecondsPerGame;
    }

}
