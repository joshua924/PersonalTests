package lc.sz1288.parking.garage;

import java.time.Duration;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reservation {
    private Slot slot;
    private Car car;
    private LocalDateTime startTime;
    private Duration reservationDuration;
}
