package barrylui.myteam;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;

import barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerInfoModel.PlayerInfo;
import barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerInfoModel.Rosterplayers;
import barrylui.myteam.MySportsFeedAPI.MySportsFeedPlayerStatsModel.PlayerStatsPerGame;
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
    TextView agetextview;
    TextView ppgapgrpgtextview;
    TextView stealsblockstextview;
    TextView minutesfreethrowsfieldgoalspergame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_page);


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

        mysportsfeedFirstName = suredBitsFirstName;
        mysportsfeedLastName = suredBitsLastName;

        jerseyNumbertextview = (TextView)findViewById(R.id.playerjerseynumber);
        firstNametextview = (TextView)findViewById(R.id.firstNameTextView);
        lastNametextview = (TextView)findViewById(R.id.lastNameTextView);
        positiontextview = (TextView)findViewById(R.id.positiontextview);
        heighttextview = (TextView)findViewById(R.id.heighttextview);

        firstNametextview.setText(suredBitsFirstName);
        lastNametextview.setText(suredBitsLastName);

        mysportsfeedFirstName = mysportsfeedFirstName.replace(" ", "");
        mysportsfeedFirstName = mysportsfeedFirstName.replace("-", "");
        mysportsfeedFirstName = mysportsfeedFirstName.replace("'", "");
        mysportsfeedFirstName = mysportsfeedFirstName.replace(".", "");
        Log.d(TAG, "onCreate: " + mysportsfeedFirstName);

        mysportsfeedLastName = mysportsfeedLastName.replace(" ","");
        mysportsfeedLastName = mysportsfeedLastName.replace("'", "");
        mysportsfeedLastName = mysportsfeedLastName.replace("-", "");
        mysportsfeedLastName = mysportsfeedLastName.replace(".", "");
        Log.d(TAG, "onCreate: " + mysportsfeedLastName);


        TextView seasonaveragestextview = (TextView) findViewById(R.id.seasonaveragetextview);
        agetextview = (TextView)findViewById(R.id.agetextview);
        ppgapgrpgtextview = (TextView)findViewById(R.id.ppgapgrpgtextview);
        stealsblockstextview = (TextView)findViewById(R.id.stealsblockstextview);
        minutesfreethrowsfieldgoalspergame = (TextView)findViewById(R.id.mpgftpfgptextview);

        int teamcolor = getColor(colorMap.get(teamidsuredbits));
        firstNametextview.setTextColor(teamcolor);
        lastNametextview.setTextColor(teamcolor);
        jerseyNumbertextview.setTextColor(teamcolor);
        positiontextview.setTextColor(teamcolor);
        heighttextview.setTextColor(teamcolor);
        agetextview.setTextColor(teamcolor);
        seasonaveragestextview.setTextColor(teamcolor);


        TextView playertoolbartextview = (TextView)findViewById(R.id.playertoolbartextview);
        playertoolbartextview.setText(suredBitsFirstName + " " + suredBitsLastName);
        Toolbar toolbar = (Toolbar) findViewById(R.id.playpagetoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(getDrawable(colorMap.get(teamidsuredbits)));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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

        if (teamidsuredbits.equals("OKC")){
            mysportsteamid = "OKL";
        } else if (teamidsuredbits.equals("BKN")){
            mysportsteamid = "BRO";
        } else{
            mysportsteamid = teamidsuredbits;
        }


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

        String name =  mysportsfeedFirstName + "-" + mysportsfeedLastName;
        Calendar calendar = Calendar.getInstance();
        String date = String.valueOf(calendar.get(Calendar.MONTH))+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+String.valueOf(calendar.get(Calendar.YEAR));
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

        String params = "PTS/G,AST/G,REB/G,STL/G,BS/G,MIN/G,FTM,FTA,FGM,FGA";
        Call<PlayerStatsPerGame> call2 = sportsFeedAPI.getPlayerStatsPerGame(name, params);
        call2.enqueue(new Callback<PlayerStatsPerGame>() {
            @Override
            public void onResponse(Call<PlayerStatsPerGame> call, Response<PlayerStatsPerGame> response) {
                Log.d(TAG, "onResponse: Server Response " + response.toString());

                if(response.code()==200){
                    /*
                    String jerseyNumber = response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getPlayer().getJerseyNumber();
                    if (jerseyNumber == null){
                        jerseyNumbertextview.setText("");
                    }else{
                        jerseyNumbertextview.setText("#" + jerseyNumber);
                    }
                    String playerpoistion = response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getPlayer().getPosition();
                    positiontextview.setText(playerpoistion + " | ");
*/
                    String pointspergame = response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getPtsPerGame().getText();
                    String assistspergame = response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getAstPerGame().getText();
                    String reboundspergame = response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getRebPerGame().getText();
                    ppgapgrpgtextview.setText(pointspergame + " PPG  " + assistspergame + " APG  " + reboundspergame + " RPG  ");

                    String stealspergame = response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getStlPerGame().getText();
                    String blockspergame = response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getBlkPerGame().getText();
                    stealsblockstextview.setText(stealspergame + " STL/G  " + blockspergame + " BLK/G");

                    double mpgseconds = Double.parseDouble(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getMinSecondsPerGame().getText());
                    double mpgminutes = mpgseconds/60;
                    DecimalFormat minutesFormat = new DecimalFormat("#.##");
                    String minutespergame = minutesFormat.format(mpgminutes);

                    int fga = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getFgAtt().getText());
                    int fgm = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getFgMade().getText());


                    DecimalFormat percentageFormat = new DecimalFormat("#.#");
                    double fgpercent= ((double)fgm/(double)fga)*100;
                    String fgpercentage = percentageFormat.format(fgpercent);
                    int fta = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getFtAtt().getText());
                    int ftm = Integer.parseInt(response.body().getCumulativeplayerstats().getPlayerstatsentry().get(0).getStats().getFtMade().getText());

                    double ftpercent = ((double)ftm/(double)fta)*100;
                    String ftpercentage = percentageFormat.format(ftpercent);
                    minutesfreethrowsfieldgoalspergame.setText(minutespergame + " MPG  " + ftpercentage + "% FT  " + fgpercentage + "% FG");
                }
                else{
                    Log.d(TAG, "onResponse: Server Response " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<PlayerStatsPerGame> call, Throwable t) {
                Log.e(TAG, "onFailure: something went wrong" + t.getMessage());
                Toast.makeText(PlayerPage.this, "something went wrong " + t.getMessage(), Toast.LENGTH_LONG).show();
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
