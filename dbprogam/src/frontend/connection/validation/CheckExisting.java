/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-26
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-26
 * Description: This class contains any methods that are associated with checking if something
 *              exists in the Database.
 */

package frontend.connection.validation;

//IMPORTS
import frontend.connection.DatabaseConnection;
import general.ReadSQLStatementsFile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.lang.System.out;

public class CheckExisting
{
    /**
     * SUBMODULE findTable
     * DESCRIPTION: This submodule gets a list of the tables that are in the database
     *              and checks to see if a table is present or not.
     * @param inDbconn (DatabaseConnection)
     * @param tableName (String)
     * @return found (boolean)
     */
    public static boolean findTable(DatabaseConnection inDbconn, String tableName)
    {
        /// DECLERATION OF VARIABLES
        boolean found = false;
        String stmtStr;
        Connection conn;
        PreparedStatement stmt;
        ResultSet tableList;
        
        /// DEFINEMENT OF METHOD
        try
        {
            conn = inDbconn.getConn();
            stmtStr = ReadSQLStatementsFile.getTableList();
            stmt = conn.prepareStatement(stmtStr);
            tableList = stmt.executeQuery();
            while(tableList.next() && !tableName.equals(tableList.getString(1)))
            {
                if(tableName.equals(tableList.getString(1)))
                {
                    found = true;
                }
                else
                {
                    found = false;
                }//ENDIF
            }//END WHILE
        }//END TRY
        catch(SQLException sqlex)
        {
            out.println(sqlex.getMessage());
            sqlex.printStackTrace();
        }//END CATCH
        return found;
    }//END findTable
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    //PRIVATE STATIC METHODS //
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    
}//END class CheckExisting
