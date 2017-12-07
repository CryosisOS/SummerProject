/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-05
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-05
 * Description: This source code contains the main method for the console application front end of this
 *              program.
 */

package frontend.console;

import static java.lang.System.out;
import frontend.connection.DatabaseConnection;
import java.sql.SQLException;

public class ExecMain
{
    public static void main(String [] args)
    {
        /// DECLERATION OF VARIABLES
        String host;
        String userName;
        String passWord;
        
        /// DEFINEMENT OF METHOD
        
        
        try
        {
            host = GetDetails.getHost();
            out.print("Host details have been loaded.\n");
            userName = GetDetails.getUsername();
            passWord = GetDetails.getPassword();
            DatabaseConnection dbconn = new DatabaseConnection(host, userName, passWord);
            dbconn.establishConnection();
            out.print("You have established the connection to the Server.\n");
            out.print("The username has been accepted.\n");
            out.print("The password has been accepted.\n");
        }//END TRY
        catch(SQLException | InstantiationException | IllegalAccessException ex)
        {
            out.println(ex.getMessage());
        }//END CATCH
    }//END main
}//END class ExecMain
