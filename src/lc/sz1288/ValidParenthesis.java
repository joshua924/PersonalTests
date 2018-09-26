package lc.sz1288;

/**
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:
 * <p>
 * 1. Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * 2. Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * 3. Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * 4. '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 * 5. An empty string is also valid.
 */
public class ValidParenthesis {
    public boolean checkValidString(String s) {
        int bal = 0;
        for (int i = 0; i < s.length(); i++) {
            bal += s.charAt(i) == ')' ? -1 : 1;
            if (bal < 0) {
                return false;
            }
        }
        if (bal == 0) {
            return true;
        }
        bal = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            bal += s.charAt(i) == '(' ? -1 : 1;
            if (bal < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidParenthesis vp = new ValidParenthesis();
        System.out.println(vp.checkValidString("*(*)((*"));
    }
}
