package com.example.tripreminder2021.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tripreminder2021.config.Constants;
import com.example.tripreminder2021.pojo.TripModel;
import com.example.tripreminder2021.pojo.TripStatus;
import com.example.tripreminder2021.repository.HistoryRepository;
import com.example.tripreminder2021.repository.Repoooo;

import java.util.ArrayList;

public class Viewww extends ViewModel {

    private Repoooo repoooo;

    public void init()
    {
        repoooo=new Repoooo();
    }

    public void addTripToHistory(String trip_id) {
        repoooo.addTripToHistory(trip_id);
    }
    public void changeTripStatus(String trip_id, TripStatus status) {
        repoooo.changeTripStatus(trip_id,status);
    }
    public void updateTrip(String trip_id, TripModel tripModel) {
        repoooo.updateTrip(trip_id,tripModel);
    }
    public void deleteTrip(String trip_id) {
        repoooo.deleteTrip(trip_id);
    }
}