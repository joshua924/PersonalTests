package lc.sz1288;

public class BulbSwitch {
    /*
     * Any two factors will cancel each other out except for the square root, so only the bulbs
     * whose index has a integer square root will end up being on
     */
    public int bulbSwitch(int n) {
        int i = 0;
        while (i * i <= n) {
            i++;
        }
        return i - 1;
    }

    public static void main(String[] args) {
        BulbSwitch bs = new BulbSwitch();
        System.out.println(bs.bulbSwitch(1));
        System.out.println(bs.bulbSwitch(200));
        System.out.println(bs.bulbSwitch(100000));
    }
}
