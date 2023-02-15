package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a list of accounts where each element accounts[i] is a list of strings,
 * where the first element accounts[i][0] is a name,
 * and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts.
 * Two accounts definitely belong to the same person if there is some common email to both accounts.
 * Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
 * A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name,
 * and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 */
public class AccountMerge {
  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    Map<String, String> emailNames = new HashMap<>();
    Map<String, Set<String>> edges = new HashMap<>();

    for (List<String> account : accounts) {
      String name = account.get(0);
      for (int i = 1; i < account.size(); i++) {
        String email = account.get(i);
        emailNames.put(email, name);
        if (!edges.containsKey(email)) {
          edges.put(email, new HashSet<>());
        }

        if (i >= 2) {
          String pre = account.get(i - 1);
          edges.get(pre).add(email);
          edges.get(email).add(pre);
        }
      }
    }

    Set<String> visited = new HashSet<>();
    List<List<String>> result = new ArrayList<>();
    for (String source : edges.keySet()) {
      if (!visited.contains(source)) {
        visited.add(source);
        List<String> path = new ArrayList<>();
        path.add(source);
        dfs(source, edges, path, visited);
        path.sort(Comparator.naturalOrder());
        path.add(0, emailNames.get(source));
        result.add(path);
      }
    }

    return result;
  }

  private void dfs(String node, Map<String, Set<String>> edges, List<String> path,
                   Set<String> visited) {
    for (String neighbor : edges.get(node)) {
      if (visited.contains(neighbor)) {
        continue;
      }
      visited.add(neighbor);
      path.add(neighbor);
      dfs(neighbor, edges, path, visited);
    }
  }

  public static void main(String[] args) {
    AccountMerge am = new AccountMerge();
    List<List<String>> accounts = Arrays.asList(
        Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
        Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
        Arrays.asList("Mary", "mary@mail.com"),
        Arrays.asList("John", "johnnybravo@mail.com")
    );
    System.out.println(am.accountsMerge(accounts));
  }
}
