package eu.europa.ec.aqrsystem.utils;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.regex.Pattern;

public class ValidationMasksTest {

    @Test
    public void checkUrl() {
        Pattern p = Pattern.compile(ValidationMasks.url);
        assertTrue(p.matcher("http://www.eionet.europa.eu/").matches());
        assertTrue(p.matcher("www.eionet.europa.eu").matches()); // Do we really want to allow this?
    }

    @Test
    public void checkNumber() {
        Pattern p = Pattern.compile(ValidationMasks.number);
        assertTrue(p.matcher("9").matches());
        assertTrue(p.matcher("9.999").matches());
        assertFalse(p.matcher("-9.999").matches());
    }

    @Test
    public void checkInspireId() {
        Pattern p = Pattern.compile(ValidationMasks.inspireId);
        assertTrue(p.matcher("_1").matches());
        assertTrue(p.matcher("AQD-A7_X").matches());
        assertFalse(p.matcher("http://www.eionet.europa.eu/").matches());
    }
}
