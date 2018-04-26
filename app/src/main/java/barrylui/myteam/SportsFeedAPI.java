package barrylui.myteam;

import barrylui.myteam.ConferenceTeamStandingsModel.Standings;
import barrylui.myteam.RankingsModel.Rankings;
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
}
