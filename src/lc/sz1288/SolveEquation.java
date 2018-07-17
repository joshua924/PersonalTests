package lc.sz1288;

public class SolveEquation {
    public String solveEquation(String equation) {
        String[] twoSides = equation.split("=");
        return Side.solve(Side.parseSide(twoSides[0]), Side.parseSide(twoSides[1]));
    }

    private static class Side {
        int multiplier;
        int offset;

        Side(int multiplier, int offset) {
            this.multiplier = multiplier;
            this.offset = offset;
        }

        static Side parseSide(String s) {
            int index = 1;
            int head = 0;
            Side side = new Side(0, 0);
            while (head < s.length()) {
                while (index < s.length() && s.charAt(index) != '-' && s.charAt(index) != '+') {
                    index++;
                }
                String token = s.substring(head, index);
                if (token.endsWith("x")) {
                    String substring = token.substring(0, token.length() - 1);
                    int multiplier = substring.equals("") || substring.equals("+") ? 1 : substring.equals("-") ? -1 : Integer.valueOf(substring);
                    side.multiplier += multiplier;
                } else {
                    side.offset += Integer.valueOf(token);
                }
                head = index;
                index++;
            }
            return side;
        }

        static String solve(Side s1, Side s2) {
            if (s1.multiplier == s2.multiplier) {
                if (s1.offset == s2.offset) {
                    return "Infinite solutions";
                } else {
                    return "No solution";
                }
            } else {
                int value = (s2.offset - s1.offset) / (s1.multiplier - s2.multiplier);
                return "x=" + value;
            }
        }
    }

    public static void main(String[] args) {
        SolveEquation se = new SolveEquation();
        System.out.println(se.solveEquation("x+5-3+x=6+x-2"));
        System.out.println(se.solveEquation("x=x"));
        System.out.println(se.solveEquation("2x=x"));
        System.out.println(se.solveEquation("2x+3x-6x=x+2"));
        System.out.println(se.solveEquation("x=x+2"));
    }
}
