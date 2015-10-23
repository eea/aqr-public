package eu.europa.ec.aqrsystem.utils;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BaseUtilsTest {

    @Test
    public void checkSplit() {
        String[] expected = {"æ", " ø ", "å"};
        List<String> items = BaseUtils.stringToListOfTags("æ, ø ,å");

        int eInx = 0;
        for (String item : items) {
            assertEquals(expected[eInx++], item);
        }
    }

}
