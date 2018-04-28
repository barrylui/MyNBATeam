package barrylui.myteam.TeamRoster;

import java.util.List;

import barrylui.myteam.TeamRoster.SuredBitsPlayerModel.PlayerInfoModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SuredBitsAPI {

    String BASE_URL = "http://api.suredbits.com/nba/v0/";

    @GET("players/{team}")
    Call<List<PlayerInfoModel>> getTeamInfo(@Path("team") String team);

    @GET("stats/{lastname}/{firstname}/2018")
    Call<List<PlayerInfoModel>> getPlayerStats(@Path("lastname") String lastName,
                                         @Path("firstname") String firstName);
}