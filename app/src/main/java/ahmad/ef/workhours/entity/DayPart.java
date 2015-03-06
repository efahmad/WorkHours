package ahmad.ef.workhours.entity;

/**
 * Created by ahmad on 2/16/2015.
 */

public class DayPart {

    // private fields
    private int id;
    // Start time in milliseconds
    private long startTime;
    // Type of the DayPart
    private DayPartType type;

    // Default constructor
    public DayPart(){}

    // Constructors
    public DayPart(int id, long startTime, DayPartType type){
        this.id = id;
        this.startTime = startTime;
        this.type = type;
    }

    public DayPart(long startTime, DayPartType type){
        this.startTime = startTime;
        this.type = type;
    }

    // Getters and Setters

    public int getId(){
        return this.id;
    }

    public void setId(int value){
        this.id = value;
    }

    public long getStartTime(){
        return this.startTime;
    }

    public void setStartTime(long value){
        this.startTime = value;
    }

    public void setType(DayPartType type){
        this.type = type;
    }

    public DayPartType getType(){
        return this.type;
    }
}
