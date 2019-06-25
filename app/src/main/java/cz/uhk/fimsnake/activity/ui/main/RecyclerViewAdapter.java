package cz.uhk.fimsnake.activity.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.dao.Cache;
import cz.uhk.fimsnake.dao.CacheFactory;
import cz.uhk.fimsnake.model.user.Score;

/**
 * Created by Luboš Hájek in 2019
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private List<Score> scores;
    private List<Score> scoresCurrentUser;
    private int index = 0;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");
    private Cache cache;
    private List<Score> scoreOrderbyDate;

    public RecyclerViewAdapter(Context context) {
        cache = CacheFactory.getInstance();
        this.scores = cache.getAllScore();
        this.scoresCurrentUser = cache.getCurrentUserScore();
        this.scoreOrderbyDate = cache.getScoreOrderbyDate();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.score_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        switch (index) {
            case 1:
                /*
                 * My score
                 */
                viewHolder.date.setText(simpleDateFormat.format(scoresCurrentUser.get(i).getDate()));
                viewHolder.alias.setText(cache.getUser().getAlias());
                viewHolder.score.setText(scoresCurrentUser.get(i).getScore() + "");
                break;
            case 2:
                /*
                 * Best today
                 */
                viewHolder.date.setText(simpleDateFormat.format(scoreOrderbyDate.get(i).getDate()));
                viewHolder.alias.setText(scoreOrderbyDate.get(i).getUserAlias());
                viewHolder.score.setText(scoreOrderbyDate.get(i).getScore() + "");
                break;
            case 3:
                /*
                 * TOP 100
                 */
                viewHolder.date.setText(simpleDateFormat.format(scores.get(i).getDate()));
                viewHolder.alias.setText(scores.get(i).getUserAlias());
                viewHolder.score.setText(scores.get(i).getScore() + "");
                break;
            case 4:
                /*
                 * My statistics
                 */
                viewHolder.date.setText("Played games: " + cache.getScoreCount());
                viewHolder.alias.setText("Highest Record: " + cache.getBiggestScoreCurrentUser());
                viewHolder.score.setText("Average score: " + cache.getAverageScoreCurrentUser());
                break;
        }
    }

    @Override
    public int getItemCount() {
        switch (index) {
            case 1:
                /*
                 * My score
                 */
                return scoresCurrentUser.size();
            case 2:
                /*
                 * Best today
                 */
                return scoreOrderbyDate.size();
            case 3:
                /*
                 * TOP 100
                 */
                return scores.size();
            case 4:
                /*
                 * My statistics
                 */
                return 1;
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView alias;
        private TextView score;
        private TextView date;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            alias = itemView.findViewById(R.id.score_item_alias);
            score = itemView.findViewById(R.id.score_item_data);
            date = itemView.findViewById(R.id.score_item_time);
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
