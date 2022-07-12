package lc.sz1288;

/**
 * A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths. The lengths should not have leading zeros.
 *
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
 *
 * "s10n" ("s ubstitutio n")
 * "sub4u4" ("sub stit u tion")
 * "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced)
 * The following are not valid abbreviations:
 *
 * "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
 * "s010n" (has leading zeros)
 * "s0ubstitution" (replaces an empty substring)
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 */
public class ValidWordAbbreviation {
  public boolean validWordAbbreviation(String word, String abbr) {
    int wordIndex = 0;
    int abbrIndex = 0;
    while (wordIndex < word.length() && abbrIndex < abbr.length()) {
      if (Character.isDigit(abbr.charAt(abbrIndex))) {
        int begin = abbrIndex;
        while (abbrIndex < abbr.length() && Character.isDigit(abbr.charAt(abbrIndex))) {
          abbrIndex++;
        }
        String numString = abbr.substring(begin, abbrIndex);
        if (numString.startsWith("0")) {
          return false;
        }
        int num = Integer.parseInt(numString);
        wordIndex += num;
      } else {
        if (word.charAt(wordIndex) != abbr.charAt(abbrIndex)) {
          return false;
        }
        abbrIndex++;
        wordIndex++;
      }
    }
    return wordIndex == word.length() && abbrIndex == abbr.length();
  }

  public static void main(String[] args) {
    ValidWordAbbreviation solution = new ValidWordAbbreviation();
    System.out.println(solution.validWordAbbreviation("substitution", "s10n"));
    System.out.println(solution.validWordAbbreviation("substitution", "sub4u4"));
    System.out.println(solution.validWordAbbreviation("substitution", "12"));
    System.out.println(solution.validWordAbbreviation("substitution", "s55n"));
    System.out.println(solution.validWordAbbreviation("substitution", "s010n"));
    System.out.println(solution.validWordAbbreviation("substitution", "s0ubstitution"));
    System.out.println(solution.validWordAbbreviation("substitution", "s11tion"));
    System.out.println(solution.validWordAbbreviation("substitution", "s2"));
  }
}
