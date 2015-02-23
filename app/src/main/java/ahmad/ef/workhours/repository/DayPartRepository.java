package ahmad.ef.workhours.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import ahmad.ef.workhours.AppConstants;
import ahmad.ef.workhours.DatabaseHandler;
import ahmad.ef.workhours.DayPart;

/**
 * Created by ahmad on 2/20/2015.
 */
public class DayPartRepository implements IRepository<DayPart> {

    private Context context;

    public DayPartRepository(Context context){
        this.context = context;
    }

    /**
     * Add a new entity
     *
     * @param entity The entity object to be added
     */
    @Override
    public void add(DayPart entity) {
        SQLiteDatabase db = DatabaseHandler.getInstance(context).getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(AppConstants.DAY_PART_KEY_START, entity.getStartTime());
        values.put(AppConstants.DAY_PART_KEY_END, entity.getEndTime());

        // Insert the row
        db.insert(AppConstants.DAY_PART_TABLE, null, values);
        // Close database connection
        db.close();
    }

    /**
     * Get an entity by id
     *
     * @param id Primary key of the entity
     * @return The entity with the specified id
     */
    @Override
    public DayPart get(int id) {
        return null;
    }

    /**
     * Get a list of all entities
     *
     * @return List of entities
     */
    @Override
    public List<DayPart> getAll() {
        return null;
    }

    /**
     * Get count of entities
     *
     * @return Count of entities
     */
    @Override
    public int getCount() {
        return 0;
    }

    /**
     * Update an entity
     *
     * @param entity Data for the entity to be updated
     * @return Affected rows by the update command
     */
    @Override
    public int update(DayPart entity) {
        return 0;
    }

    /**
     * Delete an entity
     *
     * @param entity The entity object to be deleted
     */
    @Override
    public void delete(DayPart entity) {

    }

    /**
     * Delete an entity by id
     *
     * @param id Primary key of the entity to be deleted
     */
    @Override
    public void delete(int id) {

    }

    /**
     * Search for DayParts by start time
     * @param startTime Value for start time
     * @return A list of DayParts
     */
    public List<DayPart> getDayPartsByEnterTime(int startTime){
        return null;
    }

    /**
     * Search for DayParts by end time
     * @param endTime Value for end time
     * @return A list of DayParts
     */
    public List<DayPart> getDayPartsByExitTime(int endTime){
        return null;
    }
}
