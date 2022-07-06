package lc.sz1288;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
 *
 * Implement the FreqStack class:
 *
 * FreqStack() constructs an empty frequency stack.
 * void push(int val) pushes an integer val onto the top of the stack.
 * int pop() removes and returns the most frequent element in the stack.
 * If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.
 */
public class MaximumFrequencyStack {
  private final Map<Integer, Stack<Integer>> countToNums;
  private final Map<Integer, Integer> frequencies;
  private int maxFreq;

  public MaximumFrequencyStack() {
    countToNums = new HashMap<>();
    frequencies = new HashMap<>();
    maxFreq = 0;
  }

  public void push(int val) {
    if (!frequencies.containsKey(val)) {
      frequencies.put(val, 1);
      Stack<Integer> stack = countToNums.getOrDefault(1, new Stack<>());
      stack.push(val);
      countToNums.put(1, stack);
      maxFreq = Math.max(1, maxFreq);
    } else {
      int count = frequencies.get(val);
      frequencies.put(val, count + 1);
      Stack<Integer> stack = countToNums.getOrDefault(count + 1, new Stack<>());
      stack.add(val);
      countToNums.put(count + 1, stack);
      maxFreq = Math.max(count + 1, maxFreq);
    }
  }

  public int pop() {
    Stack<Integer> stack = countToNums.get(maxFreq);
    int num = stack.pop();
    frequencies.put(num, frequencies.get(num) - 1);
    if (stack.isEmpty()) {
      maxFreq--;
    }
    return num;
  }

  public static void main(String[] args) {
    MaximumFrequencyStack freqStack = new MaximumFrequencyStack();
    freqStack.push(5); // The stack is [5]
    freqStack.push(7); // The stack is [5,7]
    freqStack.push(5); // The stack is [5,7,5]
    freqStack.push(7); // The stack is [5,7,5,7]
    freqStack.push(4); // The stack is [5,7,5,7,4]
    freqStack.push(5); // The stack is [5,7,5,7,4,5]
    System.out.println(freqStack.pop());   // return 5
    System.out.println(freqStack.pop());   // return 7
    System.out.println(freqStack.pop());   // return 5
    System.out.println(freqStack.pop());   // return 4
  }
}
