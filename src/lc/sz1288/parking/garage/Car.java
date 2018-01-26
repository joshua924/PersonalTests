package lc.sz1288.parking.garage;

import lombok.Value;

@Value
public class Car {
    private String plateNumber;
    private CarSize size;

    public enum CarSize {
        SMALL,
        MEDIUM,
        LARGE
    }
}
