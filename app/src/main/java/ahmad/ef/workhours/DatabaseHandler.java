package ahmad.ef.workhours;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ahmad on 2/18/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static DatabaseHandler mInstance = null;
    private Context mContext;

    public static DatabaseHandler getInstance(Context context){
        if(mInstance == null){
            mInstance = new DatabaseHandler(context.getApplicationContext());
        }

        return mInstance;
    }
    // Constructor
    private DatabaseHandler(Context context) {
        super(context, AppConstants.DATABASE_NAME, null, AppConstants.DATABASE_VERSION);
        mContext = context;
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create EnterExit table
        String CREATE_ENTER_EXIT_TABLE = "CREATE TABLE " + AppConstants.DAY_PART_TABLE + "(" +
                AppConstants.DAY_PART_KEY_ID + " INTEGER PRIMARY KEY," +
                AppConstants.DAY_PART_KEY_START + " INTEGER," +
                AppConstants.DAY_PART_KEY_TYPE + " INTEGER" +
                ")";
        db.execSQL(CREATE_ENTER_EXIT_TABLE);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if exists
        db.execSQL("DROP TABLE IF EXISTS " + AppConstants.DAY_PART_TABLE);

        // Create tables again
        onCreate(db);
    }
}
