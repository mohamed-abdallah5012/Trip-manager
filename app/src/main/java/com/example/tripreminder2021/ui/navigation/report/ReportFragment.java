package com.example.tripreminder2021.ui.navigation.report;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
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
    private  TextView textViewtest;
    private EditText from,to;

    DatePickerDialog datePickerDialog;
    Calendar calendar = Calendar.getInstance();
    Locale en = new Locale("en", "USA");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-YYYY", en);

    Date date_minimal;
    Date date_maximal;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        reportViewModel =
                ViewModelProviders.of(this).get(ReportViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reports, container, false);
        textViewtest = root.findViewById(R.id.textViewtest);

        from=root.findViewById(R.id.editTextDate1);
        to=root.findViewById(R.id.editTextDate2);

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        from.setText(simpleDateFormat.format(calendar.getTime()));
                        date_minimal = calendar.getTime();

                        String input1 = from.getText().toString();
                        String input2 = to.getText().toString();
                        if (input1.isEmpty() && input2.isEmpty()){
                            root.findViewById(R.id.showReport).setEnabled(false);
                        }else {
                            root.findViewById(R.id.showReport).setEnabled(true);
                        }
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        to.setText(simpleDateFormat.format(calendar.getTime()));
                        date_maximal = calendar.getTime();

                        String input1 = to.getText().toString();
                        String input2 = from.getText().toString();
                        if (input1.isEmpty() && input2.isEmpty()){
                            root.findViewById(R.id.showReport).setEnabled(false);
                        }else {
                            root.findViewById(R.id.showReport).setEnabled(true);
                        }
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        root.findViewById(R.id.showReport).setOnClickListener(v ->{
            Log.i("TAG", "onCreateView: from "+from.getText().toString());
            Log.i("TAG", "onCreateView: to  "+to.getText().toString());
            ArrayList<TripModel> tripModels=reportViewModel.
                    getReportedList(date_minimal.getTime(),
                            date_maximal.getTime()).getValue();
            textViewtest.setText(tripModels.size()+"     ");
            Log.i("TAG", "onCreateView: size "+tripModels.size());
        });
        return root;
    }

}