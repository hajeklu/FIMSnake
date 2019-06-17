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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.uhk.fimsnake.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private Context context;
    private List<Integer> scores = new ArrayList<>();
    private List<Date> dates = new ArrayList<>();
    private List<String> aliass = new ArrayList<>();

    public RecyclerViewAdapter(Context context, List<Integer> scores, List<Date> dates, List<String> aliass) {
        this.context = context;
        this.scores = scores;
        this.dates = dates;
        this.aliass = aliass;
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

        viewHolder.date.setText(dates.get(i).toString());
        viewHolder.alias.setText(aliass.get(i));
        viewHolder.score.setText(scores.get(i).toString());

    }

    @Override
    public int getItemCount() {
        return scores.size();
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
}
