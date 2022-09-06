package lc.sz1288;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients.
 * The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i].
 * Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
 *
 * You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.
 *
 * Return a list of all the recipes that you can create. You may return the answer in any order.
 *
 * Note that two recipes may contain each other in their ingredients.
 */
public class FindAllRecipes {
  public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
    Map<String, List<String>> edges = new HashMap<>();
    Map<String, Integer> inDegree = new HashMap<>();
    for (int i = 0; i < recipes.length; i++) {
      List<String> required = ingredients.get(i);
      for (String each : required) {
        if (!edges.containsKey(each)) {
          edges.put(each, new ArrayList<>());
        }
        edges.get(each).add(recipes[i]);
      }
      inDegree.put(recipes[i], required.size());
    }

    List<String> result = new LinkedList<>();
    Queue<String> queue = new LinkedList<>(Arrays.asList(supplies));
    while (!queue.isEmpty()) {
      String current = queue.poll();
      for (String recipe : edges.getOrDefault(current, new ArrayList<>())) {
        int degree = inDegree.get(recipe);
        inDegree.put(recipe, degree - 1);
        if (degree - 1 == 0) {
          queue.add(recipe);
          result.add(recipe);
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    FindAllRecipes solution = new FindAllRecipes();
    System.out.println(solution.findAllRecipes(
        new String[]{"bread", "sandwich"},
        ImmutableList.of(ImmutableList.of("yeast", "flour"), ImmutableList.of("meat", "bread")),
        new String[]{"yeast", "flour", "meat"}));
  }
}
