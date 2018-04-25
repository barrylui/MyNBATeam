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

import java.text.DecimalFormat;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_landing_page);

        int teamid = getIntent().getIntExtra("TeamID",0);
        int teamlogo = getIntent().getIntExtra("TeamLogo",0);
        final int teamcolors = getIntent().getIntExtra("TeamColors",0);
        String teamName = getIntent().getStringExtra("TeamName");
        final int teamConference = getIntent().getIntExtra("TeamConference", -1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(getDrawable(teamcolors));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final TextView franchiseName = (TextView)findViewById(R.id.franchiseName);


        final TextView winstv = (TextView)findViewById(R.id.numWins);
        final TextView losestv = (TextView)findViewById(R.id.numLoses);
        final TextView dashtv = (TextView) findViewById(R.id.dash);
        final TextView ppgtv = (TextView)findViewById(R.id.ppgtextview);
        final TextView oppgtv = (TextView)findViewById(R.id.oppgtextview);
        final TextView apgtv = (TextView)findViewById(R.id.apgtextview);
        final TextView rpgtv = (TextView)findViewById(R.id.rpgtextview);
        final TextView tpatv = (TextView)findViewById(R.id.textview3pa);
        final TextView tpptv = (TextView)findViewById(R.id.textview3pp);
        winstv.setTextColor(getResources().getColor(teamcolors));
        losestv.setTextColor(getResources().getColor(teamcolors));
        dashtv.setTextColor(getResources().getColor(teamcolors));

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

            String BASE_URL = "https://api.mysportsfeeds.com/v1.2/pull/nba/2017-2018-regular/";

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            SportsFeedAPI sportsFeedAPI = retrofit.create(SportsFeedAPI.class);
            String teamstatsparams = "W,L,PTS/G,AST/G,REB/G,3PA/G,3PM/G,PTS/G,PTSA/G";
            Call<Standings> call = sportsFeedAPI.getStandings(teamstatsparams, teamName);

            call.enqueue(new Callback<Standings>() {
                @Override
                public void onResponse(Call<Standings> call, Response<Standings> response) {
                    Log.d(TAG, "onResponse: Server Response: " + response.toString());


                    if(response.code()==200){
                        String city = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getTeam().getCity();
                        String name = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getTeam().getName();
                        franchiseName.setText(city + " " + name);

                        String numberOfWins = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getWins().getText();
                        winstv.setText(numberOfWins);

                        String numberOfLoses = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getLosses().getText();
                        losestv.setText(numberOfLoses);

                        String teamppg = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getPtsPerGame().get(0).getText();
                        ppgtv.setText(teamppg + " Points Per Game");

                        String oPPG = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getPtsAgainstPerGame().getText();
                        oppgtv.setText(oPPG + " Opponents PPG");

                        String apg = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getAstPerGame().getText();
                        apgtv.setText(apg + " Assists Per Game");

                        String rpg = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getRebPerGame().getText();
                        rpgtv.setText(rpg + " Rebounds Per Game" );

                        String tpa = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getFg3PtAttPerGame().getText();
                        tpatv.setText(tpa + " 3Pt Attempts Per Game");

                        double threepointatt = Double.parseDouble(tpa);
                        double threepointmade = Double.parseDouble(response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getFg3PtMadePerGame().getText());
                        double threepercentage = threepointmade/threepointatt;
                        DecimalFormat form = new DecimalFormat(".000");
                        tpptv.setText(form.format(threepercentage) + " 3Pt Percentage");
                        Log.d(TAG, "3PA " + threepointatt + " 3PM " + threepointmade + " 3PT% " + threepercentage);
                    }
                    else{
                        Log.d(TAG, "onResponse: Server Response " + response.toString());
                        Toast.makeText(TeamLandingPage.this, "Error " + response.toString(), Toast.LENGTH_LONG).show();
                    }
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

    public void bindTeamStatsData(){

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
