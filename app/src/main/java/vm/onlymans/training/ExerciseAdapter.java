package vm.onlymans.training;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder>{
    private ArrayList<Exercise> exercises;
    private ExerciseAdapter.OnExerciseItemListener onExerciseItemListener;
    public ExerciseAdapter(ArrayList<Exercise> exercises, ExerciseAdapter.OnExerciseItemListener onExerciseItemListener){
        this.exercises = exercises;
        this.onExerciseItemListener = onExerciseItemListener;
    }
    @NonNull
    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExerciseAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false),onExerciseItemListener
        ) { };
    }
    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder holder, int position) {
        holder.enumerated.setText(String.format("%s", position+1));
        holder.title.setText(String.format("%s",this.exercises.get(position).getName_of_exercise()));
    }
    @Override
    public int getItemCount() {
        return this.exercises.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView enumerated;
        TextView title;
        ImageButton delete_btn;
        ExerciseAdapter.OnExerciseItemListener onExerciseItemListener;
        public ViewHolder(@NonNull View itemView, ExerciseAdapter.OnExerciseItemListener onExerciseItemListener){
            super(itemView);
            enumerated = itemView.findViewById(R.id.textview_exercise_position);
            title = itemView.findViewById(R.id.textview_exercise_name);
            delete_btn = itemView.findViewById(R.id.imageButton_change_exercise);
            this.onExerciseItemListener = onExerciseItemListener;
            itemView.setOnClickListener(this);
            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onExerciseItemListener.onExerciseButtonClick(getAdapterPosition());
                }
            });

        }

        @Override
        public void onClick(View view) {
            onExerciseItemListener.onExerciseItemClick(getAdapterPosition());
        }
    }
    public interface OnExerciseItemListener{
        void onExerciseItemClick(int position);
        void onExerciseButtonClick(int position);
    }
}
