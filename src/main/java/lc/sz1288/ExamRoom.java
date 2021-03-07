package lc.sz1288;

import java.util.TreeSet;

public class ExamRoom {
    private final TreeSet<Integer> seated;
    private final int total;

    public ExamRoom(int N) {
        seated = new TreeSet<>();
        total = N;
    }

    public int seat() {
        if (seated.isEmpty()) {
            seated.add(0);
            return 0;
        }
        int pre = -seated.first();
        int seat = 0;
        int distance = 0;
        for (int each : seated) {
            int pos = (each + pre) / 2;
            if (pos - pre > distance) {
                distance = pos - pre;
                seat = pos;
            }
            pre = each;
        }
        if (total - pre - 1 > distance) {
            seat = total - 1;
        }
        seated.add(seat);
        return seat;
    }

    public void leave(int p) {
        seated.remove(p);
    }

    public static void main(String[] args) {
        ExamRoom er = new ExamRoom(10);
        System.out.println(er.seat());
        System.out.println(er.seat());
        System.out.println(er.seat());
        System.out.println(er.seat());
        er.leave(4);
        System.out.println(er.seat());
    }
}
