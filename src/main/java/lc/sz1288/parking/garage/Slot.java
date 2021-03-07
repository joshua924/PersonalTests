package lc.sz1288.parking.garage;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Slot {
    private String slotId;
    private SlotSize size;

    public enum SlotSize {
        SMALL,
        MEDIUM,
        LARGE
    }
}
