/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-06
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-13
 * Description: This source code asks the user for the relevant details that aren't fields for
 *              any Objects.
 */

package frontend.console;

import general.ReadConfigFile;
import general.UserInput;

public class GetDetails
{
    /**
     * SUBMODULE getHost
     * DESCRIPTION: This submodule is used to get the host name for the MySQL Database.
     * @return hostName (String)
     */
    public static String getHost()
    {
        /// DECLERATION OF VARIABLES
        String hostName;
        
        /// DEFINEMENT OF METHOD
        hostName = ReadConfigFile.getHostProperty();
        return hostName;
    }//END getHost
    
    /**
     * SUBMODULE getUsername
     * DESCRIPTION: This submodule ask the user for the username that they wish to
     *              enter in as the user for the MySQL Database.
     * @return username (String)
     */
    public static String getUsername()
    {
        /// DECLERATION OF VARIABLES
        String username;
        String prompt = "Username: ";
        
        /// DEFINEMENT OF METHOD
        username = UserInput.getString(prompt);
        return username;
    }//END getUsername
    
    /**
     * SUBMODULE getPassword
     * DESCRIPTION: This submodule asks the user for the password to the user account that they
     *              are using for the MySQL database.
     * @return password (String)
     */
    public static String getPassword()
    {
        /// DECLERATION OF VARIABLES
        String password;
        String prompt = "Password: ";
        
        /// DEFINEMENT OF METHOD
        password = UserInput.getString(prompt);
        return password;
    }//END getPassword
    
    /**
     * SUBMODULE getNewTable
     * DESCRIPTION: This submodule asks the user for the name of the new table that they would like
     *              to create. This takes on the name of the file that they wish to read in and use
     *              the values from.
     * @param prompt (String)
     * @return curTable (String)
     */
    public static String getNewTable(String prompt)
    {
        /// DECLERATION OF VARIABLES
        String curTable;
        
        /// DEFINEMENT OF METHOD
        curTable = UserInput.getString(prompt);
        return curTable;
    }//END getCurrentTable
    
    /**
     * SUBMODULE getCSVFilePath
     * DESCRIPTION: This submodule calls the appropriate methods to get the file path from the
     *              config.properties file that is for the directory that stores the .csv files that
     *              are to be used.
     * @return filePath (String)
     */
    public static String getCSVFilePath()
    {
        /// DECLERATION OF VARIABLES
        String filePath;
        
        /// DEFINEMENT OF METHOD
        filePath = ReadConfigFile.getCSVFilePath();
        return filePath;
    }//END getCSVFilePath
}//END class GetDetails
