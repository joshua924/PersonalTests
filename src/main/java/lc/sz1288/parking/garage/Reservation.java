package lc.sz1288.parking.garage;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Reservation {
    private Slot slot;
    private Car car;
    private LocalDateTime startTime;
    private Duration reservationDuration;
}
