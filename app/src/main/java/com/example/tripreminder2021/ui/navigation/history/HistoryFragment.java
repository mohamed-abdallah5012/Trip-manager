package com.example.tripreminder2021.ui.navigation.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.tripreminder2021.*;
import com.example.tripreminder2021.adapters.HistoryRecyclerViewAdapter;
import com.example.tripreminder2021.pojo.TripModel;
import com.example.tripreminder2021.viewModels.HistoryViewModel;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<TripModel> HistoryTripList;
    private HistoryRecyclerViewAdapter recyclerViewAdapter;
    private HistoryViewModel historyViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = root.findViewById(R.id.recycler);
        HistoryTripList = new ArrayList<>();

        TripModel model=new TripModel("mohamed","ahmed","mahmoud","Ali","yousef","ayman");
        HistoryTripList.add(model);

        //upcomingViewModel= ViewModelProviders.of(getActivity()).get(UpcomingViewModel.class);
        //upcomingViewModel.init(getContext());

        recyclerViewAdapter = new HistoryRecyclerViewAdapter(HistoryTripList);
        //recyclerViewAdapter = new UpcomingRecyclerViewAdapter(upcomingViewModel.getUpcomingTrips().getValue());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        return root;
    }

}
