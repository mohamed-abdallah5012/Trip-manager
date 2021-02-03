package com.example.tripreminder2021.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tripreminder2021.pojo.TripModel;
import com.example.tripreminder2021.repository.HistoryRepository;

import java.util.ArrayList;

public class ReportViewModel extends ViewModel {

    private MutableLiveData<ArrayList<TripModel>> tripReportedList=new MutableLiveData<>();

    public MutableLiveData<ArrayList<TripModel>> getReportedList(String from, String to) {
        tripReportedList=HistoryRepository.getInstance().getTripsReport(from, to);
        return tripReportedList;
    }
}