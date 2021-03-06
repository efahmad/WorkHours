package ahmad.ef.workhours.entity;

/**
 * Created by asma on 3/6/2015.
 */
public enum DayPartType {
    NORMAL(1),
    VACATION(2),
    MISSION(3);

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
