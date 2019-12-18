package oz.asansor.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import oz.asansor.app.Model.Asansor;
import oz.asansor.app.R;

public class AsansorAdapter extends RecyclerView.Adapter<AsansorAdapter.MyViewHolder> {

    private ArrayList<Asansor> asansors;
    private LayoutInflater inflater;
    private Context context;

    public AsansorAdapter(Context context, ArrayList<Asansor> asansors) {
        inflater = LayoutInflater.from(context);
        this.asansors = asansors;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.asansor_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Asansor selectedAsansor = asansors.get(position);
        holder.setData(selectedAsansor, position);
    }

    @Override
    public int getItemCount() {
        return asansors.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView kat, evSahibi;

        MyViewHolder(View itemView) {
            super(itemView);
            kat = (TextView) itemView.findViewById(R.id.kat);
            evSahibi = (TextView) itemView.findViewById(R.id.ev_sahibi);
        }

        void setData(Asansor selectedAsansor, int position) {
            this.kat.setText(selectedAsansor.getKat());
            this.evSahibi.setText(selectedAsansor.getEvSahibi());
        }
    }
}