package lc.sz1288;

import org.junit.Test;

import static org.junit.Assert.*;

public class SingleElementSortedArrayTest {
    private static final int[] ARR1 = {1};
    private static final int[] ARR2 = {1, 1, 7, 7, 8, 8, 9, 9, 10};
    private static final int[] ARR3 = {1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
    private static final int[] ARR4 = {1, 1, 2, 2, 3, 4, 4};
    private static final int[] ARR5 = {8, 8, 7, 7, 6, 5, 5, 4, 4};

    private SingleElementSortedArray singleElementSortedArray = new SingleElementSortedArray();

    @Test
    public void singleNonDuplicate() {
        assertEquals(1, singleElementSortedArray.singleNonDuplicate(ARR1));
        assertEquals(10, singleElementSortedArray.singleNonDuplicate(ARR2));
        assertEquals(1, singleElementSortedArray.singleNonDuplicate(ARR3));
        assertEquals(3, singleElementSortedArray.singleNonDuplicate(ARR4));
        assertEquals(6, singleElementSortedArray.singleNonDuplicate(ARR5));
    }
}