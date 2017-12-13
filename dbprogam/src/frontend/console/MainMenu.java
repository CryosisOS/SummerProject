/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-13
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-13
 * Description: This class' responsibility is to continue the program after the connection to the
 *              database has been established.
 */

package frontend.console;

//IMPORTS
import general.UpdateConfigFile;

public class MainMenu
{
    public static void continueProgramToMain()
    {
        /// DECLERATION OF VARIABLES
        String prevTable;
        String curTable;
        
        /// DEFINEMENT OF METHOD
        prevTable = GetDetails.getPreviousTable(); //This gets the 'current' table name
        UpdateConfigFile.updatePreviousTable(prevTable);//This sets the previous table property to the 'current' table
        curTable = GetDetails.getCurrentTable();//This asks for the name of the now 'current' table.
        UpdateConfigFile.updateCurrentTable(curTable);//This sets the current table property to the 'current' table
    }//END continueProgramToMain
}//END class MainMenu
