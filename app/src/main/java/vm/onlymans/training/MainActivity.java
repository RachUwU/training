package vm.onlymans.training;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WorkoutAdapter.OnWorkoutItemListener {
    private static final String TAG = "MainActivity";
    private final List<Workout> items = new ArrayList<>();
    private RecyclerView.Adapter adapter = new WorkoutAdapter(this.items, this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recyclerview_workout);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }
    public void add(View view){
        EditText editText = findViewById(R.id.edittext_workoutname);
        String workout_name = editText.getText().toString();
        if(workout_name.equals("")){
            workout_name = getString(R.string.default_workout_name);
        }
        Toast.makeText(this, workout_name, Toast.LENGTH_SHORT).show();
        //items.add(new Workout(workout_name));
        Workout wk = new Workout(workout_name);
        wk.addExercise(0);
        wk.addExercise(1);
        wk.addExercise(2);
        items.add(wk);
        editText.setText("");
        adapter.notifyItemInserted(this.items.size() - 1);
    }

    @Override
    public void onWorkoutItemClick(int position) {
        Intent intent = new Intent(this, ListOfExercises.class);
        Log.d(TAG, "onWorkoutItemClick: " + items.get(position).getlable());
        intent.putExtra("selected_workout_name", items.get(position).getlable());
        startActivity(intent);
    }
}