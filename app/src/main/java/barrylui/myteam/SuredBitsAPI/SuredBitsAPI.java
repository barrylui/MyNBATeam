package barrylui.myteam.SuredBitsAPI;

import java.util.List;

import barrylui.myteam.SuredBitsAPI.SuredBitsPlayerModel.PlayerInfoModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SuredBitsAPI {

    String BASE_URL = "http://api.suredbits.com/nba/v0/";

    @GET("players/{team}")
    Call<List<PlayerInfoModel>> getTeamInfo(@Path("team") String team);
}