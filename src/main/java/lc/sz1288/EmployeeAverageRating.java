package lc.sz1288;

import com.google.common.collect.ImmutableSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EmployeeAverageRating {
  private final Map<Employee, Set<Employee>> employeeReports;

  public EmployeeAverageRating(Set<Employee> employees) {
    this.employeeReports = new HashMap<>();
    Map<Integer, Employee> employeeIds = new HashMap<>();
    for (Employee employee : employees) {
      employeeIds.put(employee.id, employee);
    }
    for (Employee employee : employees) {
      if (employee.manager == null) {
        continue;
      }
      Employee manager = employeeIds.get(employee.manager);
      if (!employeeReports.containsKey(manager)) {
        employeeReports.put(manager, new HashSet<>());
      }
      employeeReports
          .get(manager)
          .add(employee);
    }
  }

  public Employee getHighestRatingTeam() {
    Map<Employee, Double> averageRating = new HashMap<>();
    double highest = 0;
    Employee highestTeam = null;
    for (Employee employee : employeeReports.keySet()) {
      double rating = getRating(employee, averageRating);
      if (rating > highest) {
        highest = rating;
        highestTeam = employee;
      }
    }
    return highestTeam;
  }

  private double getRating(Employee employee, Map<Employee, Double> averageRating) {
    Set<Employee> reports = employeeReports.get(employee);
    if (reports == null) {
      return employee.rating;
    }
    if (averageRating.containsKey(employee)) {
      return averageRating.get(employee);
    }
    double total = 0;
    for (Employee report : reports) {
      total += getRating(report, averageRating);
    }
    total /= reports.size();
    averageRating.put(employee, total);
    System.out.printf("%s:%f%n", employee, total);
    return total;
  }

  private static class Employee {
    int id;
    String name;
    String title;
    int rating; //1 through 5
    Integer manager;

    Employee(int id, String name, String title, int rating, Integer manager) {
      this.id = id;
      this.name = name;
      this.title = title;
      this.rating = rating;
      this.manager = manager;
    }

    @Override
    public String toString() {
      return "Employee{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", title='" + title + '\'' +
          '}';
    }
  }

  public static void main(String[] args) {
    Employee boss = new Employee(1, "boss", "boss", 5, null);

    // vp1 has a manager, the manager has 2 ICs
    Employee vp1 = new Employee(2, "vp1", "vp", 4, 1);
    Employee manager1 = new Employee(4, "ma", "manager", 3, 2);
    Employee ic1 = new Employee(11, "bac", "ic", 2, 4);
    Employee ic2 = new Employee(12, "cab", "ic", 5, 4);

    //vp2 has 3 ICs
    Employee vp2 = new Employee(3, "vp2", "vp", 5, 1);
    Employee ic3 = new Employee(11, "abc", "ic", 4, 3);
    Employee ic4 = new Employee(12, "cba", "ic", 4, 3);
    Employee ic5 = new Employee(13, "aba", "ic", 5, 3);

    EmployeeAverageRating solution = new EmployeeAverageRating(
        ImmutableSet.of(boss, vp1, manager1, ic1, ic2, ic3, ic4, ic5, vp2)
    );
    System.out.println(solution.getHighestRatingTeam());
  }
}
