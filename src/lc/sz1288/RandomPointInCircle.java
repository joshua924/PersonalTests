package lc.sz1288;

public class RandomPointInCircle {
    static class Solution {
        private double centerX;
        private double centerY;
        private double radius;

        public Solution(double radius, double x_center, double y_center) {
            this.radius = radius;
            this.centerX = x_center;
            this.centerY = y_center;
        }

        public double[] randPoint() {
            double len = Math.sqrt(Math.random()) * radius;
            double deg = Math.random() * 2 * Math.PI;
            double x = centerX + len * Math.cos(deg);
            double y = centerY + len * Math.sin(deg);
            return new double[]{x, y};
        }
    }
}
