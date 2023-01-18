package vm.onlymans.training;

import java.util.ArrayList;
import java.util.Objects;

public class Exercise{
    private String name_of_exercise;
    private ArrayList<Set> list = new ArrayList<>();

    public Exercise() {
        name_of_exercise = Workout.types_of_exercises[0];
    }
    public Exercise(int type){
        name_of_exercise = Workout.types_of_exercises[type];
    }
    public Exercise(String name) {
        name_of_exercise = name;
    }
    public int size(){
        return list.size();
    }

    public void setName(String name){ name_of_exercise = name;}
    public Exercise add_set(Set set){
        list.add(set);
        return this;
    }
    public Exercise add_set(float weight, int reps){
        list.add(new Set(weight, reps));
        return this;
    }
    public void change_weight(int n, float weight){
        try{
            Set set = list.get(n);
            set.change_weight(weight);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
    public void change_reps(int n, int reps){
        try{
            Set set = list.get(n);
            set.change_reps(reps);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
    public void add_weight(int n, float additional_weight){
        try{
            Set set = list.get(n);
            set.change_weight(set.getWeight() + additional_weight);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
    public void add_rep(int n){
        try{
            Set set = list.get(n);
            set.change_reps(set.getReps() + 1);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
    public void decrease_rep(int n){
        try{
            Set set = list.get(n);
            set.change_reps(set.getReps() - 1);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
    public Set get_set(int n){
        try{
            return list.get(n);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return null;
    }

    public String getName_of_exercise() {
        return name_of_exercise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercise)) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(getName_of_exercise(), exercise.getName_of_exercise()) && Objects.equals(list, exercise.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName_of_exercise(), list);
    }
}
