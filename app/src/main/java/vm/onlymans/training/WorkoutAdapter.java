package vm.onlymans.training;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {
    private List<Workout> items = new ArrayList<>();
    private OnWorkoutItemListener onWorkoutItemListener;
    public WorkoutAdapter(List<Workout> items, OnWorkoutItemListener onWorkoutItemListener) {
        this.items = items;
        this.onWorkoutItemListener = onWorkoutItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout, parent, false),onWorkoutItemListener
        ) { };
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.enumerated.setText(String.format("%s", position+1));
        holder.title.setText(String.format("%s",this.items.get(position).getlable()));
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView enumerated;
        TextView title;
        TextView time;
        OnWorkoutItemListener onWorkoutItemListener;
        public ViewHolder(@NonNull View itemView, OnWorkoutItemListener onWorkoutItemListener){
            super(itemView);
            enumerated = itemView.findViewById(R.id.textview_workout_position);
            title = itemView.findViewById(R.id.textview_workout_name);
            time = itemView.findViewById(R.id.textview_workout_date);
            this.onWorkoutItemListener = onWorkoutItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onWorkoutItemListener.onWorkoutItemClick(getAdapterPosition());
        }
    }
    public interface OnWorkoutItemListener{
        void onWorkoutItemClick(int position);
    }
}
