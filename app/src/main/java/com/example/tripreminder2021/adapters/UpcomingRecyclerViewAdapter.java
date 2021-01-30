package com.example.tripreminder2021.adapters;

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

import java.util.ArrayList;

public class UpcomingRecyclerViewAdapter extends RecyclerView.Adapter<UpcomingRecyclerViewAdapter.ViewHolder>{

    private ArrayList<TripModel> list;
    private TripModel current;
    //private Context context;

    public UpcomingRecyclerViewAdapter(ArrayList<TripModel> list)
    {
        //this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_card_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        current = list.get(position);

        holder.tripName.setText(current.getTripname());
        holder.startLoc.setText(current.getStartloc());
        holder.endLoc.setText(current.getEndloc());
        holder.time.setText(current.getTime());
        holder.date.setText(current.getDate());
        holder.startNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    class MyMenuItemClickListener implements androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_upcoming_view_notes:
                    Log.i("TAG", "onMenuItemClick: "+current.getTripname());
                    return true;
                case R.id.action_upcoming_cancel_note:
                    Log.i("TAG", "onMenuItemClick: "+current.getDate());
                    return true;
                default:
            }
            return false;
        }
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
}
