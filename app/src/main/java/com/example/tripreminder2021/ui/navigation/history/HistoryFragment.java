package com.example.tripreminder2021.ui.navigation.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.tripreminder2021.*;
import com.example.tripreminder2021.adapters.HistoryRecyclerViewAdapter;
import com.example.tripreminder2021.adapters.UpcomingRecyclerViewAdapter;
import com.example.tripreminder2021.pojo.TripModel;
import com.example.tripreminder2021.viewModels.HistoryViewModel;
import com.example.tripreminder2021.viewModels.UpcomingViewModel;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private HistoryRecyclerViewAdapter recyclerViewAdapter;
    private HistoryViewModel historyViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel= ViewModelProviders.of(this).get(HistoryViewModel.class);

        View root = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = root.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        historyViewModel.init();
        historyViewModel.getHistoryTrips().observe(getViewLifecycleOwner(),
                list -> recyclerViewAdapter.notifyDataSetChanged());

        recyclerViewAdapter = new HistoryRecyclerViewAdapter(getContext(),historyViewModel.getHistoryTrips().getValue());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        return root;
    }
}
