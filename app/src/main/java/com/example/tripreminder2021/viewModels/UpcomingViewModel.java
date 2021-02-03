package com.example.tripreminder2021.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tripreminder2021.pojo.TripModel;
import com.example.tripreminder2021.repository.HistoryRepository;
import com.example.tripreminder2021.repository.UpcomingRepository;

import java.util.ArrayList;

public class UpcomingViewModel extends ViewModel {

    private MutableLiveData<ArrayList<TripModel>> tripList;

    public void init()
    {
        if (tripList!=null)
            return;
        tripList=UpcomingRepository.getInstance().getUpcomingTrips();
    }

    public MutableLiveData<ArrayList<TripModel>> getUpcomingTrips() {
        tripList= UpcomingRepository.getInstance().getUpcomingTrips();
        return tripList;
    }
}