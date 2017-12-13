/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-06
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-13
 * Description: This source code asks the user for the relevant details.
 */

package frontend.console;

import general.ReadConfigFile;
import general.UserInput;

public class GetDetails
{
    public static String getHost()
    {
        /// DECLERATION OF VARIABLES
        String hostName;
        
        /// DEFINEMENT OF METHOD
        hostName = ReadConfigFile.getHostProperty();
        return hostName;
    }//END getHost
    
    public static String getUsername()
    {
        /// DECLERATION OF VARIABLES
        String username;
        String prompt = "Username: ";
        
        /// DEFINEMENT OF METHOD
        username = UserInput.getString(prompt);
        return username;
    }//END getUsername
    
    public static String getPassword()
    {
        /// DECLERATION OF VARIABLES
        String password;
        String prompt = "Password: ";
        
        /// DEFINEMENT OF METHOD
        password = UserInput.getString(prompt);
        return password;
    }//END getPassword
    
    public static String getPreviousTable()
    {
        /// DECLERATION OF VARIABLES
        String prevTable;
    
        /// DEFINEMENT OF METHOD
        prevTable = ReadConfigFile.getPreviousTable();
        return prevTable;
    }//END getPreviousTable
    
    public static String getCurrentTable(String prompt)
    {
        /// DECLERATION OF VARIABLES
        String curTable;
        
        /// DEFINEMENT OF METHOD
        curTable = UserInput.getString(prompt);
        return curTable;
    }//END getCurrentTable
}//END class GetDetails
