package com.example.tripreminder2021.adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tripreminder2021.R;
import com.example.tripreminder2021.pojo.TripModel;
import com.example.tripreminder2021.pojo.TripStatus;
import com.example.tripreminder2021.repository.FirebaseDatabaseServices;
import com.example.tripreminder2021.ui.activities.AddBtnActivity;
import com.example.tripreminder2021.ui.navigation.Home.UpcomingFragment;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UpcomingRecyclerViewAdapter extends RecyclerView.Adapter<UpcomingRecyclerViewAdapter.ViewHolder>{

    private ArrayList<TripModel> list;

    private TripModel currentTrip;
    private Context context;
    FirebaseDatabaseServices firebaseDatabaseServices;

   

    public UpcomingRecyclerViewAdapter(ArrayList<TripModel> list,Context context)

    {
        this.context=context;
        this.list=list;
        firebaseDatabaseServices=new FirebaseDatabaseServices();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_card_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        currentTrip = list.get(position);

        holder.tripName.setText(currentTrip.getTripname());
        holder.startLoc.setText(currentTrip.getStartloc());
        holder.endLoc.setText(currentTrip.getEndloc());
        holder.time.setText(currentTrip.getTime());
        holder.date.setText(currentTrip.getDate());
        holder.startNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //remove from upcoming
                //add to history

                Uri gmIntentUri = Uri.parse("google.navigation:q=" + currentTrip.getEndloc());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);


            }
        });

        holder.popMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.popMenu);
            }
        });

    }
    private void showPopupMenu(View view) {
        // inflate menu
        androidx.appcompat.widget.PopupMenu popup = new PopupMenu(view.getContext(),view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.upcoming_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_upcoming_show_notes:
                        //currentTrip.getNotes();
                        Log.i("TAG", "onMenuItemClick: "+currentTrip.getTripname());
                        return true;

                    case R.id.action_upcoming_add_notes:

                        firebaseDatabaseServices.updateNotes(currentTrip.getTrip_id(),currentTrip.getNotes());
                        Log.i("TAG", "onMenuItemClick: "+currentTrip.getDate());
                        return true;

                    case R.id.action_upcoming_edit_trip:

                        editTrip(currentTrip);
                        return true;

                    case R.id.action_upcoming_cancel_trip:

                        firebaseDatabaseServices.changeTripStatus(currentTrip.getTrip_id(), TripStatus.Canceled);
                        return true;

                    case R.id.action_upcoming_delete_trip:
                        showDeleteAlertDialog(currentTrip.getTrip_id());
                        return true;

                    default:
                }
                return false;
            }
        });
        popup.show();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tripName;
        private Button popMenu;
        private TextView startLoc;
        private TextView endLoc;
        private TextView time;
        private TextView date;
        private Button startNow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tripName=itemView.findViewById(R.id.trip_name_id);
            popMenu=itemView.findViewById(R.id.pop_menu_id);
            startLoc=itemView.findViewById(R.id.start_loc_id);
            endLoc=itemView.findViewById(R.id.end_loc_id);
            time=itemView.findViewById(R.id.Time_id);
            date=itemView.findViewById(R.id.Date_id);
            startNow=itemView.findViewById(R.id.startnow);
        }
    }
    private void editTrip(TripModel tripModel)
    {
        Intent intent = new Intent(context, AddBtnActivity.class);
        Bundle Bundle = new Bundle();
        Bundle.putParcelable("LAST_VALUES", tripModel);
        intent.putExtras(Bundle);
    }

    private void showDeleteAlertDialog(String trip_id)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("Sure you want to delete the trip");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        firebaseDatabaseServices.deleteTrip(trip_id);
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
