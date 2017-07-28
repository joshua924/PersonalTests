package gson;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gson.ComplexObject.AgeRange;
import gson.ComplexObject.Person;
import gson.ComplexObject.Sex;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SerializeAndDeserialize {
    public static void main(String[] args) {
        // enable complex map key serialization will allow map parsing
        Gson gson = new GsonBuilder().setPrettyPrinting().enableComplexMapKeySerialization().create();
        String json = gson.toJson(makeComplexObject());
        System.out.println(json);
        ComplexObject fromJson = gson.fromJson(json, ComplexObject.class);
        System.out.println(fromJson);
    }

    private static ComplexObject makeComplexObject() {
        ComplexObject co = new ComplexObject();
        co.setDuration(Duration.ofHours(3));
        co.setStartTime(LocalTime.NOON);
        co.setPersons(makePersons());
        return co;
    }

    private static Map<AgeRange, Set<Person>> makePersons() {
        Map<AgeRange, Set<Person>> persons = new HashMap<>();
        Person person1 = new Person(BigDecimal.valueOf(24.2), "Shuang Zhou", Sex.MALE);
        Person person2 = new Person(BigDecimal.valueOf(26.5), "Qingqing Xiao", Sex.FEMALE);
        AgeRange ageRange1 = new AgeRange(BigDecimal.ZERO, BigDecimal.valueOf(25));
        AgeRange ageRange2 = new AgeRange(BigDecimal.valueOf(25), BigDecimal.valueOf(100));
        persons.put(ageRange1, ImmutableSet.of(person1));
        persons.put(ageRange2, ImmutableSet.of(person2));
        return persons;
    }
}
