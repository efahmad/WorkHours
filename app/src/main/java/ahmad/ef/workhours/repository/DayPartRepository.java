package ahmad.ef.workhours.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ahmad.ef.workhours.AppConstants;
import ahmad.ef.workhours.DatabaseHandler;
import ahmad.ef.workhours.entity.DayPart;
import ahmad.ef.workhours.entity.DayPartType;

/**
 * Created by ahmad on 2/20/2015.
 */
public class DayPartRepository implements IRepository<DayPart> {

    private Context context;

    public DayPartRepository(Context context) {
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
        values.put(AppConstants.DAY_PART_KEY_TYPE, entity.getType().getCode());

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
        SQLiteDatabase db = DatabaseHandler.getInstance(context).getReadableDatabase();
        Cursor cursor = db.query(AppConstants.DAY_PART_TABLE,
                new String[]{AppConstants.DAY_PART_KEY_ID,
                        AppConstants.DAY_PART_KEY_START,
                        AppConstants.DAY_PART_KEY_TYPE},
                AppConstants.DAY_PART_KEY_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        DayPart dayPart = new DayPart(
                Integer.parseInt(cursor.getString(0)),
                Long.parseLong(cursor.getString(1)),
                DayPartType.fromCode(Integer.parseInt((cursor.getString(2)))));
        return dayPart;
    }

    /**
     * Get a list of all entities
     *
     * @return List of entities
     */
    @Override
    public List<DayPart> getAll() {
        List<DayPart> dayParts = new ArrayList<DayPart>();
        // Query for all DayParts
        String query = "SELECT * FROM " + AppConstants.DAY_PART_TABLE;

        SQLiteDatabase db = DatabaseHandler.getInstance(context).getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // Loop through all rows and add them to list
        if (cursor.moveToFirst()) {
            do {
                DayPart dayPart = new DayPart();
                dayPart.setId(Integer.parseInt(cursor.getString(0)));
                dayPart.setStartTime(Long.parseLong(cursor.getString(1)));
                dayPart.setType(DayPartType.fromCode(Integer.parseInt(cursor.getString(2))));
                dayParts.add(dayPart);
            } while (cursor.moveToNext());
        }

        // Return DayPart list
        return dayParts;
    }

    /**
     * Get count of entities
     *
     * @return Count of entities
     */
    @Override
    public int getCount() {
        SQLiteDatabase db = DatabaseHandler.getInstance(context).getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + AppConstants.DAY_PART_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        return Integer.parseInt(cursor.getString(0));
    }

    /**
     * Update a single DayPart
     *
     * @param entity Data for the entity to be updated
     * @return Affected rows by the update command
     */
    @Override
    public int update(DayPart entity) {
        SQLiteDatabase db = DatabaseHandler.getInstance(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppConstants.DAY_PART_KEY_START, entity.getStartTime());
        values.put(AppConstants.DAY_PART_KEY_TYPE, entity.getType().getCode());

        // Update the row and return number of affected rows
        return db.update(
                AppConstants.DAY_PART_TABLE,
                values,
                AppConstants.DAY_PART_KEY_ID + " = ?",
                new String[]{String.valueOf(entity.getId())});
    }

    /**
     * Delete an entity
     *
     * @param entity The entity object to be deleted
     */
    @Override
    public int delete(DayPart entity) {
        return this.delete(entity.getId());
    }

    /**
     * Delete an entity by id
     *
     * @param id Primary key of the entity to be deleted
     */
    @Override
    public int delete(int id) {
        SQLiteDatabase db = DatabaseHandler.getInstance(context).getWritableDatabase();
        return db.delete(
                AppConstants.DAY_PART_TABLE,
                AppConstants.DAY_PART_KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    /**
     * Search for DayParts by start time
     *
     * @param startTime Value for start time
     * @return A list of DayParts
     */
    public List<DayPart> getDayPartsByStartTime(long startTime) {
        List<DayPart> dayParts = new ArrayList<DayPart>();
        SQLiteDatabase db = DatabaseHandler.getInstance(context).getReadableDatabase();
        Cursor cursor = db.query(
                AppConstants.DAY_PART_TABLE,
                new String[]{AppConstants.DAY_PART_KEY_ID,
                        AppConstants.DAY_PART_KEY_START,
                        AppConstants.DAY_PART_KEY_TYPE},
                AppConstants.DAY_PART_KEY_START + " = ?",
                new String[]{String.valueOf(startTime)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            do {
                DayPart dayPart = new DayPart();
                dayPart.setId(Integer.parseInt(cursor.getString(0)));
                dayPart.setStartTime(Long.parseLong(cursor.getString(1)));
                dayPart.setType(DayPartType.fromCode(Integer.parseInt((cursor.getString(2)))));
            } while (cursor.moveToNext());
        }
        return dayParts;
    }

    /**
     * Searches for DayParts by type
     *
     * @param type
     * @return
     */
    public List<DayPart> getDayPartsByEndTime(DayPartType type) {
        List<DayPart> dayParts = new ArrayList<DayPart>();
        SQLiteDatabase db = DatabaseHandler.getInstance(context).getReadableDatabase();
        String sqlQuery = "SELECT " + AppConstants.DAY_PART_KEY_ID + ", " +
                AppConstants.DAY_PART_KEY_START + ", " +
                AppConstants.DAY_PART_KEY_TYPE + " FROM " +
                AppConstants.DAY_PART_TABLE + " WHERE " + AppConstants.DAY_PART_KEY_TYPE +
                " = ?";

        Cursor cursor = db.rawQuery(sqlQuery, new String[]{String.valueOf(type.getCode())});
        if (cursor.moveToFirst()) {
            do {
                DayPart dayPart = new DayPart();
                dayPart.setId(Integer.parseInt(cursor.getString(0)));
                dayPart.setStartTime(Long.parseLong(cursor.getString(1)));
                dayPart.setType(DayPartType.fromCode(Integer.parseInt(cursor.getString(2))));
            } while (cursor.moveToNext());
        }
        return dayParts;
    }
}
