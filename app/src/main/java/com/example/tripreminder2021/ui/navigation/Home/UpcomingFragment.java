package com.example.tripreminder2021.ui.navigation.Home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.tripreminder2021.*;
import com.example.tripreminder2021.adapters.UpcomingRecyclerViewAdapter;
import com.example.tripreminder2021.pojo.TripModel;
import com.example.tripreminder2021.pojo.TripStatus;
import com.example.tripreminder2021.repository.FirebaseDatabaseServices;
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


        recyclerViewAdapter = new UpcomingRecyclerViewAdapter(upcomingViewModel.getUpcomingTrips().getValue(),getActivity());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        return root;
    }

    public static class MyMenuItemClickListener implements androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener {

        private TripModel trip;
        private FirebaseDatabaseServices firebaseDatabaseServices;
        public MyMenuItemClickListener(TripModel trip)
        {

            this.trip=trip;
            firebaseDatabaseServices=new FirebaseDatabaseServices();

        }
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_upcoming_show_notes:

                    Log.i("TAG", "onMenuItemClick: "+trip.getTripname());
                    return true;

                case R.id.action_upcoming_add_notes:
                    Log.i("TAG", "onMenuItemClick: "+trip.getDate());
                    return true;
                case R.id.action_upcoming_edit_trip:
                    editTrip(trip);
                    Log.i("TAG", "onMenuItemClicffk: "+trip.getDate());
                    return true;

                case R.id.action_upcoming_cancel_trip:

                    firebaseDatabaseServices.changeTripStatus(trip.getTrip_id(), TripStatus.Canceled);

                    Log.i("TAG", "onMenuItemClddick: "+trip.getDate());
                    return true;

                case R.id.action_upcoming_delete_trip:
                    firebaseDatabaseServices.deleteTrip(trip.getTrip_id());

                    Log.i("TAG", "onMenuItemClisssck: "+trip.getDate());
                    return true;
                default:
            }
            return false;
        }
        private void editTrip(TripModel tripModel)
        {
            //Intent intent=new Intent(trip.getV)
        }
    }


}
