package vm.onlymans.training;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SetAdapter extends RecyclerView.Adapter<SetAdapter.ViewHolder> {
    private static final String TAG = "SetAdapter";
    private ArrayList<Set> list;
    private SetAdapter.OnSetButtonListener onSetButtonListener;
    public SetAdapter(ArrayList<Set> list, SetAdapter.OnSetButtonListener onSetButtonListener){
        this.list = list;
        this.onSetButtonListener = onSetButtonListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SetAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_set, parent, false), onSetButtonListener
        ) { };
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.enumerated.setText(String.format("%s", position+1));
        holder.title.setText(String.format("Weight %s for %s rep(s)", list.get(position).getWeight(), list.get(position).getReps()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView enumerated;
        TextView title;
        ImageButton delete_btn;
        SetAdapter.OnSetButtonListener onSetButtonListener;
        public ViewHolder(@NonNull View itemView, SetAdapter.OnSetButtonListener onSetButtonListener) {
            super(itemView);
            enumerated = itemView.findViewById(R.id.textview_set_position);
            title = itemView.findViewById(R.id.textview_set_name);
            delete_btn = itemView.findViewById(R.id.imgBtnDeleteSet);
            this.onSetButtonListener = onSetButtonListener;
            delete_btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onSetButtonListener.onSetButtonClick(getAdapterPosition());
        }
    }

    public interface OnSetButtonListener{
        void onSetButtonClick(int position);
    }
}
