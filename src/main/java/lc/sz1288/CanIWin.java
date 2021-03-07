package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

public class CanIWin {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) {
            return true;
        }
        if (desiredTotal > maxChoosableInteger * (maxChoosableInteger + 1) / 2) {
            return false;
        }
        return canIWin(desiredTotal, maxChoosableInteger, 0, new HashMap<>());
    }

    private boolean canIWin(int desiredTotal, int maxChoosableInteger, int state, Map<Integer, Boolean> results) {
        if (results.containsKey(state)) {
            return results.get(state);
        }
        for (int i = 0; i < maxChoosableInteger; i++) {
            if ((state >> i & 1) == 1) {
                continue;
            }
            int newState = state | 1 << i;
            if (desiredTotal <= i + 1 || !canIWin(desiredTotal - i - 1, maxChoosableInteger, newState, results)) {
                results.put(state, true);
                return true;
            }
        }
        results.put(state, false);
        return false;
    }
}
