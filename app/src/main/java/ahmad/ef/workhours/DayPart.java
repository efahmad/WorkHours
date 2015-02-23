package ahmad.ef.workhours;

/**
 * Created by ahmad on 2/16/2015.
 */

public class DayPart {

    // private fields
    private int _id;
    // Start time in epoch seconds
    private int _startTime;
    // End time in epoch seconds
    private int _endTime;

    // Default constructor
    public DayPart(){}


    // Constructors

    public DayPart(int id, int startTime, int endTime){
        this._id = id;
        this._startTime = startTime;
        this._endTime = endTime;
    }

    public DayPart(int startTime, int endTime){
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

    public int getStartTime(){
        return this._startTime;
    }

    public void setStartTime(int value){
        this._startTime = value;
    }

    public int getEndTime(){
        return this._endTime;
    }

    public void setEndTime(int value){
        this._endTime = value;
    }
}
