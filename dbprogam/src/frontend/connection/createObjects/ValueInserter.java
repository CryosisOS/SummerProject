/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-21
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-21
 * Description: This Object is used to insert values into the Table.
 */
package frontend.connection.createObjects;

//IMPORTS
import frontend.connection.DatabaseConnection;
import general.ReadConfigFile;
import general.ReadSQLStatementsFile;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ValueInserter extends ObjectConnection
{
    //CLASS FIELDS
    private String [][] arrToInsert;
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // REST OF BODY //
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    
    /**
     * ALTERNATE CONSTRUCTOR
     * IMPORT: inArr (String Array)
     * EXPORT: none
     */
    public ValueInserter(DatabaseConnection inDbconn, String [][] inArr)
    {
        super(inDbconn);
        setArrToInsert(inArr);
    }//END ALTERNATE CONSTRUCTOR
    
    /**
     * SUBMODULE setArrToInsert
     * DESCRIPTION: This is the setter for the class field arrToInsert.
     * @param inArr (String Array)
     */
    private void setArrToInsert(String [][] inArr)
    {
        arrToInsert = new String[inArr.length][inArr[0].length];
        for(int ii=0;ii<inArr.length;ii++)
        {
            for (int jj = 0; jj < inArr[0].length; jj++)
            {
                arrToInsert[ii][jj] = inArr[ii][jj];
            }//END FOR
        }//END FOR
    }//END setArrToInsert
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // PUBLIC DOING METHODS //
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    
    /**
     * SUBMODULE insertValues
     * DESCRIPTION: This submodule inserts all of the field class arrToInsert's values
     *              into a single string an executes a SQL statement with it.
     * @throws SQLException (Exception)
     */
    public void insertValues() throws SQLException
    {
        /// DECLERATION OF VARIABLES
        int stringLength;
        String tableName;
        String values = "";
        String stmt;
        StringBuilder finalString = null;
        PreparedStatement prepStmt;
        Statement commStmt = getCon().createStatement();
        
        
        /// DEFINEMENT OF METHOD
        stmt = ReadSQLStatementsFile.getInsertStatement();
        tableName = ReadConfigFile.getCurrentTable();
        for(int ii=0;ii<arrToInsert.length;ii++)
        {
            values = values + "(";
            for(int jj=0;jj<arrToInsert[0].length;jj++)
            {
                values = values + arrToInsert[ii][jj] +", ";
            }//END FOR
            values = values +"), ";
        }//END FOR
        stringLength = values.length();
        finalString = new StringBuilder(values);
        finalString.deleteCharAt(stringLength - 1);
        finalString.deleteCharAt(stringLength - 2);
        values = finalString.toString();
        try
        {
            prepStmt = getCon().prepareStatement(stmt);
            prepStmt.setString(1, tableName);
            prepStmt.setString(2, values);
            prepStmt.executeUpdate();
            commStmt.executeUpdate("COMMIT;");
        }//ENDTRY
        catch(SQLException sqlex)
        {
            throw sqlex;
        }//END CATCH
        
    }//END insertValues
    
}//END class ValueInserter
