package barrylui.myteam.TeamLandingPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.design.widget.NavigationView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;

import java.util.ArrayList;
import java.util.Arrays;

import barrylui.myteam.ChooseYourTeam;
import barrylui.myteam.ConferenceTeamStandingsModel.Standings;
import barrylui.myteam.GameLog;
import barrylui.myteam.R;
import barrylui.myteam.RankingsModel.Rankings;
import barrylui.myteam.RosterViewer;
import barrylui.myteam.SportsFeedAPI;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TeamLandingPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView franchiseName;
    TextView winstv;
    TextView losestv;
    TextView dashtv;
    TextView ppgtv;
    TextView oppgtv;
    TextView apgtv;
    TextView rpgtv;
    TextView tpatv;
    TextView tpptv;
    TextView teamranktv;
    Button teamRoster;
    TextView infotv;

    String teamName="";
    int teamConference=-1;
    int teamcolors;

    String city;
    String name;
    String standingsRank;
    Double teamppg;
    Double oppPPG;
    Double teamapg;
    Double teamrpg;
    Double team3pt;

    int numInside;
    int numOffense;
    int numDefense;
    int numRebounds;
    int numPassing;
    int numThrees;

    ArrayList<String> labels;

    final String BASE_URL = "https://api.mysportsfeeds.com/v1.2/pull/nba/2017-2018-regular/";
    OkHttpClient client;
    Retrofit retrofit;

    private static final String TAG = "TeamLandingPage";
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_landing_page);

        client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(getString(R.string.username), getString(R.string.password)))
                .build();

        String BASE_URL = "https://api.mysportsfeeds.com/v1.2/pull/nba/2017-2018-regular/";

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        int teamid = getIntent().getIntExtra("TeamID",0);
        int teamlogo = getIntent().getIntExtra("TeamLogo",0);
        teamcolors = getIntent().getIntExtra("TeamColors",0);
        teamName = getIntent().getStringExtra("TeamName");
        teamConference = getIntent().getIntExtra("TeamConference", -1);
        int teamInsideRank = getIntent().getIntExtra("PtsInPaintRank", -1);
        numInside = 31-teamInsideRank;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(getDrawable(teamcolors));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        franchiseName = (TextView)findViewById(R.id.franchiseName);
        winstv  = (TextView)findViewById(R.id.numWinsTextView);
        losestv = (TextView)findViewById(R.id.numLosesTextView);
        dashtv = (TextView) findViewById(R.id.dashTextView);
        ppgtv = (TextView)findViewById(R.id.ppgtextview);
        oppgtv = (TextView)findViewById(R.id.oppgtextview);
        apgtv = (TextView)findViewById(R.id.apgtextview);
        rpgtv =(TextView)findViewById(R.id.rpgtextview);
        tpatv = (TextView)findViewById(R.id.textview3pa);
        tpptv = (TextView)findViewById(R.id.textview3pp);
        teamranktv = (TextView)findViewById(R.id.teamStanding);
        teamRoster = (Button)findViewById(R.id.teamRosterButton) ;
        infotv = (TextView)findViewById(R.id.infoTextView);
        
        winstv.setTextColor(getColor(teamcolors));
        losestv.setTextColor(getColor(teamcolors));
        dashtv.setTextColor(getColor(teamcolors));
        teamRoster.setBackgroundColor(getColor(teamcolors));
        teamRoster.setTextColor(Color.WHITE);
        infotv.setTextColor(Color.BLACK);

        ImageView iv = (ImageView)findViewById(R.id.teamlogo);
        iv.setImageResource(teamlogo);
        if(teamName.equals("DAL"))
        {
            iv.getLayoutParams().width = 550;
            iv.getLayoutParams().height = 550;

            iv.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        if(InternetCheckerUtility.isNetworkAvailable(TeamLandingPage.this)==false){
            Toast.makeText(TeamLandingPage.this, "No Internet Connection", Toast.LENGTH_LONG).show();
            infotv.setText("No Internet Connection");
        }else{
            new AsyncFetchNBAData().execute();

        }
    }

    private class AsyncFetchNBAData extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... voids) {
            getTeamStats();
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Toast.makeText(TeamLandingPage.this, "Fetching data...", Toast.LENGTH_SHORT).show();
        }
    }

    public void getTeamStats(){
        SportsFeedAPI sportsFeedAPI = retrofit.create(SportsFeedAPI.class);
        String teamstatsparams = "W,L,PTS/G,AST/G,REB/G,3PA/G,3PM/G,PTS/G,PTSA/G";
        Call<Standings> call = sportsFeedAPI.getStandings(teamstatsparams, teamName);

        call.enqueue(new Callback<Standings>() {
            @Override
            public void onResponse(Call<Standings> call, Response<Standings> response) {
                Log.d(TAG, "onResponse: Server Response: " + response.toString());


                if(response.code()==200){
                    city = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getTeam().getCity();
                    name = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getTeam().getName();
                    franchiseName.setText(city + " " + name);

                    String numberOfWins = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getWins().getText();
                    winstv.setText(numberOfWins);

                    String numberOfLoses = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getLosses().getText();
                    losestv.setText(numberOfLoses);

                    teamppg = Double.parseDouble(response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getPtsPerGame().get(0).getText());
                    ppgtv.setText(teamppg + " Points Per Game");

                    oppPPG = Double.parseDouble(response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getPtsAgainstPerGame().getText());
                    oppgtv.setText(oppPPG + " Opponents PPG");

                    teamapg = Double.parseDouble(response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getAstPerGame().getText());
                    apgtv.setText(teamapg + " Assists Per Game");

                    teamrpg = Double.parseDouble(response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getRebPerGame().getText());
                    rpgtv.setText(teamrpg + " Rebounds Per Game" );

                    team3pt = Double.parseDouble(response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getStats().getFg3PtMadePerGame().getText());
                    tpatv.setText(team3pt + " 3Pt Made Per Game");

                    standingsRank = response.body().getConferenceteamstandings().getConference().get(teamConference).getTeamentry().get(0).getRank();
                    String conference;
                    if(teamConference == 0){
                        conference = "East";
                    }
                    else{
                        conference = "West";
                    }
                    standingsRank = "#" + standingsRank + " in the " + conference;
                    teamranktv.setText(standingsRank);
                    teamranktv.setTextColor(getColor(teamcolors));

                    getTeamStatsRankAndBindData();
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChooseYourTeam.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void getTeamStatsRankAndBindData(){
        SportsFeedAPI sportsFeedAPI = retrofit.create(SportsFeedAPI.class);
        String params = "PTS/G,PTSA/G,REB/G,AST/G,3PM/G";
        Call<Rankings> call = sportsFeedAPI.getStatsRank(params);

        call.enqueue(new Callback<Rankings>() {
            @Override
            public void onResponse(Call<Rankings> call, Response<Rankings> response) {
                Log.d(TAG, "onResponse: Server Response: " + response.toString());

                if(response.code()==200){
                    double[] rankOffense = new double[30];
                    double[] rankDefense = new double[30];
                    double[] rankRebound = new double[30];
                    double[] rankAssists = new double[30];
                    double[] rank3PTMade = new double[30];
                    int index = 0;

                    for(int i =0; i<2;i++){
                        for (int j= 0; j<15;j++){
                            rankOffense[index] = Double.parseDouble(response.body().getConferenceteamstandings().getConference().get(i).getTeamentry().get(j).getStats().getPtsPerGame().getText());
                            rankDefense[index] = Double.parseDouble(response.body().getConferenceteamstandings().getConference().get(i).getTeamentry().get(j).getStats().getPtsAgainstPerGame().getText());
                            rankRebound[index] = Double.parseDouble(response.body().getConferenceteamstandings().getConference().get(i).getTeamentry().get(j).getStats().getRebPerGame().getText());
                            rankAssists[index] = Double.parseDouble(response.body().getConferenceteamstandings().getConference().get(i).getTeamentry().get(j).getStats().getAstPerGame().getText());
                            rank3PTMade[index] = Double.parseDouble(response.body().getConferenceteamstandings().getConference().get(i).getTeamentry().get(j).getStats().getFg3PtMadePerGame().getText());
                            index++;
                        }
                    }
                    Arrays.sort(rankOffense);
                    Arrays.sort(rankDefense);
                    Arrays.sort(rankRebound);
                    Arrays.sort(rankAssists);
                    Arrays.sort(rank3PTMade);

                    for(int i=0; i<30; i++){
                        int offenseval = Double.compare(rankOffense[i],teamppg);
                        int defenseval = Double.compare(rankDefense[i],oppPPG);
                        int reboundval = Double.compare(rankRebound[i], teamrpg);
                        int passingval = Double.compare(rankAssists[i], teamapg);
                        int threesval = Double.compare(rank3PTMade[i], team3pt);

                        if (offenseval == 0){
                             numOffense = i;
                            Log.d(TAG, "offenseval: "+ offenseval);
                        }
                        if (defenseval == 0){
                            numDefense = i;
                            Log.d(TAG, "Defenseval: "+ defenseval);
                        }
                        if (reboundval == 0){
                            numRebounds = i;
                            Log.d(TAG, "Reboundval: "+ reboundval);
                        }
                        if (passingval == 0){
                            numPassing = i;
                            Log.d(TAG, "passingval: "+ passingval);
                        }
                        if (threesval == 0){
                            numThrees = i;
                            Log.d(TAG, "threesval: "+ threesval);
                        }
                    }
                    int dRank = 31 - numDefense;
                    Log.d(TAG, "ORank: " + numOffense + " DRank: " + dRank + " RRank: "+ numRebounds + " PRank: " + numPassing + " 3Rank: " + numThrees + " IRank " + numInside);


                    RadarChart chart = (RadarChart)findViewById(R.id.radarchart);

                    ArrayList<Entry> entry1 = new ArrayList<>();
                    entry1.add(new Entry( numRebounds,0));
                    entry1.add(new Entry(numOffense,1));
                    entry1.add(new Entry(dRank,2));
                    entry1.add(new Entry(numPassing,3));
                    entry1.add(new Entry(numInside,4));
                    entry1.add(new Entry(numThrees,5));

                    labels = new ArrayList<String>();
                    labels.add("Rebounds");
                    labels.add("Offense");
                    labels.add("Defense");
                    labels.add("Assists");
                    labels.add("Inside Scoring");
                    labels.add("3PT");

                    RadarDataSet dataset1 = new RadarDataSet(entry1,"Player");
                    dataset1.setColor(getColor(teamcolors));
                    dataset1.setDrawFilled(true);
                    dataset1.setFillColor(getColor(teamcolors));
                    dataset1.setFillAlpha(180);
                    dataset1.setLineWidth(5f);
                    dataset1.setDrawValues(false);

                    ArrayList<RadarDataSet> dataSets= new ArrayList<RadarDataSet>();
                    dataSets.add(dataset1);
                    RadarData theradardata = new RadarData(labels, dataSets);
                    chart.setData(theradardata);
                    chart.setDescription("");
                    Legend l = chart.getLegend();
                    l.setEnabled(false);

                    YAxis yAxis = chart.getYAxis();
                    yAxis.resetAxisMaxValue();
                    yAxis.setAxisMaxValue(30);
                    yAxis.setAxisMinValue(0);
                    yAxis.setDrawLabels(false);

                    chart.notifyDataSetChanged();
                    chart.invalidate();




                }
                else{
                    Log.d(TAG, "onResponse: Server Response " + response.toString());
                    Toast.makeText(TeamLandingPage.this, "Error " + response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Rankings> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());

            }
        });
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
