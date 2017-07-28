package gson;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexObject {
    private Duration duration;
    private LocalTime startTime;
    private Map<AgeRange, Set<Person>> persons;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Person {
        private BigDecimal age;
        private String name;
        private Sex sex;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AgeRange {
        private BigDecimal start;
        private BigDecimal end;
    }

    public enum Sex {
        MALE(0),
        FEMALE(1);

        private final int num;

        Sex(int num) {
            this.num = num;
        }
    }
}
