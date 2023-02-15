package lc.sz1288;

import java.util.Objects;
import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 */
public class MinStack {
  private final Stack<Integer> stack;
  private final Stack<Integer> minValues;

  public MinStack() {
    this.stack = new Stack<>();
    this.minValues = new Stack<>();
  }

  public void push(int val) {
    stack.push(val);
    if (minValues.isEmpty() || val <= minValues.peek()) {
      minValues.push(val);
    }
  }

  public void pop() {
    Integer pop = stack.pop();
    if (Objects.equals(pop, minValues.peek())) {
      minValues.pop();
    }
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return minValues.peek();
  }

  public static void main(String[] args) {
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.push(-3);
    System.out.println(minStack.getMin());
    minStack.pop();
    System.out.println(minStack.getMin());
    System.out.println(minStack.top());
    minStack.pop();
    System.out.println(minStack.getMin());
    System.out.println(minStack.top());
  }
}
