package lc.sz1288;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BasicCalculator {
    public int calculate(String s) {
        Stack<String> stack = new Stack<>();
        s = s.replaceAll(" ", "").replaceAll("-2147483648", "+2147483647+1");
        Converter converter = new Converter();
        String postfix = converter.convert(s);
        for (String token : postfix.split(",")) {
            if (!isOperator(token)) {
                stack.push(token);
            } else {
                int i2 = Integer.parseInt(stack.pop());
                int i1 = Integer.parseInt(stack.pop());
                int evaluate = evaluate(i1, token, i2);
                stack.push(String.valueOf(evaluate));
            }
        }
        return Integer.valueOf(stack.pop());
    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    private int evaluate(int i1, String op, int i2) {
        System.out.println(i1 + op + i2);
        int ans = 0;
        switch (op) {
            case "+":
                ans = i1 + i2;
                break;
            case "-":
                ans = i1 - i2;
                break;
            case "*":
                ans = i1 * i2;
                break;
            case "/":
                ans = (int) Math.floor((double) i1 / i2);
                break;
        }
        return ans;
    }

    private static class Converter {
        private final Map<Character, Integer> operators;

        private Converter() {
            operators = new HashMap<>();
            operators.put('+', 1);
            operators.put('-', 1);
            operators.put('*', 2);
            operators.put('/', 2);
        }

        private String convert(String s) {
            Stack<Character> stack = new Stack<>();
            int index = 0;
            StringBuilder res = new StringBuilder();
            while (index < s.length()) {
                int current = index;
                while (current < s.length() && Character.isDigit(s.charAt(current))) {
                    current++;
                }
                if (index != current) {
                    res.append(s.substring(index, current)).append(",");
                } else {
                    char operator = s.charAt(current);
                    if (operator == '(') {
                        stack.push(operator);
                    } else if (operator == ')') {
                        while (stack.peek() != '(') {
                            res.append(stack.pop()).append(",");
                        }
                        stack.pop();
                    } else {
                        while (!stack.isEmpty() && stack.peek() != '(' && operators.get(stack.peek()) >= operators.get(operator)) {
                            res.append(stack.pop()).append(",");
                        }
                        stack.push(operator);
                    }
                    current++;
                }
                index = current;
            }
            while (!stack.isEmpty()) {
                res.append(stack.pop()).append(",");
            }
            return res.deleteCharAt(res.length() - 1).toString();
        }
    }

    public static void main(String[] args) {
        BasicCalculator bc = new BasicCalculator();
        String x = "( 33   +(6   *(  (   ((( ((( 27   +   82) -   (   80-  29)   )   + (   (   5  + 26  ) *  (  17  * 76 )   )   )+ (   (   ( 6-  4   )  *   48)* (   18  +  (  13+   58 )  )  ) )+  " +
                " ( ( (  ( 5/  72  )   -( 22+  28)   )   -(   (   24  + 16)*(  31   *  10)  )  ) *(  ((81   *50  ) /   (  74  +85) )+   (  ( 64   +   28   )*(  30-1)   )  )  )   ) +  ( ( 39  +  (((" +
                "   24  +70 )  *   (   79+  93) )  -  (7 +(9+   63  )  )) ) +(  (   ((54   + 6  )-   (   18+   25  )  ) -  (   (67   *  81)   + (17   +  27 )) ) +(( (   62* 56 )   +   (   19*  78  " +
                " ) )+( ( 80   *  95   )  +   (   43   -  24 )  ) ))))  - ((   30 + (10  -   ( ( (  11 + 81  )   +  (57 *  99  ) )-  ( ( 18   +   17   )  - (   27 -   38   )   ) )   ))   -(66   " +
                "+93)   )  ) -  ((  ( ( (   (  (65  -72   )   + (  58   *83   ) ) *  ((   28  +  12  )  +   (   29  *  53)  ) )* (  (  (43+ 26  )  +(  7   * 31  ))   +   ((29 +  6  ) +  (59  +  40 " +
                "  )  )  )   )- ( (  ( (   21  +37)   * (   56  -  73  )  )+ 6)  +(  (13*  49  )+ (   38+   (  76+   47)  ) )))   / ( (   ( (   (95-  100 )  +50 )  -  ( ( 40 +   11 )   +  (21 - 71 " +
                " )) )  -  (10   - ( (44 +66   )   +(25 +  71 )) )  ) -  ( (  ( (  93   -  95) + ( 37*66) )*78)/ (   (   (  57   -   62)   +   (83   -   43   )   )  *(   (81+ 64) -  52 )   ))  )  )" +
                "  +( (  (  ( (47* (   94 *   90  ) )   - (  (  18  - 5  ) - (  17 *34   )   ) )   +  (49  *(   (  82 *   41 )-(  93 + 55 )   )  )  )/( ( ( (   52/9  )* ( 77   *   40  )   )   + (( " +
                " 100+20 ) +( 86  /   96   ) ) )  +  (( (5 -  9   )   - (  94/   92 ) )+((67 -   25   )+  (   20   +   39 )   )) )   )  *  ( (  (  ((  22*44)   *( 61 *  83 ) )   +   ((   41  +  29 " +
                " )   -(   8  +   90 )  )   )+   (30   - ( ( 36   /68  )  *(  38+9)   )   )   ) /14 )  )   )) )   )";
        System.out.println(bc.calculate(x));
    }
}
