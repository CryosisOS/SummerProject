/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-24
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-25
 * Description: This class has the responsibility of getting a table view from
 *              the database with the imported name.
 */

package frontend.connection.sqlqueries;

//IMPORTS
import frontend.connection.DatabaseConnection;
import general.ReadSQLStatementsFile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

public class TableViews
{
    public static ResultSet getTableView(DatabaseConnection inDbconn, String tableName)
    {
        /// DECLERATION OF VARIABLES
        String stmt;
        PreparedStatement getTable;
        ResultSet outTableView = null;
        Connection conn;
        
        /// DEFINEMENT OF METHOD
        try
        {
            conn = setConn(inDbconn);
            stmt = ReadSQLStatementsFile.getTableViewStatement();
            getTable = conn.prepareStatement(stmt);
            getTable.setString(1,tableName);
            outTableView = getTable.executeQuery();
            
        }//END TRY
        catch(SQLException sqlex)
        {
            out.print(sqlex.getMessage());
            sqlex.printStackTrace();
        }//END CATCH
        return outTableView;
    }//END tableView
    
    private static Connection setConn(DatabaseConnection inDbconn)
    {
        Connection conn;
        conn = inDbconn.getConn();
        return conn;
    }//END setConn
}//END class TableViews
