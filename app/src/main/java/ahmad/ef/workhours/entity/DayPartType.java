package ahmad.ef.workhours.entity;

/**
 * Created by asma on 3/6/2015.
 */
public enum DayPartType {
    WORKING_TIME(1),
    OVERTIME(2),
    VACATION(3),
    DELAY(4),
    HURRY(5);

    private final int typeCode;

    DayPartType(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getCode() {
        return this.typeCode;
    }

    public static DayPartType fromCode(int code) {
        for (DayPartType dp : values()) {
            if (dp.typeCode == code) {
                return dp;
            }
        }
        return null;
    }
}
