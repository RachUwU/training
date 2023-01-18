package vm.onlymans.training;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListOfExercises extends AppCompatActivity implements ExerciseAdapter.OnExerciseItemListener {
    private static final String TAG = "ListOfWorkout";
    private String workout_name;
    private final ArrayList<Exercise> list = new ArrayList<>();
    private String current_exercise_name;
    private final RecyclerView.Adapter<ExerciseAdapter.ViewHolder> adapter = new ExerciseAdapter(list, this);

    public ListOfExercises(){}
    public String getCurrent_exercise_name() {
        return current_exercise_name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_exercises);
        if(getIntent().hasExtra("selected_workout_name")){
            workout_name = getIntent().getStringExtra("selected_workout_name");
            Log.d(TAG, "onCreate: " + workout_name);
        }
        else Log.d(TAG, "onCreate: No workout was given");
        TextView textView = findViewById(R.id.tView_workout_lable);
        textView.setText(workout_name);
        ArrayAdapter<String> exerciseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Workout.types_of_exercises);
        exerciseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spExercises = findViewById(R.id.sp_types_of_exercise);
        spExercises.setAdapter(exerciseAdapter);
        spExercises.setPrompt(getString(R.string.spDialog));
        spExercises.setOnItemSelectedListener(onItemSelectedListener());

        RecyclerView recycler = findViewById(R.id.recyclerView_exercises);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

    }
    public void add(View view){
        int enumType = Workout.getNumOfType(current_exercise_name);
        Log.d(TAG, enumType + current_exercise_name);
        if(enumType != -1){
            list.add(new Exercise(enumType));
            adapter.notifyItemInserted(list.size() - 1);
        }
        else{
            Toast.makeText(this, "Selected exercise doesn't exist!", Toast.LENGTH_SHORT).show();
        }
    }
    AdapterView.OnItemSelectedListener onItemSelectedListener(){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                current_exercise_name = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
    }

    @Override
    public void onExerciseItemClick(int position) {
        Intent intent = new Intent(this, ListOfSets.class);
        intent.putExtra("selected exercise name", list.get(position).getName_of_exercise());
        startActivity(intent);
    }

    @Override
    public void onExerciseButtonClick(int position) {
        try {
            list.remove(position);
        }catch (ArrayIndexOutOfBoundsException E){
            E.printStackTrace();
        }
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, list.size());
    }


}