package oz.asansor.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import oz.asansor.app.Adapter.AsansorAdapter;
import oz.asansor.app.DB.DBHelper;
import oz.asansor.app.Model.Asansor;
import oz.asansor.app.R;

public class AsansorFragment extends BaseFragment {
    private DBHelper dbHelper;
    private String TAG = "AsansorFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.asansor_fragment, container, false);
        RecyclerView macRecyclerView = view.findViewById(R.id.asansor_recylerview);

        dbHelper = new DBHelper(getActivity());

        ArrayList<Asansor> sortedList = getSortedList();
        Collections.reverse(sortedList);

        AsansorAdapter asansorAdapter = new AsansorAdapter(getActivity(), sortedList);
        macRecyclerView.setAdapter(asansorAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        macRecyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    private ArrayList<Asansor> getDataFromDB() {
        return  dbHelper.getAllAsansors();
    }

    private ArrayList<Asansor> getSortedList(){

        ArrayList<Asansor> sortedList = getDataFromDB();

        Collections.sort( sortedList, new Comparator<Asansor>() {
            @Override
            public int compare(Asansor o1, Asansor o2) {
                return o1.getDocumentID().compareTo(o2.getDocumentID());
            }
        });
        return sortedList;
    }
}