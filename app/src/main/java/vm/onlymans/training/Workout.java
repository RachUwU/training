package vm.onlymans.training;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Workout implements Serializable {
    private String lable;
    private ArrayList<Exercise> exercises = new ArrayList<>();
    public static String[] types_of_exercises ={ "Bench press", "Squats", "Deadlift"};
    public Workout(){lable = "MyWorkout";}
    public Workout(String lable){this.lable = lable;}

    public void setlable(String newlable){ lable = newlable;}
    public String getlable(){ return lable;}
    public Exercise addExercise(int type){
        if(type >= 0 && type < types_of_exercises.length){
            Exercise newexercise = new Exercise(types_of_exercises[type]);
            exercises.add(newexercise);
            return newexercise;
        }
        return null;
    }
    public void removeExercise(int n){
        try{
            exercises.remove(n);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
    public Exercise getExercise(int n){
        try {
            return exercises.get(n);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return null;
    }
    public static int getNumOfType(String name){
        for(int i = 0; i < types_of_exercises.length; i++){
            if(name.equals(types_of_exercises[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Workout)) return false;
        Workout workout = (Workout) o;
        return Objects.equals(lable, workout.lable) && Objects.equals(exercises, workout.exercises);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lable, exercises);
    }

    @Override
    public String toString() {
        return "Workout{" +
                "lable='" + lable + '\'' +
                ", exercises=" + exercises +
                '}';
    }
}
