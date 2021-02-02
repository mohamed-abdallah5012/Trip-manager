package com.example.tripreminder2021.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.tripreminder2021.config.Constants;
import com.example.tripreminder2021.pojo.TripModel;
import com.example.tripreminder2021.pojo.TripStatus;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRepo {


    private static DatabaseRepo instance;
    private static DatabaseReference reference;

    public static DatabaseRepo getInstance()
    {
        if (instance==null)
        {
            instance=new DatabaseRepo();
            reference=FirebaseDatabase.getInstance().getReference();
        }
        return instance;
    }
    public void addTripToHistory(String trip_id) {
        reference.child(Constants.TRIP_CHILD_NAME).
                child(Constants.CURRENT_USER_ID).
                child(trip_id).
                child(Constants.SEARCH_CHILD_NAME).setValue(Constants.SEARCH_CHILD_HISTORY_KEY);

    }
    public void changeTripStatus(String trip_id, TripStatus status) {
        reference.child(Constants.TRIP_CHILD_NAME).
                child(Constants.CURRENT_USER_ID).
                child(trip_id).
                child(Constants.STATUS_CHILD_NAME).
                setValue(status);

        saveTheTripInHistory(trip_id);
    }
    private void saveTheTripInHistory(String trip_id)
    {
        reference.child(Constants.TRIP_CHILD_NAME).
                child(Constants.CURRENT_USER_ID).
                child(trip_id).
                child(Constants.SEARCH_CHILD_NAME).
                setValue(Constants.SEARCH_CHILD_HISTORY_KEY);

    }
    public void updateTrip(String trip_id, TripModel tripModel) {
        reference.child(Constants.TRIP_CHILD_NAME)
                .child(Constants.CURRENT_USER_ID)
                .child(trip_id)
                .setValue(tripModel);
    }
    public void deleteTrip(String trip_id) {
       reference.child(Constants.TRIP_CHILD_NAME)
                .child(Constants.CURRENT_USER_ID)
                .child(trip_id).removeValue();
    }
    public void addTrip(TripModel trip)
    {
        trip.setTrip_id(reference.child(Constants.TRIP_CHILD_NAME)
                .child(Constants.CURRENT_USER_ID)
                .push().getKey());

        reference.child(Constants.TRIP_CHILD_NAME)
                .child(Constants.CURRENT_USER_ID)
                .child(trip.getTrip_id())
                .setValue(trip);
    }

    public void updateNotes(String trip_id, List<String> notes) {
        reference.child(Constants.TRIP_CHILD_NAME).
                child(Constants.CURRENT_USER_ID).
                child(trip_id).
                child("notes").
                setValue(notes);
    }
}
