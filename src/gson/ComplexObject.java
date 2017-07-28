package gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexObject {
    private Duration duration;
    private LocalTime startTime;
    private Map<ComplexObject, LocalTime> sons;
}
