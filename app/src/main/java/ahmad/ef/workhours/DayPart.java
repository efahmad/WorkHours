package ahmad.ef.workhours;

/**
 * Created by ahmad on 2/16/2015.
 */

public class DayPart {

    // private fields
    private int _id;
    // Start time in epoch seconds
    private long _startTime;
    // End time in epoch seconds
    private long _endTime;

    // Default constructor
    public DayPart(){}


    // Constructors

    public DayPart(int id, long startTime, long endTime){
        this._id = id;
        this._startTime = startTime;
        this._endTime = endTime;
    }

    public DayPart(long startTime, long endTime){
        this._startTime = startTime;
        this._endTime = endTime;
    }

    // Getters and Setters

    public int getId(){
        return this._id;
    }

    public void setId(int value){
        this._id = value;
    }

    public long getStartTime(){
        return this._startTime;
    }

    public void setStartTime(long value){
        this._startTime = value;
    }

    public long getEndTime(){
        return this._endTime;
    }

    public void setEndTime(long value){
        this._endTime = value;
    }
}
