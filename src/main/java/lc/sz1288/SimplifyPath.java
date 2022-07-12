package lc.sz1288;

import java.util.LinkedList;

public class SimplifyPath {
  public String simplifyPath(String path) {
    LinkedList<String> paths = new LinkedList<>();
    for (String part : path.split("/")) {
      if (".".equals(part) || part.isEmpty()) {
        continue;
      }
      if ("..".equals(part)) {
        paths.pollLast();
      } else {
        paths.offer(part);
      }
    }
    return "/" + String.join("/", paths);
  }

  public static void main(String[] args) {
    SimplifyPath solution = new SimplifyPath();
    System.out.println(solution.simplifyPath("/home/"));
    System.out.println(solution.simplifyPath("/../"));
    System.out.println(solution.simplifyPath("/home//foo/"));
  }
}
