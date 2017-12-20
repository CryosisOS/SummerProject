/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-16
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-16
 * Description: This source code takes care of creating tables, with specific names and everything else
 *              with it.
 */

package frontend.connection.createObjects;

//IMPORTS

import frontend.connection.DatabaseConnection;
import general.ReadConfigFile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static general.ReadConfigFile.getCreateTableStatement;

public class CreateTable
{
    //PRIVATE STATIC FIELDS
    private static final String varchar = " ? VARCHAR (5) NOT NULL,";
    private static final String date = " ? CHAR (10) NOT NULL,";
    private static final String percent = " ? VARCHAR (4) NOT NULL,";
    
    //CLASS FIELDS
    private String tableName;
    private String [] unitArr;
    private DatabaseConnection dbconn;
    private Connection con;
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // REST OF BODY //
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    
    /**
     * ALTERNATE CONSTRUCTOR
     * DESCRIPTION: This submodule is the alternate constructor.
     * @param inDbconn (DatabaseConnection)
     */
    public CreateTable(DatabaseConnection inDbconn)
    {
        dbconn = inDbconn;
        setTableName();
    }//END ALTERNATE CONSTRUCTOR
    
    public void setTableName()
    {
        tableName = ReadConfigFile.getCurrentTable();
    }//END setTableName
    
    /**
     * SUBMODULE setUnitArr
     * DESCRIPTION: This submodule creates an array with all the units in them.
     */
    private void setUnitArr()
    {
        /// DECLERATION OF VARIABLES
        String unitString;
        
        /// DEFINEMENT OF METHOD
        unitString = ReadConfigFile.getUnits();
        unitArr = unitString.split(",");
    }//END setUnitArr
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // PUBLIC DOING METHODS //
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    
    /**
     * SUBMODULE createNewStringDatabaseTable
     * DESCRIPTION: This method creates a new table in the database with it's appropriate name.
     *              This method is for the String CSV File with the fields that are filled with
     *              'Yes', 'No' or 'Close"
     * @throws SQLException
     */
    public void createNewStringDatabaseTable() throws SQLException
    {
        /// DECLERATION OF VARIABLES
        int stmtIndex = 0;
        String unitDec = "";
        String finalStatement;
        
        /// DEFINEMENT OF METHOD
        setUpConnection();
        setTableName();
        for(int ii=0; ii<unitArr.length;ii++)
        {
            unitDec = unitDec+varchar;
        }//END FOR
        try
        {
            finalStatement = getCreateTableStatement();
            finalStatement  = finalStatement + unitDec;
            PreparedStatement createTable = con.prepareStatement(finalStatement);
            createTable.setString(1, tableName);
            for(int ii=0;ii<unitArr.length;ii++)
            {
                stmtIndex = ii+2;
                createTable.setString(stmtIndex, unitArr[ii]);
            }//END FOR
            createTable.executeUpdate();
        }//END TRY
        catch(SQLException sqlex)
        {
            throw sqlex;
        }//END CATCH
    }//END createNewStringDatabaseTable
    
    /**
     * SUBMODULE createNewDateDatabaseTable
     * DESCRIPTION: This method creates a new table in the database with it's appropriate name.
     *              This method is for the CSV File with the fields that are filled with the
     *              dates.
     * @throws SQLException
     */
    public void createNewDateDatabaseTable() throws SQLException
    {
        /// DECLERATION OF VARIABLES
        int stmtIndex = 0;
        String unitDec = "";
        String finalStatement;
        
        /// DEFINEMENT OF METHOD
        setUpConnection();
        setTableName();
        for(int ii=0; ii<unitArr.length;ii++)
        {
            unitDec = unitDec+date;
        }//END FOR
        try
        {
            finalStatement = getCreateTableStatement();
            finalStatement  = finalStatement + unitDec;
            PreparedStatement createTable = con.prepareStatement(finalStatement);
            createTable.setString(1, tableName);
            for(int ii=0;ii<unitArr.length;ii++)
            {
                stmtIndex = ii+2;
                createTable.setString(stmtIndex, unitArr[ii]);
            }//END FOR
            createTable.executeUpdate();
        }//END TRY
        catch(SQLException sqlex)
        {
            throw sqlex;
        }//END CATCH
    }//END createNewDateDatabaseTable
    
    /**
     * SUBMODULE createNewPercentDatabaseTable
     * DESCRIPTION: This method creates a new table in the database with it's appropriate name.
     *              This method is for the CSV File that is filled with the Percentage of a students
     *              progress.
     * @throws SQLException
     */
    public void createNewPercentDatabaseTable() throws SQLException
    {
        /// DECLERATION OF VARIABLES
        int stmtIndex = 0;
        String unitDec = "";
        String finalStatement;
        
        /// DEFINEMENT OF METHOD
        setUpConnection();
        setTableName();
        for(int ii=0; ii<unitArr.length;ii++)
        {
            unitDec = unitDec+percent;
        }//END FOR
        try
        {
            finalStatement = getCreateTableStatement();
            finalStatement  = finalStatement + unitDec;
            PreparedStatement createTable = con.prepareStatement(finalStatement);
            createTable.setString(1, tableName);
            for(int ii=0;ii<unitArr.length;ii++)
            {
                stmtIndex = ii+2;
                createTable.setString(stmtIndex, unitArr[ii]);
            }//END FOR
            createTable.executeUpdate();
        }//END TRY
        catch(SQLException sqlex)
        {
            throw sqlex;
        }//END CATCH
    }//END createNewPercentDatabaseTable
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // PUBLIC DOING METHODS //
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    
    /**
     * SUBMODULE setUpConnection
     * DESCRIPTION: This submodule sets up the class field con from the Object dbconn.
     */
    private void setUpConnection()
    {
        con = dbconn.getConn();
    }//END setUpConnection
    
}//END class CreateTable
