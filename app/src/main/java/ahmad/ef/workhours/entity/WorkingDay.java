package ahmad.ef.workhours.entity;

/**
 * Created by asma on 3/3/2015.
 */
public class WorkingDay {
    private int id;
    // Date of the working day in milliseconds
    private long date;

    public WorkingDay(int id, long date){
        this.id = id;
        this.date = date;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public long getDate(){
        return this.date;
    }

    public void setDate(long date){
        this.date = date;
    }
}
