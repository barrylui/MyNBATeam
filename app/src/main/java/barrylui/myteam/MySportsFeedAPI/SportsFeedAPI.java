package barrylui.myteam.MySportsFeedAPI;

import barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerInfoModel.PlayerInfo;
import barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerStatsModel.PlayerStatsPerGame;
import barrylui.myteam.MySportsFeedAPI.MySportsFeedTeamRankingsModel.Standings;
import barrylui.myteam.TeamRankingsModel.Rankings;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by The MACHINE on 4/24/2018.
 */

public interface SportsFeedAPI {

    String BASE_URL = "https://api.mysportsfeeds.com/v1.2/pull/nba/2017-2018-regular/";
    String teamstatsparams = "W,L,PTS/G,AST/G,REB/G,3PA/G,3PM/G,PTS/G,PTSA/G";

    @Headers("Content-type: application/json")
    @GET("conference_team_standings.json?")
    Call<Standings> getStandings(
            @Query("teamstats") String param1,
            @Query("team") String param2);

    @Headers("Content-type: application/json")
    @GET("conference_team_standings.json?")
    Call<Rankings> getStatsRank(
            @Query("teamstats") String param1);

    @Headers("Content-type: application/json")
    @GET("cumulative_player_stats.json?")
    //player=Kristaps-Porzingis&playerstats=PTS/G,AST/G,REB/G,STL/G,BS/G/
    Call<PlayerStatsPerGame> getPlayerStatsPerGame(@Query("player") String param1,
                                                   @Query("playerstats") String param2);

    @Headers("Content-type: application/json")
    @GET("roster_players.json?")
    Call<PlayerInfo> getPlayerInfo(@Query("fordate") String param1,
                                   @Query("team") String param2);
}
