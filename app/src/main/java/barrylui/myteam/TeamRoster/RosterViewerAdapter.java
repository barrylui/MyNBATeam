package barrylui.myteam.TeamRoster;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import barrylui.myteam.R;
import barrylui.myteam.TeamRoster.SuredBitsPlayerModel.PlayerInfoModel;

public class RosterViewerAdapter extends RecyclerView.Adapter<RosterViewerAdapter.RosterViewHolder>{
    private List<PlayerInfoModel> playerList = null;
    private Context mContext;
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
    public void onBindViewHolder(RosterViewHolder viewHolder, int position) {
        String theFirstName = playerList.get(position).getFirstName();
        String theLastName = playerList.get(position).getLastName();
        String playerId = String.valueOf(playerList.get(position).getPlayerId());
        String url = "https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/" + playerId + ".png";
        viewHolder.playerName.setText(theFirstName + " " + theLastName);
        Context context = viewHolder.playerPhoto.getContext();
        Picasso.with(context).load(url).placeholder(R.drawable.default_nba_headshot_v2).error(R.drawable.default_nba_headshot_v2).into(viewHolder.playerPhoto);
    }


    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public static class RosterViewHolder extends RecyclerView.ViewHolder
    {
        ImageView playerPhoto;
        TextView playerName;

        public RosterViewHolder(View view)
        {
            super(view);
            playerPhoto = (ImageView) view.findViewById(R.id.playerHeadShot);
            playerName = (TextView)view.findViewById(R.id.nameTextView);
        }
    }
}
