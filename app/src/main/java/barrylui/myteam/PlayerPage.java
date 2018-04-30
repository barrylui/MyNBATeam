package barrylui.myteam;

import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.HashMap;

import barrylui.myteam.TeamLandingPage.InternetCheckerUtility;
import barrylui.myteam.TeamLandingPage.TeamLandingPage;

public class PlayerPage extends AppCompatActivity {

    String TAG  = "PlayerPage";
    String mysportsteamid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_page);

        HashMap<String, Integer> colorMap = new HashMap<String, Integer>();
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

        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        String imageurl = getIntent().getStringExtra("imageurl");
        String teamidsuredbits = getIntent().getStringExtra("teamid");
        Log.d(TAG, "onCreate: " + firstName + " " + lastName + " " + imageurl);

        TextView jerseyNumbertextview = (TextView)findViewById(R.id.playerjerseynumber);
        TextView firstNametextview = (TextView)findViewById(R.id.firstNameTextView);
        TextView lastNametextview = (TextView)findViewById(R.id.lastNameTextView);
        TextView positiontextview = (TextView)findViewById(R.id.positiontextview);
        TextView heighttextview = (TextView)findViewById(R.id.heighttextview);
        TextView agetextview = (TextView)findViewById(R.id.agetextview);

        int color = getColor(colorMap.get(teamidsuredbits));
        firstNametextview.setTextColor(color);
        lastNametextview.setTextColor(color);
        jerseyNumbertextview.setTextColor(color);
        positiontextview.setTextColor(color);
        heighttextview.setTextColor(color);
        agetextview.setTextColor(color);

        firstNametextview.setText(firstName);
        lastNametextview.setText(lastName);


        TextView playertoolbartextview = (TextView)findViewById(R.id.playertoolbartextview);
        playertoolbartextview.setText(firstName + " " + lastName);
        Toolbar toolbar = (Toolbar) findViewById(R.id.playpagetoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(getDrawable(colorMap.get(teamidsuredbits)));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
            //Method call here
            return null;
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
