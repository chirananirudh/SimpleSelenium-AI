package gettingStarted;

import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestNGTest {

    private static final Logger logger = LoggerFactory.getLogger(TestNGTest.class);

    @Test
    public void testLogin() {
        logger.info("Testing the login operation");
    }

    @Test
    public void testCreateUser() {
        logger.info("Testing the Create User operaetion");
    }

    @Test
    public void testLogoff() {
        logger.info("Testing the log off operation");
    }

    @Test
    public void testSettings() {
        logger.info("Testing the settings operation");
    }

}
