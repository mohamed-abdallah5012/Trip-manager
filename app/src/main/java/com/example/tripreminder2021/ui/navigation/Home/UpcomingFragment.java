package com.example.tripreminder2021.ui.navigation.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.tripreminder2021.*;
import com.example.tripreminder2021.adapters.UpcomingRecyclerViewAdapter;
import com.example.tripreminder2021.pojo.TripModel;
import com.example.tripreminder2021.viewModels.UpcomingViewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class UpcomingFragment extends Fragment {

    private RecyclerView recyclerView;
    private UpcomingRecyclerViewAdapter recyclerViewAdapter;
    private UpcomingViewModel upcomingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        upcomingViewModel= ViewModelProviders.of(this).get(UpcomingViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        upcomingViewModel.init();
        upcomingViewModel.getUpcomingTrips().observe(getViewLifecycleOwner(), new Observer<ArrayList<TripModel>>() {
            @Override
            public void onChanged(ArrayList<TripModel> list) {
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });

        recyclerViewAdapter = new UpcomingRecyclerViewAdapter(upcomingViewModel.getUpcomingTrips().getValue());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        return root;
    }
}
