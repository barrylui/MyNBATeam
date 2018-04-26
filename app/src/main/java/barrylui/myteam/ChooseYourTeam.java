package barrylui.myteam;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import barrylui.myteam.TeamLandingPage.TeamLandingPage;

public class ChooseYourTeam extends AppCompatActivity implements View.OnClickListener{

    public MediaPlayer soundPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_your_team);

        //Setup NBA Team Buttons
        Button buttonBucks = (Button)findViewById(R.id.bucksbutton);
        Button buttonBulls = (Button)findViewById(R.id.bullsbutton);
        Button buttonCavs = (Button)findViewById(R.id.cavsbutton);
        Button buttonCeltics = (Button)findViewById(R.id.celticsbutton);
        Button buttonClippers = (Button)findViewById(R.id.clippersbutton);
        Button buttonGrizzlies = (Button)findViewById(R.id.grizzliesbutton);
        Button buttonHawks = (Button)findViewById(R.id.hawksbutton);
        Button buttonHeat = (Button)findViewById(R.id.heatbutton);
        Button buttonHornets = (Button)findViewById(R.id.hornetsbutton);
        Button buttonJazz = (Button)findViewById(R.id.jazzbutton);
        Button buttonKings = (Button)findViewById(R.id.kingsbutton);
        Button buttonKnicks = (Button)findViewById(R.id.knicksbutton);
        Button buttonLakers = (Button)findViewById(R.id.lakersbutton);
        Button buttonMagic = (Button)findViewById(R.id.magicbutton);
        Button buttonMavericks = (Button)findViewById(R.id.mavsbutton);
        Button buttonNets = (Button)findViewById(R.id.netsbutton);
        Button buttonNuggets = (Button)findViewById(R.id.nuggetsbutton);
        Button buttonPacers = (Button)findViewById(R.id.pacersbutton);
        Button buttonPelicans = (Button)findViewById(R.id.pelicansbutton);
        Button buttonPistons = (Button)findViewById(R.id.pistonbutton);
        Button buttonRaptors = (Button)findViewById(R.id.raptorsbutton);
        Button buttonRockets = (Button)findViewById(R.id.rocketsbutton);
        Button buttonSixers = (Button)findViewById(R.id.sixersbutton);
        Button buttonSpurs = (Button)findViewById(R.id.spursbutton);
        Button buttonSuns = (Button)findViewById(R.id.sunsbutton);
        Button buttonThunder = (Button)findViewById(R.id.thunderbutton);
        Button buttonTrailBlazers = (Button)findViewById(R.id.trailblazersbutton);
        Button buttonTimberWolves = (Button)findViewById(R.id.timberwolvesbutton);
        Button buttonWarriors = (Button)findViewById(R.id.warriorsbutton);
        Button buttonWizards = (Button)findViewById(R.id.wizardsbutton);


        buttonBucks.setOnClickListener(this);
        buttonBulls.setOnClickListener(this);
        buttonCavs.setOnClickListener(this);
        buttonCeltics.setOnClickListener(this);
        buttonClippers.setOnClickListener(this);
        buttonGrizzlies.setOnClickListener(this);
        buttonHawks.setOnClickListener(this);
        buttonHeat.setOnClickListener(this);
        buttonHornets.setOnClickListener(this);
        buttonJazz.setOnClickListener(this);
        buttonKings.setOnClickListener(this);
        buttonKnicks.setOnClickListener(this);
        buttonLakers.setOnClickListener(this);
        buttonMagic.setOnClickListener(this);
        buttonMavericks.setOnClickListener(this);
        buttonNets.setOnClickListener(this);
        buttonNuggets.setOnClickListener(this);
        buttonPacers.setOnClickListener(this);
        buttonPelicans.setOnClickListener(this);
        buttonPistons.setOnClickListener(this);
        buttonRaptors.setOnClickListener(this);
        buttonRockets.setOnClickListener(this);
        buttonSuns.setOnClickListener(this);
        buttonSixers.setOnClickListener(this);
        buttonSpurs.setOnClickListener(this);
        buttonSixers.setOnClickListener(this);
        buttonThunder.setOnClickListener(this);
        buttonTrailBlazers.setOnClickListener(this);
        buttonTimberWolves.setOnClickListener(this);
        buttonWarriors.setOnClickListener(this);
        buttonWizards.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int id=0;
        Intent teamPage = new Intent(this, TeamLandingPage.class);
        ActivityOptions options;
        switch(v.getId()){
            case R.id.bucksbutton:
                id = R.integer.bucksID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.bucks_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_buckslogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorBucksPrimary);
                teamPage.putExtra("PtsInPaintRank", 7);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.bullsbutton:
                id = R.integer.bullsID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.bulls_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_bullslogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorBullsPrimary);
                teamPage.putExtra("PtsInPaintRank", 26);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.cavsbutton:
                id = R.integer.cavaliersID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.cavaliers_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_cavslogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorCavaliersPrimary);
                teamPage.putExtra("PtsInPaintRank", 17);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.celticsbutton:
                id = R.integer.celticsID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.celtics_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_celticslogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorCelticsPrimary);
                teamPage.putExtra("PtsInPaintRank", 29);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.clippersbutton:
                id = R.integer.clippersID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.clippers_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_clipperlogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorClippersPrimary);
                teamPage.putExtra("PtsInPaintRank", 2);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.grizzliesbutton:
                id = R.integer.grizzliesID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.grizzlies_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_grizzlies);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorGrizzliesPrimary);
                teamPage.putExtra("PtsInPaintRank", 25);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.hawksbutton:
                id = R.integer.hawksID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.hawks_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_hawkslogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorHawksPrimary);
                teamPage.putExtra("PtsInPaintRank", 27);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.heatbutton:
                id = R.integer.heatID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.heat_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_heatlogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorHeatPrimary);
                teamPage.putExtra("PtsInPaintRank", 13);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.hornetsbutton:
                id = R.integer.hornetsID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.hornets_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_hornetslogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorHornetsPrimary);
                teamPage.putExtra("PtsInPaintRank", 16);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.jazzbutton:
                id = R.integer.jazzID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.jazz_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_jazzlogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorJazzPrimary);
                teamPage.putExtra("PtsInPaintRank", 14);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.kingsbutton:
                id = R.integer.kingsID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.kings_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_kingslogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorKingsPrimary);
                teamPage.putExtra("PtsInPaintRank", 21);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());

                break;
            case R.id.knicksbutton:
                id = R.integer.knicksID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", R.string.knicks_name);
                teamPage.putExtra("TeamLogo", R.drawable.ic_knickslogo);
                teamPage.putExtra("TeamName", getString(R.string.knicks_name));
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorKnicksPrimary);
                teamPage.putExtra("PtsInPaintRank", 9);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.lakersbutton:
                id = R.integer.lakersID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.lakers_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_lakerslogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorLakersPrimary);
                teamPage.putExtra("PtsInPaintRank", 3);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.magicbutton:
                id = R.integer.magicID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.magic_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_magiclogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorMagicPrimary);
                teamPage.putExtra("PtsInPaintRank", 11);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.mavsbutton:
                id = R.integer.mavericksID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.mavericks_name));
                teamPage.putExtra("TeamLogo", R.drawable.mavslogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorMavericksPrimary);
                teamPage.putExtra("PtsInPaintRank", 30);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.netsbutton:
                id = R.integer.netsID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.nets_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_netslogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorNetsPrimary);
                teamPage.putExtra("PtsInPaintRank", 15);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.nuggetsbutton:
                id = R.integer.nuggetsID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.nuggets_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_nuggetslogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorNuggetsPrimary);
                teamPage.putExtra("PtsInPaintRank", 5);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.pacersbutton:
                id = R.integer.pacersID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.pacers_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_pacerslogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorPacersPrimary);
                teamPage.putExtra("PtsInPaintRank", 12);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.pelicansbutton:
                id = R.integer.pelicansID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.pelicans_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_pelicanslogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorPelicansPrimary);
                teamPage.putExtra("PtsInPaintRank", 1);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.pistonbutton:
                id = R.integer.pistonsID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.pistons_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_pistonslogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorPistonsPrimary);
                teamPage.putExtra("PtsInPaintRank", 20);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.raptorsbutton:
                id = R.integer.raptorsID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.raptor_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_raptorslogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorRaptorsPrimary);
                teamPage.putExtra("PtsInPaintRank", 6);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.rocketsbutton:
                id = R.integer.rocketsID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.rockets_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_rocketslogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorRocketsPrimary);
                teamPage.putExtra("PtsInPaintRank", 28);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.sixersbutton:
                id = R.integer.sixersID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.sixers_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_sixerslogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorSixersPrimary);
                teamPage.putExtra("PtsInPaintRank", 8);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.spursbutton:
                id = R.integer.spursID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.spurs_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_spurslogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorSpursPrimary);
                teamPage.putExtra("PtsInPaintRank", 22);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.sunsbutton:
                id = R.integer.sunsID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.suns_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_sunslogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorSunsPrimary);
                teamPage.putExtra("PtsInPaintRank", 10);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.thunderbutton:
                id = R.integer.thunderID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.thunder_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_thunderlogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorThunderPrimary);
                teamPage.putExtra("PtsInPaintRank", 18);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.trailblazersbutton:
                id = R.integer.trailblazersID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.trailbalzer_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_trailblazerslogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorTrailblazersPrimary);
                teamPage.putExtra("PtsInPaintRank", 23);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.timberwolvesbutton:
                id = R.integer.timberwolvesID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.timberwolves_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_timberwolveslogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorTimberwolvesPrimary);
                teamPage.putExtra("PtsInPaintRank", 4);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.warriorsbutton:
                id = R.integer.warriorsID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.warriors_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_warriorslogo);
                teamPage.putExtra("TeamConference",1);
                teamPage.putExtra("TeamColors", R.color.colorWarriorsPrimary);
                teamPage.putExtra("PtsInPaintRank", 19);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            case R.id.wizardsbutton:
                id = R.integer.wizardsID;
                teamPage.putExtra("TeamID",id);
                teamPage.putExtra("TeamName", getString(R.string.wizards_name));
                teamPage.putExtra("TeamLogo", R.drawable.ic_wizardslogo);
                teamPage.putExtra("TeamConference",0);
                teamPage.putExtra("TeamColors", R.color.colorWizardsPrimary);
                teamPage.putExtra("PtsInPaintRank", 24);
                options = ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "logo");
                startActivity(teamPage, options.toBundle());
                break;
            default:
                break;
        }
    }
}
