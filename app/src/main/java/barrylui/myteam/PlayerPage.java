package barrylui.myteam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

public class PlayerPage extends AppCompatActivity {

    String TAG  = "PlayerPage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_page);
        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        String imageurl = getIntent().getStringExtra("imageurl");
        String teamid = getIntent().getStringExtra("teamid");
        Log.d(TAG, "onCreate: " + firstName + " " + lastName + " " + imageurl);
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
        colorMap.put("",R.color.colorKnicksPrimary);
        colorMap.put("",R.color.colorLakersPrimary);
        colorMap.put("",R.color.colorPacersPrimary);
        colorMap.put("",R.color.colorPelicansPrimary);
        colorMap.put("",R.color.colorPistonsPrimary);
        colorMap.put("",R.color.colorRaptorsPrimary);
        colorMap.put("",R.color.colorRocketsPrimary);
        colorMap.put("",R.color.colorSixersPrimary);
        colorMap.put("",R.color.colorSpursPrimary);
        colorMap.put("",R.color.colorSunsPrimary);
        colorMap.put("",R.color.colorThunderPrimary);
        colorMap.put("",R.color.colorTrailblazersPrimary);
        colorMap.put("",R.color.colorTimberwolvesPrimary);
        colorMap.put("",R.color.colorWarriorsPrimary);
        colorMap.put("",R.color.colorWizardsPrimary);





    }
}
