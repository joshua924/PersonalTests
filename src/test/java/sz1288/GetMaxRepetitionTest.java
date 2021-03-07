package sz1288;

import lc.sz1288.GetMaxRepetition;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetMaxRepetitionTest {
    private GetMaxRepetition gm = new GetMaxRepetition();

    @Test
    public void getMaxRepetitions() throws Exception {
        assertEquals(2, gm.getMaxRepetitions("acb", 4, "ab", 2));
    }

    @Test
    public void getMaxRepetitions1() throws Exception {
        assertEquals(2, gm.getMaxRepetitions("acb", 5, "ab", 2));
    }

    @Test
    public void getMaxRepetitions2() throws Exception {
        assertEquals(4, gm.getMaxRepetitions("a", 20, "aaaaa", 1));
    }

    @Test
    public void getMaxRepetitions3() throws Exception {
        assertEquals(0, gm.getMaxRepetitions("acb", 70, "db", 2));
    }
}