package com.example.tripreminder2021.repository;

import com.example.tripreminder2021.config.Constants;
import com.example.tripreminder2021.pojo.TripModel;
import com.example.tripreminder2021.pojo.TripStatus;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Repoooo {

    private DatabaseReference reference=FirebaseDatabase.getInstance().getReference();

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
                child(Constants.STATUS_CHILD_NAME).setValue(status);

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

}
