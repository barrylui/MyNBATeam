package barrylui.myteam.TeamRoster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import barrylui.myteam.R;
import barrylui.myteam.SuredBitsAPI.SuredBitsAPI;
import barrylui.myteam.InternetUtilities.InternetCheckerUtility;
import barrylui.myteam.SuredBitsAPI.SuredBitsPlayerModel.PlayerInfoModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RosterViewer extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RosterViewerAdapter recycleViewAdapter = null;
    public List<PlayerInfoModel> listOfPlayers = null;
    Retrofit retrofit;
    String teamName;
    int  teamColors;
    int teamLogo;
    private static final String TAG = "RosterView";
    ImageView toolbarLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_viewer);
        teamName = getIntent().getStringExtra("TeamAbbrv");
        teamColors = getIntent().getIntExtra("TeamColor",0);
        teamLogo = getIntent().getIntExtra("TeamLogo", 0);

        //change team abbr for okl and bro so the appreviations can be used to interact with mysportsfeed api
        if(teamName.equals("OKL")){
            teamName = "OKC";
        }
        if(teamName.equals("BRO")){
            teamName = "BKN";
        }

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getColor(teamColors));

        Toolbar toolbar = (Toolbar) findViewById(R.id.rostertoolbar);
        toolbarLogo = (ImageView)findViewById(R.id.rostertoolbarlogo);
        toolbarLogo.setImageResource(teamLogo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(getDrawable(teamColors));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        String BASE_URL = "http://api.suredbits.com/nba/v0/";

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if(InternetCheckerUtility.isNetworkAvailable(RosterViewer.this)==false){
            Toast.makeText(RosterViewer.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }
        else {
            SuredBitsAPI suredBitsAPI = retrofit.create(SuredBitsAPI.class);
            Call<List<PlayerInfoModel>> call = suredBitsAPI.getTeamInfo(teamName);
            call.enqueue(new Callback<List<PlayerInfoModel>>() {
                @Override
                public void onResponse(Call<List<PlayerInfoModel>> call, Response<List<PlayerInfoModel>> response) {
                    Log.d(TAG, "onResponse: Server Response: " + response.toString());
                    listOfPlayers = response.body();
                    recycleViewAdapter = new RosterViewerAdapter(getApplicationContext(), listOfPlayers);
                    recyclerView.setAdapter(recycleViewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                            LinearLayoutManager.VERTICAL,
                            false));
                }

                @Override
                public void onFailure(Call<List<PlayerInfoModel>> call, Throwable t) {
                    Log.d("Error: ", t.getMessage());
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return true;
    }
}
