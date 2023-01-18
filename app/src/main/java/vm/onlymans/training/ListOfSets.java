package vm.onlymans.training;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListOfSets extends AppCompatActivity implements SetAdapter.OnSetButtonListener{
    private static final String TAG = "ListOfSets";
    private final ArrayList<Set> list = new ArrayList<>();
    private final RecyclerView.Adapter<SetAdapter.ViewHolder> adapter = new SetAdapter(this.list, this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_sets);
        String exercise_name = null;
        if(getIntent().hasExtra("selected exercise name")){
            exercise_name = getIntent().getStringExtra("selected exercise name");
        }
        else Log.d(TAG, "onCreate: No workout was given");
        TextView textView = findViewById(R.id.tView_exercise_name);
        textView.setText(exercise_name);

        RecyclerView recycler = findViewById(R.id.recyclerView_sets);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }
    @SuppressLint("SetTextI18n")
    public void increase_weight(View view){
        EditText editText = findViewById(R.id.editTextWeight);
        EditText edit_increment = findViewById(R.id.editTextIncrement);
        float increment = 1.25F;
        if(!edit_increment.getText().toString().equals("")) {
            increment = Float.parseFloat(edit_increment.getText().toString());
        }
        else{
            edit_increment.setText(String.valueOf(increment));
        }
        float weight = 0;
        if(!editText.getText().toString().equals("")) {
            weight = Float.parseFloat(editText.getText().toString());
        }
        if(increment >= 0){
            editText.setText(Float.toString(weight + increment));
        }
        else {
            if(-increment >= weight){
                editText.setText("0");
            }
            else    editText.setText(Float.toString(weight + increment));
        }
    }
    @SuppressLint("SetTextI18n")
    public void decrease_weight(View view){
        EditText editText = findViewById(R.id.editTextWeight);
        EditText edit_increment = findViewById(R.id.editTextIncrement);
        float increment = 1.25F;
        if(!edit_increment.getText().toString().equals("")) {
            increment = Float.parseFloat(edit_increment.getText().toString());
        }
        else{
            edit_increment.setText(String.valueOf(increment));
        }
        float weight = 0;
        if(!editText.getText().toString().equals("")) {
            weight = Float.parseFloat(editText.getText().toString());
        }

        if(increment >= 0){
            if( increment >= weight){
                editText.setText("0");
            }
            else    editText.setText(Float.toString(weight - increment));
        }
        else {
            editText.setText(Float.toString(weight - increment));
        }
    }
    public void increase_reps(View view){
        EditText editText = findViewById(R.id.editTextRepAmount);
        int reps = 0;
        if(!editText.getText().toString().equals("")) {
            reps = Integer.parseInt(editText.getText().toString());
        }
        editText.setText(String.valueOf(reps + 1));
    }
    public void decrease_reps(View view){
        EditText editText = findViewById(R.id.editTextRepAmount);
        int reps = 0;
        if(!editText.getText().toString().equals("")) {
            reps = Integer.parseInt(editText.getText().toString());
        }
        if(reps > 0){
            editText.setText(String.valueOf(reps - 1));
        }
    }
    public void add(View view){
        EditText editText_weight = findViewById(R.id.editTextWeight);
        EditText editText_reps = findViewById(R.id.editTextRepAmount);
        Log.d(TAG, "add: " + list.toString());
        float weight;
        if(editText_weight.getText().toString().equals("") || Float.parseFloat(editText_weight.getText().toString())==0) {
            Toast toast = Toast.makeText(this, "Please enter correct weight!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        else weight = Float.parseFloat(editText_weight.getText().toString());
        int reps;
        if(editText_reps.getText().toString().equals("") || Integer.parseInt(editText_reps.getText().toString())==0) {
            Toast toast = Toast.makeText(this, "Please enter correct rep amount!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        else  reps = Integer.parseInt(editText_reps.getText().toString());
        list.add(new Set(weight, reps));
        Log.d(TAG, "add: " + list + " " + list.size());
        adapter.notifyItemInserted(list.size() - 1);
    }
    public void reset_weight(View view){
        EditText editText = findViewById(R.id.editTextWeight);
        editText.setText("0");
    }
    public void reset_reps(View view){
        EditText editText = findViewById(R.id.editTextRepAmount);
        editText.setText("0");
    }
    public void set_1_25(View view){
        Button btn = findViewById(R.id.buttonChangeWeight1);
        EditText editText = findViewById(R.id.editTextIncrement);
        editText.setText(btn.getText().toString());
    }
    public void set_2_5(View view){
        Button btn = findViewById(R.id.buttonChangeWeight2);
        EditText editText = findViewById(R.id.editTextIncrement);
        editText.setText(btn.getText().toString());
    }
    public void set_5(View view){
        Button btn = findViewById(R.id.buttonChangeWeight3);
        EditText editText = findViewById(R.id.editTextIncrement);
        editText.setText(btn.getText().toString());
    }
    public void set_10(View view){
        Button btn = findViewById(R.id.buttonChangeWeight4);
        EditText editText = findViewById(R.id.editTextIncrement);
        editText.setText(btn.getText().toString());
    }
    public void set_15(View view){
        Button btn = findViewById(R.id.buttonChangeWeight5);
        EditText editText = findViewById(R.id.editTextIncrement);
        editText.setText(btn.getText().toString());
    }
    public void set_20(View view){
        Button btn = findViewById(R.id.buttonChangeWeight6);
        EditText editText = findViewById(R.id.editTextIncrement);
        editText.setText(btn.getText().toString());
    }
    @Override
    public void onSetButtonClick(int position) {
        try {
            list.remove(position);
        }catch (ArrayIndexOutOfBoundsException E){
            E.printStackTrace();
        }
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, list.size());
    }
}