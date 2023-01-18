package vm.onlymans.training;

import androidx.annotation.Nullable;

public class Set {
    private float weight = 0;
    private int reps = 0;
    public Set(float weight, int reps){
        this.weight = weight;
        this.reps = reps;
    }
    public boolean change_weight(float weight){
        if(weight > 0){
            this.weight = weight;
            return false;
        }
        return true;
    }
    public boolean change_reps(int reps){
        if(reps > 0){
            this.reps = reps;
            return false;
        }
        return true;
    }
    public boolean change_set(float weight, int reps){
        if(reps > 0 && weight > 0){
            this.reps = reps;
            this.weight = weight;
            return false;
        }
        return true;
    }
    public float getWeight(){
        return weight;
    }
    public int getReps(){
        return reps;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
