package lc.sz1288;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You're now a baseball game point recorder.
 * <p>
 * Given a list of strings, each string can be one of the 4 following types:
 * <p>
 * 1. Integer (one round's score): Directly represents the number of points you get in this round.
 * 2. "+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
 * 3. "D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
 * 4. "C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.
 * <p>
 * Each round's operation is permanent and could have an impact on the round before and the round after.
 * <p>
 * You need to return the sum of the points you could get in all the rounds.
 */
public class BaseballGame {
    public int calPoints(String[] ops) {
        int points = 0;
        LinkedList<Integer> validRounds = new LinkedList<>();
        for (String op : ops) {
            int point;
            switch (op) {
                case "+":
                    point = validRounds.get(validRounds.size() - 2) + validRounds.getLast();
                    validRounds.addLast(point);
                    points += point;
                    break;
                case "D":
                    point = validRounds.getLast() * 2;
                    validRounds.addLast(point);
                    points += point;
                    break;
                case "C":
                    points -= validRounds.pollLast();
                    break;
                default:
                    point = Integer.parseInt(op);
                    validRounds.addLast(point);
                    points += point;
            }
        }
        return points;
    }

    public static void main(String[] args) {
        BaseballGame bg = new BaseballGame();
        System.out.println(bg.calPoints(new String[]{"5", "2", "C", "D", "+"}));
        System.out.println(bg.calPoints(new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"}));
    }
}
