package lc.sz1288;

import java.util.Arrays;

public class Heater {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int heaterIndex = 0;
        int minRadius = Math.abs(houses[0] - heaters[0]);
        for (int house : houses) {
            while (heaterIndex < heaters.length && heaters[heaterIndex] < house) {
                heaterIndex++;
            }
            if (heaterIndex == 0) {
                minRadius = Math.max(minRadius, Math.abs(house - heaters[0]));
            } else if (heaterIndex == heaters.length) {
                minRadius = Math.max(minRadius, Math.abs(house - heaters[heaterIndex - 1]));
            } else {
                minRadius = Math.max(minRadius, Math.min(house - heaters[heaterIndex - 1], heaters[heaterIndex] - house));
            }
        }
        return minRadius;
    }

    public static void main(String[] args) {
        Heater h = new Heater();
        int[] houses = {1, 2, 3, 4};
        int[] heaters = {1, 4};
        System.out.println(h.findRadius(houses, heaters));
    }
}
