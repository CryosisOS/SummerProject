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
import static frontend.console.JavaQueries.continueToQueries;
import general.ReadConfigFile;
import general.UpdateConfigFile;
import general.UserInput;

import static java.lang.System.out;

public class MainMenu
{
    public static void continueProgramToMain()
    {
        /// DECLERATION OF VARIABLES
        int option;
        boolean isNotValid = true;
        String prompt = "\nWhich option would you like?\n1.Work with current tables.\n2.Current Table.\n"+
                        "3.Work with two new tables.\n\nPlease input the number of the option: ";
        String prevTable;
        String curTable;
        
        /// DEFINEMENT OF METHOD
        /* Listing the tables that are currently being worked with. */
        prevTable = ReadConfigFile.getPreviousTable();
        out.print("The previous table is: "+prevTable+"\n");
        curTable = ReadConfigFile.getCurrentTable();
        out.print("The current table is: "+curTable+"\n");
        
        /* Asking the user if they want to continue working with the current tables, or update them. */
        do
        {
            isNotValid = true;
            option = UserInput.getInteger(prompt);
            switch(option)
            {
                case 1:
                    useCurrentTables(); //Write method
                    break;
                case 2:
                    updateCurrentTables(prevTable, curTable);
                    break;
                case 3:
                    updateBothTables(); //Write method.
                    break;
                default:
                    out.print("\nThis is an invalid option. Please try again.\n");
            }//END Switch
        }while(isNotValid);
    }//END continueProgramToMain
    
    public static void useCurrentTables()
    {
        out.print("The current tables will be used.\n");
        continueToQueries();//Write method.
    }//END useCurrentTables
    
    public static void updateCurrentTables(String prevTable, String curTable)
    {
        /// DECLERATION OF VARIABLES
        String prompt = "Please enter in the name of the current months .csv file\n";
        
        /// DEFINEMENT OF METHOD
        prevTable = ReadConfigFile.getCurrentTable(); //This gets the 'current' table name
        UpdateConfigFile.updatePreviousTable(prevTable);//This sets the previous table property to the 'current' table
        curTable = GetDetails.getCurrentTable(prompt);//This asks for the name of the now 'current' table.
        UpdateConfigFile.updateCurrentTable(curTable);//This sets the current table property to the 'current' table
        continueToQueries();
    }//END updateCurrentTables
    
    public static void updateBothTables()
    {
        /// DECLERATION OF VARIABLES
        String promptOne = "Please enter in the name of the first .csv file.";
        String promptTwo = "Please enter in the name of the second .csv file.";
        String tempPrevTable;
        String tempCurTable;
        
        /// DEFINEMENT OF METHOD
        tempPrevTable = GetDetails.getCurrentTable(promptOne);//Getting one of the table name.
        tempCurTable = GetDetails.getCurrentTable(promptTwo);//Getting the other table name.
        UpdateConfigFile.updatePreviousTable(tempPrevTable);//Setting the first table in the config file.
        UpdateConfigFile.updateCurrentTable(tempCurTable);//Setting the second table in the config file.
        continueToQueries();
    }//END updateBothTables()
}//END class MainMenu
