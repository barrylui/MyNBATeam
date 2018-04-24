package barrylui.myteam.TeamLandingPage;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.design.widget.NavigationView;
import android.widget.Toast;

import barrylui.myteam.BasicAuthInterceptor;
import barrylui.myteam.ConferenceTeamStandingsModel.Standings;
import barrylui.myteam.GameLog;
import barrylui.myteam.R;
import barrylui.myteam.RosterViewer;
import barrylui.myteam.SportsFeedAPI;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TeamLandingPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    private static final String TAG = "TeamLandingPage";
    private static final String BASE_URL = "http://data.nba.net/10s/prod/v1/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_landing_page);


        String BASE_URL = "https://api.mysportsfeeds.com/v1.2/pull/nba/2017-2018-regular/";
        String Season = "2017-18";
        String LeagueID = "00";
        String SeasonType = "Regular%20Season&";

        int teamid = getIntent().getIntExtra("TeamID",0);
        int teamlogo = getIntent().getIntExtra("TeamLogo",0);
        int teamcolors = getIntent().getIntExtra("TeamColors",0);
        String teamName = getIntent().getStringExtra("TeamName");
        final int teamConference = getIntent().getIntExtra("TeamConference", -1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(getDrawable(teamcolors));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final TextView franchiseName = (TextView)findViewById(R.id.franchiseName);


        final TextView wins = (TextView)findViewById(R.id.numWins);
        final TextView loses = (TextView)findViewById(R.id.numLoses);
        final TextView dash = (TextView) findViewById(R.id.dash);
        wins.setTextColor(getResources().getColor(teamcolors));
        loses.setTextColor(getResources().getColor(teamcolors));
        dash.setTextColor(getResources().getColor(teamcolors));

        ImageView iv = (ImageView)findViewById(R.id.teamlogo);
        iv.setImageResource(teamlogo);
        //TextView tv = (TextView)findViewById(R.id.teamID);
        //tv.setText(teamid);

        if(InternetCheckerUtility.isNetworkAvailable(TeamLandingPage.this)==false){
            Toast.makeText(TeamLandingPage.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }else{
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new BasicAuthInterceptor(getString(R.string.username), getString(R.string.password)))
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            String teamstatsparams = "W,L,PTS/G,AST/G,REB/G,3PA/G,3PM/G,PTS/G,PTSA/G";
            SportsFeedAPI sportsFeedAPI = retrofit.create(SportsFeedAPI.class);
            Call<Standings> call = sportsFeedAPI.getStandings(teamstatsparams, teamName);

            call.enqueue(new Callback<Standings>() {
                @Override
                public void onResponse(Call<Standings> call, Response<Standings> response) {
                    Log.d(TAG, "onResponse: Server Response: " + response.toString());

                    String city = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getTeam().getCity();
                    String name = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getTeam().getName();
                    franchiseName.setText(city + " " + name);

                    String numberOfWins = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getWins().getText();
                    wins.setText(numberOfWins);

                    String numberOfLoses = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getLosses().getText();
                    loses.setText(numberOfLoses);
                }

                @Override
                public void onFailure(Call<Standings> call, Throwable t) {
                    Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());

                }
            });

        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(R.id.item1).getIcon().setColorFilter(getResources().getColor(teamcolors), PorterDuff.Mode.SRC_IN);
        navigationView.getMenu().findItem(R.id.item2).getIcon().setColorFilter(getResources().getColor(teamcolors), PorterDuff.Mode.SRC_IN);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,  R.string.open_drawer, R.string.close_drawer)
        {
            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            //Starts map activity with court listings
            case R.id.item1:
                Intent rosterView = new Intent(this, RosterViewer.class);
                this.startActivity(rosterView);
                break;
            case R.id.item2:
                Intent gamelog = new Intent(this, GameLog.class);
                this.startActivity(gamelog);
                break;
            default:
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
