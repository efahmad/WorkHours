package ahmad.ef.workhours;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import ahmad.ef.workhours.repository.DayPartRepository;


public class MainActivity extends ActionBarActivity {

    private String _btnSetTimeToggle = "start";
    private long _tmpStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSetTime = (Button) findViewById(R.id.btnSetTime);
        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                if (_btnSetTimeToggle == "start") {
                    _tmpStartTime = System.currentTimeMillis();
                    btn.setText(R.string.setEndTime);
                    _btnSetTimeToggle = "end";
                } else {
                    saveData(_tmpStartTime, System.currentTimeMillis());
                    btn.setText(R.string.setStartTime);
                    _btnSetTimeToggle = "start";
                    loadData();
                }
            }
        });

        // Show the current data in database
        loadData();
    }

    private void saveData(long startTime, long endTime) {
        DayPartRepository dayPartRepository = new DayPartRepository(this);
        dayPartRepository.add(new DayPart(startTime, endTime));
    }

    private void loadData() {
        DayPartRepository dayPartRepository = new DayPartRepository(this);
        // Reading all DayParts
        Log.d("Reading: ", "Reading all contacts...");
        List<DayPart> dayParts = dayPartRepository.getAll();

        String result = "Current data in Database:\n";
        for (DayPart dayPart : dayParts) {
            result += "Id: " + dayPart.getId() + ", " +
                    "StartTime: " + dayPart.getStartTime() + ", " +
                    "EndTime: " + dayPart.getEndTime() + "\n";
        }

        TextView txtResult = (TextView) this.findViewById(R.id.txtResult);
        txtResult.setText(result);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
