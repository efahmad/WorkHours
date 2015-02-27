package ahmad.ef.workhours;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import ahmad.ef.workhours.repository.DayPartRepository;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DayPartRepository dayPartRepository = new DayPartRepository(this);
        // Insert some DayParts
        /*Log.d("Insert: ", "Inserting...");
        dayPartRepository.add(new DayPart(100, 200));
        dayPartRepository.add(new DayPart(200, 400));
        dayPartRepository.add(new DayPart(400, 800));*/

        // Reading all DayParts
        Log.d("Reading: ", "Reading all contacts...");
        List<DayPart> dayParts = dayPartRepository.getAll();

        String result = "";
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
