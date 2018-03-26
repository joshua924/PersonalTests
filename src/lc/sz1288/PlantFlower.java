package lc.sz1288;

public class PlantFlower {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int canPlant = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (canPlant(flowerbed, i)) {
                flowerbed[i] = 1;
                canPlant++;
            }
        }
        System.out.println(canPlant);
        return canPlant >= n;
    }

    private boolean canPlant(int[] flowerbed, int i) {
        boolean canPlant = flowerbed[i] == 0;
        if (i != 0) {
            canPlant &= flowerbed[i - 1] == 0;
        }
        if (i != flowerbed.length - 1) {
            canPlant &= flowerbed[i + 1] == 0;
        }
        return canPlant;
    }

    public static void main(String[] args) {
        PlantFlower pf = new PlantFlower();
        int[] nums1 = {0, 0, 1, 0, 1};
        int[] nums2 = {1};
        System.out.println(pf.canPlaceFlowers(nums1, 1));
        System.out.println(pf.canPlaceFlowers(nums2, 0));
    }
}
