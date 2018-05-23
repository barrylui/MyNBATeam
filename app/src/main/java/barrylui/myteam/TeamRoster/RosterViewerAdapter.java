package barrylui.myteam.TeamRoster;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import barrylui.myteam.PlayerPage;
import barrylui.myteam.R;
import barrylui.myteam.SuredBitsAPI.SuredBitsPlayerModel.PlayerInfoModel;


public class RosterViewerAdapter extends RecyclerView.Adapter<RosterViewerAdapter.RosterViewHolder>{
    private List<PlayerInfoModel> playerList = null;
    private Context mContext;
    private static final String TAG = "RosterView";
    //OnItemClickListener mItemClickListener;

    public RosterViewerAdapter(Context context, List<PlayerInfoModel> list){
        playerList = list;
        mContext = context;
    }


    @Override
    public RosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View playerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_player, parent, false);
        return new RosterViewHolder(playerView);
    }

    @Override
    public void onBindViewHolder(final RosterViewHolder viewHolder, final int position) {
        final String theFirstName = playerList.get(position).getFirstName();
        final String theLastName = playerList.get(position).getLastName();
        final String teamId = playerList.get(position).getTeam();
        Log.d(TAG, "onBindViewHolder: " + teamId);
        String playerId = String.valueOf(playerList.get(position).getPlayerId());

        final String url = "https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/" + playerId + ".png";
        viewHolder.playerName.setText(theFirstName + " " + theLastName);
        viewHolder.linearLayout.setTag(position);
        final Context context = viewHolder.playerPhoto.getContext();
        //Load default heaadshot if player does not have offical picture
        Picasso.with(context).load(url).placeholder(R.drawable.default_nba_headshot_v2).error(R.drawable.default_nba_headshot_v2).into(viewHolder.playerPhoto);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int tag = (int)v.getTag();
                Log.d(TAG, "onClick: " + tag);
                Intent playerPageIntent = new Intent (v.getContext(), PlayerPage.class);
                playerPageIntent.putExtra("firstName", theFirstName);
                playerPageIntent.putExtra("lastName", theLastName);
                playerPageIntent.putExtra("imageurl", url);
                playerPageIntent.putExtra("teamid", teamId);
                v.getContext().startActivity(playerPageIntent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public static class RosterViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/
    {
        ImageView playerPhoto;
        TextView playerName;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;
        //GradientDrawableDrawable circleBackground;

        public RosterViewHolder(View view) {
            super(view);
            playerPhoto = (ImageView) view.findViewById(R.id.playerHeadShot);
            playerName = (TextView) view.findViewById(R.id.nameTextView);
            linearLayout = (LinearLayout) view.findViewById(R.id.cardview);
            //view.setOnClickListener(this);

        }
    }
}
