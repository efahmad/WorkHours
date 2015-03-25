package ahmad.ef.workhours;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ahmad.ef.workhours.entity.DayPart;
import ahmad.ef.workhours.entity.DayPartType;
import ahmad.ef.workhours.repository.DayPartRepository;

/**
 * Created by asma on 3/19/2015.
 */
public class TopRatedFragment extends Fragment {

    // Views
    private TextView txtStartTime;
    private TextView txtDate;
    private TextView txtResult;
    private EditText txtDayPartId;
    private Button btnSetStartTime;
    private Button btnSetDate;
    private Button btnAddToDb;
    private Button btnDeleteDayPart;
    private Spinner typeSpinner;

    // DayPart values
    private int mSelectedStartMinutes;
    private int mSelectedStartHour;
    private int mSelectedDay;
    private int mSelectedMonth;
    private int mSelectedYear;
    private DayPartType mSelectedDayPartType = DayPartType.NORMAL;

    // Listeners
    private TimePickerDialog.OnTimeSetListener mOnStartTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    // update the current variables (hour and minutes)
                    mSelectedStartHour = hourOfDay;
                    mSelectedStartMinutes = minute;

                    updateTxtStartTime();
                }
            };

    private DatePickerDialog.OnDateSetListener mOnDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mSelectedDay = dayOfMonth;
                    mSelectedMonth = monthOfYear;
                    mSelectedYear = year;

                    updateTxtDate();
                }
            };

    private View.OnClickListener mBtnDeleteClickListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // If the user didn't enter the DayPart id.
                    if (txtDayPartId.getText() == null || txtDayPartId.getText().length() == 0) {
                        Toast.makeText(v.getContext(),
                                R.string.msgEnterDayPartId,
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int id = Integer.parseInt(txtDayPartId.getText().toString());
                    DayPartRepository rep = new DayPartRepository(v.getContext());
                    int affectedRows = rep.delete(id);
                    Toast.makeText(v.getContext(),
                            affectedRows + " row was deleted."
                            , Toast.LENGTH_SHORT).show();
                    // Reload DayParts
                    loadData();
                    // Clear txtDayPartId text
                    txtDayPartId.setText("");
                }
            };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.top_rated_layout, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize
        txtStartTime = (TextView) getView().findViewById(R.id.txtStartTime);
        txtDate = (TextView) getView().findViewById(R.id.txtDate);
        txtResult = (TextView) getView().findViewById(R.id.txtResult);
        txtDayPartId = (EditText) getView().findViewById(R.id.txtDayPartId);
        btnSetStartTime = (Button) getView().findViewById(R.id.btnSetStartTime);
        btnSetDate = (Button) getView().findViewById(R.id.btnSetDate);
        btnAddToDb = (Button) getView().findViewById(R.id.btnAddToDb);
        btnDeleteDayPart = (Button) getView().findViewById(R.id.btnDeleteDayPart);
        typeSpinner = (Spinner) getView().findViewById(R.id.typeSpinner);

        addItemsToTypeSpinner();
        addListenerToTypeSpinner();

        // Initialize current date times
        Calendar calendar = Calendar.getInstance();
        mSelectedYear = calendar.get(Calendar.YEAR);
        mSelectedMonth = calendar.get(Calendar.MONTH);
        mSelectedDay = calendar.get(Calendar.DAY_OF_MONTH);
        mSelectedStartHour = calendar.get(Calendar.HOUR_OF_DAY);
        mSelectedStartMinutes = calendar.get(Calendar.MINUTE);

        // Initialize text views
        updateTxtStartTime();
        updateTxtDate();

        // Add button click listeners
        btnDeleteDayPart.setOnClickListener(mBtnDeleteClickListener);

        btnSetStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(mOnStartTimeSetListener);
            }
        });
        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(mOnDateSetListener);
            }
        });
        btnAddToDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, mSelectedYear);
                calendar.set(Calendar.MONTH, mSelectedMonth);
                calendar.set(Calendar.DAY_OF_MONTH, mSelectedDay);
                calendar.set(Calendar.HOUR_OF_DAY, mSelectedStartHour);
                calendar.set(Calendar.MINUTE, mSelectedStartMinutes);
                calendar.set(Calendar.SECOND, 0);
                long startTime = calendar.getTimeInMillis();
                // Save the DayPart to Database
                saveData(startTime, mSelectedDayPartType);
                loadData();
            }
        });

        txtDayPartId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (txtDayPartId.getText() != null && txtDayPartId.getText().length() > 0) {
                    btnDeleteDayPart.setEnabled(true);
                } else {
                    btnDeleteDayPart.setEnabled(false);
                }
            }
        });

        // Show the current data in database
        loadData();
    }

    private void addItemsToTypeSpinner() {
        List<String> items = new ArrayList<String>();
        items.add(DayPartType.NORMAL.toString());
        items.add(DayPartType.VACATION.toString());
        items.add(DayPartType.MISSION.toString());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.support_simple_spinner_dropdown_item, items);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        typeSpinner.setAdapter(dataAdapter);
    }

    private void addListenerToTypeSpinner() {
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                mSelectedDayPartType = DayPartType.valueOf(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private Dialog showTimePickerDialog(TimePickerDialog.OnTimeSetListener listener) {
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinutes = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(this.getActivity(), listener, currentHour, currentMinutes, true);
        timePickerDialog.show();
        return timePickerDialog;
    }

    private Dialog showDatePickerDialog(DatePickerDialog.OnDateSetListener listener) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this.getActivity(), listener, currentYear, currentMonth, currentDay);
        datePickerDialog.show();
        return datePickerDialog;
    }

    private void saveData(long startTime, DayPartType type) {
        DayPartRepository dayPartRepository = new DayPartRepository(this.getActivity());
        long id = dayPartRepository.add(new DayPart(startTime, type));
        if (id != -1) {
            Toast.makeText(this.getActivity(), "1 row was added, id: " + id,
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        DayPartRepository dayPartRepository = new DayPartRepository(this.getActivity());
        // Reading all DayParts
        Log.d("Reading: ", "Reading all contacts...");
        List<DayPart> dayParts = dayPartRepository.getAll();

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String result = "Current data in Database:\n";
        for (DayPart dayPart : dayParts) {
            Date startDate = new Date(dayPart.getStartTime());
            String type = dayPart.getType().toString();
            String startTime = formatter.format(startDate);

            result += "Id: " + dayPart.getId() + ", " +
                    "DateTime: " + startTime + ", " +
                    "Type: " + type + "\n";
        }
        txtResult.setText(result);
    }

    /**
     * Update txtStartTime with the selected time
     */
    private void updateTxtStartTime() {
        txtStartTime.setText("Time: " +
                String.format("%02d", mSelectedStartHour) + ":" +
                String.format("%02d", mSelectedStartMinutes));
    }

    /**
     * Update txtDate with the selected date
     */
    private void updateTxtDate() {
        txtDate.setText("Date: " +
                mSelectedYear + "-" +
                String.format("%02d", mSelectedMonth + 1) + "-" +
                String.format("%02d", mSelectedDay));
    }
}
