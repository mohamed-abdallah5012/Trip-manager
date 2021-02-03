package com.example.tripreminder2021.ui.navigation.report;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripreminder2021.*;
import com.example.tripreminder2021.pojo.TripModel;
import com.example.tripreminder2021.ui.fragment.DatePickerFragment;
import com.example.tripreminder2021.viewModels.ReportViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReportFragment extends Fragment {

    private ReportViewModel reportViewModel;
    private DatePickerDialog datePickerDialog;
    private ImageView start_date_picker;
    private ImageView end_date_picker;

    private TextView from,to;

    private Button show_report;
    private  TextView textViewtest;


    ArrayList<TripModel> trips=new ArrayList<TripModel>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        reportViewModel =
                ViewModelProviders.of(this).get(ReportViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reports, container, false);

        textViewtest = root.findViewById(R.id.textViewtest);
        from=root.findViewById(R.id.date_Selected_start);
        to=root.findViewById(R.id.date_Selected_end);
        start_date_picker=root.findViewById(R.id.date_Picker_start);
        end_date_picker=root.findViewById(R.id.date_Picker_end);
        show_report=root.findViewById(R.id.showReport);

        start_date_picker.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            // date picker dialog
            datePickerDialog = new DatePickerDialog(getContext(), (view, year12, monthOfYear, dayOfMonth) -> from.setText(dayOfMonth+"-"+(monthOfYear + 1)+"-"+ year12), year, month, day);
            datePickerDialog.show();
        });

        end_date_picker.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            // date picker dialog
            datePickerDialog = new DatePickerDialog(getContext(), (view, year1, monthOfYear, dayOfMonth) -> to.setText(dayOfMonth+"-"+(monthOfYear + 1)+"-"+ year1), year, month, day);
            datePickerDialog.show();
        });

        show_report.setOnClickListener(v ->{
            Log.i("TAG", "click: ");
            String start="".concat(from.getText().toString());
            String To ="".concat(to.getText().toString());
           trips=reportViewModel.getReportedList(start,To).getValue();
            //textViewtest.setText("skkkkkkkkkize "+trips.size());
            //Toast.makeText(getContext(), ""+trips.size(), Toast.LENGTH_SHORT).show();
            //Log.i("TAG", ": lkkkkkkkkkkkkkkkkkkkkist"+trips.size());

        });

        reportViewModel.getReportedList(from.getText().toString(),to.getText().toString())
                .observe(getViewLifecycleOwner(), new Observer<ArrayList<TripModel>>() {
                    @Override
                    public void onChanged(ArrayList<TripModel> list) {
                        String a="";
                        for (int i=0;i<list.size();i++)
                        {
                           a= a.concat(list.get(i).getDate());
                        }
                        textViewtest.setText(a);
                        Log.i("TAG", "onChanged: hhhhhhhhhhhhhh");
                        Log.i("TAG", "onChanged: list"+list.size());
                    }
                });
        return root;
    }

}