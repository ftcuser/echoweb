package com.citizant.seleniumtest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws SQLException 
     */
    public void testApp() throws SQLException
    {
        assertTrue( true );
        String sql = "select * from table where id=?";
        Connection conn = null;
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, 123);
        ResultSet rs = pst.executeQuery();
        int rows = pst.executeUpdate();
    }
}
