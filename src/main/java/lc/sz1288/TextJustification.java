package lc.sz1288;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of strings words and a width maxWidth,
 * format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible.
 * If the number of spaces on a line does not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 */
public class TextJustification {
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> result = new LinkedList<>();
    List<String> line = new LinkedList<>();
    int length = 0;
    for (String word : words) {
      if (length + word.length() > maxWidth) {
        result.add(justify(line, length, maxWidth));
        line = new LinkedList<>();
        length = 0;
      }
      line.add(word);
      length += word.length() + 1;
    }

    // last line
    result.add(padSpace(String.join(" ", line), maxWidth));
    return result;
  }

  private String justify(List<String> strings, int length, int maxWidth) {
    int size = strings.size();
    if (size == 1) {
      return padSpace(strings.get(0), maxWidth);
    }
    int numSpaces = maxWidth - length + size;
    int space = numSpaces / (size - 1);
    int mod = numSpaces % (size - 1);
    StringBuilder line = new StringBuilder();
    for (int i = 0; i < size; i++) {
      if (i != 0) {
        for (int j = 0; j < space; j++) {
          line.append(' ');
        }
        if (i <= mod) {
          line.append(' ');
        }
      }
      line.append(strings.get(i));
    }
    return line.toString();
  }

  private String padSpace(String line, int maxSize) {
    StringBuilder sb = new StringBuilder(line);
    while (sb.length() < maxSize) {
      sb.append(' ');
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    TextJustification solution = new TextJustification();
    String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
    String[] words1 = {"justification", "completeness", "fortification", "justification."};
    for (String s : solution.fullJustify(words, 15)) {
      System.out.println(s);
    }
    for (String s : solution.fullJustify(words1, 15)) {
      System.out.println(s);
    }
  }
}
