package com.example.tripreminder2021.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tripreminder2021.adapters.HistoryRecyclerViewAdapter;
import com.example.tripreminder2021.pojo.TripModel;
import com.example.tripreminder2021.repository.HistoryRepository;
import com.example.tripreminder2021.repository.UpcomingRepository;

import java.util.ArrayList;

public class HistoryViewModel extends ViewModel {

    private MutableLiveData<ArrayList<TripModel>> tripList;
    private MutableLiveData<ArrayList<TripModel>> tripReportedList;

    public void init()
    {
        if (tripList!=null)
            return;
        tripList= HistoryRepository.getInstance().getHistoryTrips();
    }

    public MutableLiveData<ArrayList<TripModel>> getHistoryTrips() {
        tripList=HistoryRepository.getInstance().getHistoryTrips();
        return tripList;
    }
    public MutableLiveData<ArrayList<TripModel>> getReportedList(long from,long to) {
        tripReportedList=HistoryRepository.getInstance().getTripsReport(from, to);
        return tripReportedList;
    }
}