package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

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
        Map<ComplexObject, LocalTime> sons = new HashMap<>();
        ComplexObject son = new ComplexObject(Duration.ofMinutes(30), LocalTime.MIDNIGHT, null);
        sons.put(son, LocalTime.MIDNIGHT);
        co.setSons(sons);
        return co;
    }
}
