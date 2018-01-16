package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaximizeCapital {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        List<Project> projects = Project.fromArray(Profits, Capital);
        int currentCapital = W;
        int completed = 0;
        while (completed++ < k) {
            int index = 0;
            while (index < projects.size() && projects.get(index).capital > currentCapital) {
                index++;
            }
            if (index >= projects.size()) {
                return currentCapital;
            }
            Project p = projects.get(index);
            currentCapital += p.profit;
            projects.remove(index);
        }
        return currentCapital;
    }

    private static class Project implements Comparable<Project> {
        int capital;
        int profit;

        public Project(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }

        public static List<Project> fromArray(int[] profits, int[] capitals) {
            List<Project> projects = new ArrayList<>();
            for (int i = 0; i < profits.length; i++) {
                projects.add(new Project(capitals[i], profits[i]));
            }
            projects.sort(Comparator.reverseOrder());
            return projects;
        }

        @Override
        public int compareTo(Project o) {
            return profit - o.profit;
        }
    }

    public static void main(String[] args) {
        MaximizeCapital mc = new MaximizeCapital();
        int[] profits = {1, 2, 3};
        int[] capitals = {11, 12, 13};
        System.out.println(mc.findMaximizedCapital(11, 11, profits, capitals));
    }
}
