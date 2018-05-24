package barrylui.myteam;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

import barrylui.myteam.InternetUtilities.BasicAuthInterceptor;
import barrylui.myteam.InternetUtilities.InternetCheckerUtility;
import barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerInfoModel.PlayerInfo;
import barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerStatsModel.PlayerStats;
import barrylui.myteam.MySportsFeedAPI.SportsFeedAPI;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerPage extends AppCompatActivity {

    String TAG  = "PlayerPage";
    String mysportsteamid;
    HashMap<String, Integer> colorMap = new HashMap<String, Integer>();
    OkHttpClient client;
    Retrofit retrofit;
    final String BASE_URL = "https://api.mysportsfeeds.com/v1.2/pull/nba/2017-2018-regular/";
    String suredBitsFirstName;
    String suredBitsLastName;
    String mysportsfeedFirstName;
    String mysportsfeedLastName;
    TextView jerseyNumbertextview;
    TextView firstNametextview;
    TextView lastNametextview;
    TextView positiontextview;
    TextView heighttextview;
    TextView seasonaveragestextview;
    TextView agetextview;
    TextView ppgapgrpgtextview;
    TextView stealsblockstextview;
    TextView minutesfreethrowsfieldgoalspergame;
    RadarChart radarChart;
    String pointspergame ;
    String assistspergame;
    String reboundspergame;
    String stealspergame;
    String blockspergame;
    double pointsPer36;
    double assistsPer36;
    double reboundPer36;
    double stealPer36;
    double blockPer36;
    double ftpercent;
    ArrayList<String> labels;
    int teamcolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_page);


        //ColorMap to get theme color according to team abbreviation passed to this intent
        colorMap.put("MIL", R.color.colorBucksPrimary);
        colorMap.put("CHI", R.color.colorBullsPrimary);
        colorMap.put("CLE", R.color.colorCavaliersPrimary);
        colorMap.put("BOS",R.color.colorCelticsPrimary);
        colorMap.put("LAC",R.color.colorClippersPrimary);
        colorMap.put("MEM",R.color.colorGrizzliesPrimary);
        colorMap.put("ATL",R.color.colorHawksPrimary);
        colorMap.put("MIA",R.color.colorHeatPrimary);
        colorMap.put("CHA",R.color.colorHornetsPrimary);
        colorMap.put("UTA",R.color.colorJazzPrimary);
        colorMap.put("SAC",R.color.colorKingsPrimary);
        colorMap.put("NYK",R.color.colorKnicksPrimary);
        colorMap.put("LAL",R.color.colorLakersPrimary);
        colorMap.put("ORL",R.color.colorMagicPrimary);
        colorMap.put("DAL",R.color.colorMavericksPrimary);
        colorMap.put("BKN",R.color.colorNetsPrimary);
        colorMap.put("DEN",R.color.colorNuggetsPrimary);
        colorMap.put("IND",R.color.colorPacersPrimary);
        colorMap.put("NOP",R.color.colorPelicansPrimary);
        colorMap.put("DET",R.color.colorPistonsPrimary);
        colorMap.put("TOR",R.color.colorRaptorsPrimary);
        colorMap.put("HOU",R.color.colorRocketsPrimary);
        colorMap.put("PHI",R.color.colorSixersPrimary);
        colorMap.put("SAS",R.color.colorSpursPrimary);
        colorMap.put("PHX",R.color.colorSunsPrimary);
        colorMap.put("OKC",R.color.colorThunderPrimary);
        colorMap.put("POR",R.color.colorTrailblazersPrimary);
        colorMap.put("MIN",R.color.colorTimberwolvesPrimary);
        colorMap.put("GSW",R.color.colorWarriorsPrimary);
        colorMap.put("WAS",R.color.colorWizardsPrimary);
        //OKL -> OKC
        //BRO -> BKN

        suredBitsFirstName= getIntent().getStringExtra("firstName");
        suredBitsLastName = getIntent().getStringExtra("lastName");
        String imageurl = getIntent().getStringExtra("imageurl");
        String teamidsuredbits = getIntent().getStringExtra("teamid");

        //fix team appreviations for mysportsfeedapi
        if (teamidsuredbits.equals("OKC")){
            mysportsteamid = "OKL";
        } else if (teamidsuredbits.equals("BKN")){
            mysportsteamid = "BRO";
        } else{
            mysportsteamid = teamidsuredbits;
        }


        mysportsfeedFirstName = suredBitsFirstName;
        mysportsfeedLastName = suredBitsLastName;

        jerseyNumbertextview = (TextView)findViewById(R.id.playerjerseynumber);
        firstNametextview = (TextView)findViewById(R.id.firstNameTextView);
        lastNametextview = (TextView)findViewById(R.id.lastNameTextView);
        positiontextview = (TextView)findViewById(R.id.positiontextview);
        heighttextview = (TextView)findViewById(R.id.heighttextview);

        firstNametextview.setText(suredBitsFirstName);
        lastNametextview.setText(suredBitsLastName);

        //Replace certain chars that are returned by the suredbitsAPI.
        //removes occurences of " ", "'", "." in names so mysportsfeedapi can return data
        mysportsfeedFirstName = mysportsfeedFirstName.replace(" ", "");
        mysportsfeedFirstName = mysportsfeedFirstName.replace("-", "");
        mysportsfeedFirstName = mysportsfeedFirstName.replace("'", "");
        mysportsfeedFirstName = mysportsfeedFirstName.replace(".", "");

        mysportsfeedLastName = mysportsfeedLastName.replace(" ","");
        mysportsfeedLastName = mysportsfeedLastName.replace("'", "");
        mysportsfeedLastName = mysportsfeedLastName.replace("-", "");
        mysportsfeedLastName = mysportsfeedLastName.replace(".", "");



        radarChart = (RadarChart)findViewById(R.id.playerradarchart);

        seasonaveragestextview = (TextView) findViewById(R.id.seasonaveragetextview);
        agetextview = (TextView)findViewById(R.id.agetextview);
        ppgapgrpgtextview = (TextView)findViewById(R.id.ppgapgrpgtextview);
        stealsblockstextview = (TextView)findViewById(R.id.stealsblockstextview);
        minutesfreethrowsfieldgoalspergame = (TextView)findViewById(R.id.mpgftpfgptextview);

        teamcolor = getColor(colorMap.get(teamidsuredbits));
        firstNametextview.setTextColor(Color.WHITE);
        lastNametextview.setTextColor(Color.WHITE);
        jerseyNumbertextview.setTextColor(teamcolor);
        positiontextview.setTextColor(Color.WHITE);
        heighttextview.setTextColor(Color.WHITE);
        agetextview.setTextColor(Color.WHITE);
        seasonaveragestextview.setTextColor(Color.WHITE);
        ppgapgrpgtextview.setTextColor(Color.WHITE);
        stealsblockstextview.setTextColor(Color.WHITE);
        minutesfreethrowsfieldgoalspergame.setTextColor(Color.WHITE);

        TextView playertoolbartextview = (TextView)findViewById(R.id.playertoolbartextview);
        playertoolbartextview.setText(suredBitsFirstName + " " + suredBitsLastName);
        Toolbar toolbar = (Toolbar) findViewById(R.id.playpagetoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(getDrawable(colorMap.get(teamidsuredbits)));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(teamcolor);


        client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor("blui", "gdorf151"))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImageView playerPhoto = (ImageView)findViewById(R.id.playerphoto);
        Picasso.with(this).load(imageurl).placeholder(R.drawable.default_nba_headshot_v2).error(R.drawable.default_nba_headshot_v2).into(playerPhoto);



        //Call roster players api

        if(InternetCheckerUtility.isNetworkAvailable(PlayerPage.this)==false){
            //Display no internet connection and disable teamroster button;
            Toast.makeText(PlayerPage.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }else{
            new AsyncFetchPlayerData().execute();
        }
    }
    private class AsyncFetchPlayerData extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... voids) {
            fetchNBAPlayerDataAndBind();
            return null;
        }
    }

    public void fetchNBAPlayerDataAndBind(){
        SportsFeedAPI sportsFeedAPI = retrofit.create(SportsFeedAPI.class);

        //Get Player Vital info
        String name =  mysportsfeedFirstName + "-" + mysportsfeedLastName;
        Calendar calendar = Calendar.getInstance();
        //String date = String.valueOf(calendar.get(Calendar.MONTH))+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+String.valueOf(calendar.get(Calendar.YEAR));
        String date = "20180420";
        Call<PlayerInfo> call = sportsFeedAPI.getPlayerInfo(date, name);
        call.enqueue(new Callback<PlayerInfo>() {
            @Override
            public void onResponse(Call<PlayerInfo> call, Response<PlayerInfo> response) {
                Log.d(TAG, "onResponse: Server Response " + response.toString());

                if(response.code()==200){
                    String jerseyNumber = response.body().getRosterplayers().getPlayerentry().get(0).getPlayer().getJerseyNumber();
                    if (jerseyNumber == null){
                        jerseyNumbertextview.setText("");
                    }else{
                        jerseyNumbertextview.setText("#" + jerseyNumber);
                    }

                    String playerHeight = response.body().getRosterplayers().getPlayerentry().get(0).getPlayer().getHeight();
                    heighttextview.setText(playerHeight);

                    String playerAge = response.body().getRosterplayers().getPlayerentry().get(0).getPlayer().getAge();
                    agetextview.setText("Age: " + playerAge);

                    String playerPosition = response.body().getRosterplayers().getPlayerentry().get(0).getPlayer().getPosition();
                    positiontextview.setText(playerPosition+ " | ");
                }
                else{
                    Log.d(TAG, "onResponse: Server Response " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<PlayerInfo> call, Throwable t) {
                Log.e(TAG, "onFailure: something went wrong" + t.getMessage());
                Toast.makeText(PlayerPage.this, "something went wrong " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        //Get Player per game stats and total stats
        String params = "PTS/G,AST/G,REB/G,STL/G,BS/G,MIN/G,FTM,FTA,FGM,FGA,PTS,AST,REB,STL,BS,MIN";
        Call<PlayerStats> call2 = sportsFeedAPI.getPlayerStatsPerGame(name, params);
        call2.enqueue(new Callback<PlayerStats>() {
            @Override
            public void onResponse(Call<PlayerStats> call, Response<PlayerStats> response) {
                Log.d(TAG, "onResponse: Server Response " + response.toString());

                if(response.code()==200){
                    pointspergame = response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getPtsPerGame().getText();
                    assistspergame = response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getAstPerGame().getText();
                    reboundspergame = response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getRebPerGame().getText();
                    ppgapgrpgtextview.setText(pointspergame + " PPG  " + assistspergame + " APG  " + reboundspergame + " RPG  ");

                    stealspergame = response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getStlPerGame().getText();
                    blockspergame = response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getBlkPerGame().getText();
                    stealsblockstextview.setText(stealspergame + " SPG  " + blockspergame + " BPG");

                    double mpgseconds = Double.parseDouble(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getMinSecondsPerGame().getText());
                    double mpgminutes = mpgseconds/60;
                    DecimalFormat decimalFormat = new DecimalFormat("#.#");
                    String minutespergame = decimalFormat.format(mpgminutes);

                    int fga = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getFgAtt().getText());
                    int fgm = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getFgMade().getText());

                    double fgpercent= ((double)fgm/(double)fga)*100;
                    String fgpercentage = decimalFormat.format(fgpercent);
                    int fta = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getFtAtt().getText());
                    int ftm = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getFtMade().getText());

                    ftpercent = ((double)ftm/(double)fta)*100;
                    String ftpercentage = decimalFormat.format(ftpercent);
                    minutesfreethrowsfieldgoalspergame.setText(minutespergame + " MPG  " + ftpercentage + "% FT  " + fgpercentage + "% FG");


                    seasonaveragestextview.setText("Season Averages");

                    int min36InSecs = 2160;
                    int totalseconds = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getMinSeconds().getText());
                    int totalpoints = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getPts().getText());
                    int totalassists = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getAst().getText());
                    int totalrebounds = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getReb().getText());
                    int totalsteals = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getStl().getText());
                    int totalblocks = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getBlk().getText());

                    pointsPer36 = ((double)totalpoints * (double)min36InSecs)/(double)totalseconds;
                    assistsPer36 = ((double)totalassists * (double)min36InSecs)/(double)totalseconds;
                    reboundPer36 = ((double)totalrebounds * (double)min36InSecs)/(double)totalseconds;
                    stealPer36 = ((double)totalsteals * (double)min36InSecs)/(double)totalseconds;
                    blockPer36 = ((double)totalblocks * (double)min36InSecs)/(double)totalseconds;

                    if(Double.isNaN(pointsPer36)){
                        pointsPer36 = 0.0;
                    }

                    if(Double.isNaN(assistsPer36)){
                        assistsPer36 = 0.0;
                    }

                    if(Double.isNaN(reboundPer36)){
                        reboundPer36 = 0.0;
                    }
                    if(Double.isNaN(stealPer36)){
                        stealPer36 = 0.0;
                    }

                    if(Double.isNaN(blockPer36)){
                        blockPer36 = 0.0;
                    }

                    Log.d(TAG, "per36: " + "pts " + String.valueOf(pointsPer36) + "asts " + String.valueOf(assistsPer36) + "reb " + String.valueOf(reboundPer36) + "stls " + String.valueOf(stealPer36) + "");

                    getPlayerStatsRankAndBindToChart();
                }
                else{
                    Log.d(TAG, "onResponse: Server Response " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<PlayerStats> call, Throwable t) {
                Log.e(TAG, "onFailure: something went wrong" + t.getMessage());
                Toast.makeText(PlayerPage.this, "something went wrong " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getPlayerStatsRankAndBindToChart(){
        SportsFeedAPI sportsFeedAPI = retrofit.create(SportsFeedAPI.class);
        String params = "PTS/G,AST/G,REB/G,STL/G,BS/G,FTA,FTM";
        Call<PlayerStats> call = sportsFeedAPI.getAllPlayerStats(params);

        call.enqueue(new Callback<PlayerStats>() {
            @Override
            public void onResponse(Call<PlayerStats> call, Response<PlayerStats> response) {
                Log.d(TAG, "onResponse: Server Response: " + response.toString());

                if(response.code()==200){
                    double[] rankPPG = new double[616];
                    double[] rankAPG = new double[616];
                    double[] rankRPG = new double[616];
                    double[] rankSPG = new double[616];
                    double[] rankBSG = new double[616];

                    for(int i =0; i<rankPPG.length;i++) {
                        rankPPG[i] = Double.parseDouble(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(i).getStats().getPtsPerGame().getText());
                        rankAPG[i] = Double.parseDouble(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(i).getStats().getAstPerGame().getText());
                        rankRPG[i] = Double.parseDouble(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(i).getStats().getRebPerGame().getText());
                        rankSPG[i] = Double.parseDouble(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(i).getStats().getStlPerGame().getText());
                        rankBSG[i] = Double.parseDouble(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(i).getStats().getBlkPerGame().getText());
                    }

                    Arrays.sort(rankPPG);
                    Arrays.sort(rankAPG);
                    Arrays.sort(rankRPG);
                    Arrays.sort(rankSPG);
                    Arrays.sort(rankBSG);


                    Log.d(TAG, "LEADERS: " + "Points "+ rankPPG[615] + "AST" + Double.toString(rankAPG[615]) + " REB" + String.valueOf(rankRPG[615]) + " STEALS "+ String.valueOf(rankSPG[615]) + " BLOCKS " + String.valueOf(rankBSG[615]));
                    double pointLeaderValue = rankPPG[615];
                    double assistLeaderValue = rankAPG[615];
                    double reboundLeaderValue = rankRPG[615];
                    double stealsLeaderValue = rankSPG[615];
                    double blocksLeaderValue = rankBSG[615];

                    double pointMultiplicationFactor = (double)100/pointLeaderValue;
                    double assistsMultiplicationFactor = (double)100/assistLeaderValue;
                    double reboundMultiplicationFactor = (double)100/reboundLeaderValue;
                    double stealsMultiplicationFactor = (double)100/stealsLeaderValue;
                    double blocksMultiplicationFactor = (double)100/blocksLeaderValue;
                    double freeThrowMultiplicationFactor = (double)100/(double)55;

                    double pointValue = Double.parseDouble(pointspergame) * pointMultiplicationFactor;
                    double assistValue = Double.parseDouble(assistspergame) * assistsMultiplicationFactor;
                    double reboundVale = Double.parseDouble(reboundspergame) * reboundMultiplicationFactor;
                    double stealsValue = Double.parseDouble(stealspergame) * stealsMultiplicationFactor;
                    double blocksValue = Double.parseDouble(blockspergame) * blocksMultiplicationFactor;
                    double freethrow = ftpercent - (double)40;
                    double freeThrowValue = freethrow * freeThrowMultiplicationFactor;

                    labels = new ArrayList<String>();
                    labels.add("Scoring");
                    labels.add("Asissts");
                    labels.add("Rebounding");
                    labels.add("Steals");
                    labels.add("Blocks");
                    labels.add("Free Throws");

                    ArrayList<Entry> entry1 = new ArrayList<>();
                    entry1.add(new Entry((float)pointValue,0));
                    entry1.add(new Entry((float)assistValue,1));
                    entry1.add(new Entry((float)reboundVale,2));
                    entry1.add(new Entry((float)stealsValue,3));
                    entry1.add(new Entry((float)blocksValue,4));
                    entry1.add(new Entry((float)freeThrowValue,5));





                    RadarDataSet dataset1 = new RadarDataSet(entry1,"Player");
                    dataset1.setColor((teamcolor));
                    dataset1.setDrawFilled(true);
                    dataset1.setFillColor((teamcolor));

                    if (mysportsteamid.equals("BRO") || mysportsteamid.equals("SAS")){
                        firstNametextview.setTextColor(Color.WHITE);
                        lastNametextview.setTextColor(Color.WHITE);
                        jerseyNumbertextview.setTextColor(Color.WHITE);
                        positiontextview.setTextColor(Color.WHITE);
                        heighttextview.setTextColor(Color.WHITE);
                        agetextview.setTextColor(Color.WHITE);
                        seasonaveragestextview.setTextColor(Color.WHITE);
                        ppgapgrpgtextview.setTextColor(Color.WHITE);
                        minutesfreethrowsfieldgoalspergame.setTextColor(Color.WHITE);
                        stealsblockstextview.setTextColor(Color.WHITE);
                        dataset1.setColor((Color.WHITE));
                        dataset1.setDrawFilled(true);
                        dataset1.setFillColor((Color.WHITE));
                    }

                    dataset1.setFillAlpha(180);
                    dataset1.setLineWidth(5f);
                    dataset1.setDrawValues(false);

                    ArrayList<RadarDataSet> dataSets= new ArrayList<RadarDataSet>();
                    dataSets.add(dataset1);
                    RadarData theradardata = new RadarData(labels, dataSets);
                    radarChart.setData(theradardata);
                    radarChart.setDescription("");
                    Legend l = radarChart.getLegend();
                    l.setEnabled(false);


                    radarChart.getXAxis().setTextColor(Color.WHITE);
                    YAxis yAxis = radarChart.getYAxis();
                    yAxis.resetAxisMaxValue();
                    yAxis.setAxisMaxValue(100);
                    yAxis.setAxisMinValue(0);
                    yAxis.setDrawLabels(false);

                    radarChart.notifyDataSetChanged();
                    radarChart.invalidate();
                    TextView loadingtextview = (TextView)findViewById(R.id.loadingtextview);
                    ((ViewManager)loadingtextview.getParent()).removeView(loadingtextview);
                }
                else{
                    Log.d(TAG, "onResponse: Server Response " + response.toString());
                    Toast.makeText(PlayerPage.this, "Error " + response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PlayerStats> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());
                Toast.makeText(PlayerPage.this, "Something went wrong" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
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
