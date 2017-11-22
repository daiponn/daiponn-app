package com.kasahara;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */

//    private App app = null;
//
//    public AppTest( String testName ) {
//        super( testName );
//        app = new App();
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite() {
//        return new TestSuite( AppTest.class );
//    }
//
//    /**
//     * Rigourous Test :-)
//     */
//    public void testApp() {
//        assertNotNull( app );
//    }
//
//    public void testGetMessage(){
//        String name = "hoge";
//        String msg = "Hi," + name + ". Welcome to Maven World!";
//        assertEquals(app.getMessage("hoge"), msg);
//    }

    public AppTest( String testName ) {
        super( testName );
    }

    public static Test suite(){
        return new TestSuite( AppTest.class);
    }

    public void testApp(){
        App app = new App();
        assertNotNull(app);
    }
}
