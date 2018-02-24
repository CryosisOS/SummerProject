/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-16
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-21
 * Description: This source code takes care of creating tables, with specific names and everything else
 *              with it.
 */

package frontend.connection.createObjects;

//IMPORTS
import frontend.connection.DatabaseConnection;
import general.ReadConfigFile;
import general.ReadUnitsFile;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static general.ReadSQLStatementsFile.getCreateTableStatement;

public class CreateTable extends ObjectConnection
{
    //PRIVATE STATIC FIELDS
    private static final String varchar = " ? VARCHAR (5) NOT NULL,";
    private static final String date = " ? CHAR (10) NOT NULL,";
    private static final String percent = " ? VARCHAR (6) NOT NULL,";
    
    //CLASS FIELDS
    private String tableName;
    private String [] unitArr;
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // REST OF BODY //
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    
    /**
     * ALTERNATE CONSTRUCTOR
     * DESCRIPTION: This submodule is the alternate constructor.
     * @param inDbconn (DatabaseConnection)
     */
    public CreateTable(DatabaseConnection inDbconn) throws SQLException
    {
        super(inDbconn);
        this.setTableName();
        this.setUnitArr();
        try
        {
            this.createNewStringDatabaseTable();
            this.createNewDateDatabaseTable();
            this.createNewPercentDatabaseTable();
        }//END TRY
        catch(SQLException sqlex)
        {
            throw sqlex;
        }//END CATCH
        
    }//END ALTERNATE CONSTRUCTOR
    
    /**
     * SUBMODULE setTableName
     * DESCRIPTION: This submodule sets the table name by calling the method that reads the config
     *              file.
     */
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
        unitString = ReadUnitsFile.getUnits();
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
        super.setUpConnection();
        setTableName();
        for(int ii=0; ii<unitArr.length;ii++)
        {
            unitDec = unitDec+varchar;
        }//END FOR
        try
        {
            finalStatement = getCreateTableStatement();
            finalStatement  = finalStatement + unitDec;
            PreparedStatement createTable = getCon().prepareStatement(finalStatement);
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
            PreparedStatement createTable = getCon().prepareStatement(finalStatement);
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
        String unitDec = " ?";
        String finalStatement;
        PreparedStatement createTable;
        
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
            createTable = getCon().prepareStatement(finalStatement);
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
}//END class CreateTable
