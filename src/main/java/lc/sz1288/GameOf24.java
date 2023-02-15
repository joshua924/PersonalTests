package lc.sz1288;

import java.util.ArrayList;
import java.util.List;

public class GameOf24 {
  public boolean judgePoint24(int[] cards) {
    List<Double> list = new ArrayList<>();
    for (int card : cards) {
      list.add((double) card);
    }

    return canReach(list);
  }

  private List<Double> generatePossibleResults(double a, double b) {
    List<Double> res = new ArrayList<>();
    res.add(a + b);
    res.add(a - b);
    res.add(b - a);
    res.add(a * b);
    if (b != 0) {
      res.add(a / b);
    }
    if (a != 0) {
      res.add(b / a);
    }
    return res;
  }

  private boolean canReach(List<Double> list) {
    if (list.size() == 1) {
      return Math.abs(list.get(0) - 24) <= 0.01;
    }
    for (int i = 0; i < list.size(); i++) {
      for (int j = i + 1; j < list.size(); j++) {
        List<Double> remaining = new ArrayList<>();
        for (int k = 0; k < list.size(); k++) {
          if (k != j && k != i) {
            remaining.add(list.get(k));
          }
        }
        for (double res : generatePossibleResults(list.get(i), list.get(j))) {
          remaining.add(res);
          if (canReach(remaining)) {
            return true;
          }
          remaining.remove(remaining.size() - 1);
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    GameOf24 g24 = new GameOf24();
    System.out.println(g24.judgePoint24(new int[]{4, 1, 8, 7}));
    System.out.println(g24.judgePoint24(new int[]{1, 2, 1, 3}));
    System.out.println(g24.judgePoint24(new int[]{1, 3, 1, 3}));
    System.out.println(g24.judgePoint24(new int[]{1, 3, 1, 4}));
  }
}
