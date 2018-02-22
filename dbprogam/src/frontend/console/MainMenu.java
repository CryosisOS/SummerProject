/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-13
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-30
 * Description: This class' responsibility is to continue the program after the connection to the
 *              database has been established.
 */

package frontend.console;

//IMPORTS
import frontend.connection.DatabaseConnection;
import frontend.connection.createObjects.ValueInserter;
import frontend.connection.validation.CheckExisting;
import frontend.console.fileio.ReadInCSV;
import general.ReadConfigFile;
import general.UpdateConfigFile;
import general.UserInput;
import general.validation.CorrectTableType;

import java.io.IOException;
import java.sql.SQLException;

import static frontend.console.JavaQueries.continueToQueries;
import static java.lang.System.out;

public class MainMenu
{
    /**
     * SUBMODULE continueProgramToMain
     * DESCRIPTION: This submodule presents a menu that asks the user for the type of
     *              tables that they wish to compare.
     * @param inDbconn (DatabaseConnection)
     */
    public static void continueProgramToMain(DatabaseConnection inDbconn)
    {
        /// DECLERATION OF VARIABLES
        int choice;
        boolean repeat = true;
        String prompt = "What sort of tables would you like to compare?\n" +
                        "1. STRING\n2. PERCENTAGE\n3. DATE\n" +
                        "Please choose the number of the option: ";
        
        /// DEFINEMENT OF METHOD
        do
        {
            repeat = true;
            choice = UserInput.getInteger(prompt);
            out.print("\n");
            switch(choice)
            {
                case 1:
                {
                    repeat = false;
                    CorrectTableType tableType = new CorrectTableType();
                    tableType.setTableType("STRING");
                    break;
                }//END CASE 1
                case 2:
                {
                    repeat = false;
                    CorrectTableType tableType = new CorrectTableType();
                    tableType.setTableType("PERCENTAGE");
                    break;
                }//END CASE 2
                case 3:
                {
                    repeat = false;
                    CorrectTableType tableType = new CorrectTableType();
                    tableType.setTableType("DATE");
                    break;
                }//END CASE 3
                default:
                {
                    out.print("That is not a valid option please input a valid option.\n");
                    repeat = true;
                }//END DEFAULT
            }//END CASE STATEMENT
        }while(repeat);
        continueToTableChoices(inDbconn);
    }//END continue program to main
    
    /**
     * SUBMODULE continueToTableChoices
     * DESCRIPTION: This submodule offers the user to choose what type of pair of two tables
     *              that he would want to compare.
     * @param inDbconn (DatabaseConnection)
     */
    private static void continueToTableChoices(DatabaseConnection inDbconn)
    {
        /// DECLERATION OF VARIABLES
        int option;
        boolean isNotValid;
        String prompt = "\nWhich option would you like?\n1. Work with current tables.\n2. Current Table.\n"+
                        "3. Work with two new tables.\n\nPlease input the number of the option: ";
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
                    useCurrentTables(inDbconn);
                    isNotValid = false;
                    break;
                case 2:
                    updateCurrentTable(inDbconn, prevTable, curTable);
                    isNotValid = false;
                    break;
                case 3:
                    updateBothTables(inDbconn);
                    isNotValid = false;
                    break;
                default:
                    out.print("\nThis is an invalid option. Please try again.\n");
            }//END Switch
        }while(isNotValid);
    }//END continueProgramToMain
    
    private static void useCurrentTables(DatabaseConnection inDbconn)
    {
        out.print("The current tables will be used.\n");
        continueToQueries(inDbconn);//Write method.
    }//END useCurrentTables
    
    /**
     * SUBMODULE updateCurrentTable
     * DESCRIPTION: This submodule has the responsibility of reading in a new csv file
     *              that the user wants to compare to an already existing one.
     * @param inDbconn (DatabaseConnection)
     * @param prevTable (String) //May not be needed... Need to analyse the code here and get opinions.
     * @param curTable (String) //May not be needed... Need to analyse the code here and get opinions.
     */
    private static void updateCurrentTable(DatabaseConnection inDbconn, String prevTable, String curTable)
    {
        /// DECLERATION OF VARIABLES
        boolean repeat = true;
        String prompt = "Please enter in the name of the current months' .csv file: ";
        
        /// DEFINEMENT OF METHOD
        prevTable = ReadConfigFile.getCurrentTable(); //This gets the 'current' table name
        UpdateConfigFile.updatePreviousTable(prevTable);//This sets the previous table property to the 'current' table
        curTable = GetDetails.getNewTable(prompt);//This asks for the name of the now 'current' table.
        UpdateConfigFile.updateCurrentTable(curTable);//This sets the current table property to the 'current' table
        /* At this point we need to check that the table that they are imported is not already in the
         * database. If it is, we will go to the first option... By passing the rest of the code
         * in this method.
         */
        do
        {
            if(CheckExisting.findTable(inDbconn, curTable))
            {
                repeat = false;
                out.print("This new file has already been read in\n");
                out.print("Changing to option 3 where you are using two already\n");
                out.print("existing tables.\n");
                useCurrentTables(inDbconn);
            }
            else if(!CheckExisting.findTable(inDbconn, curTable))
            {
                repeat = false;
                readFileIntoDatabase(inDbconn, curTable);
                continueToQueries(inDbconn);
            }//ENDIF
        }while(repeat);//END DO-WHILE
    }//END updateCurrentTables
    
    /**
     * SUBMODULE updateBothTables
     * DESCRIPTION: This submodule handles the responsibility of making sure that two random
     *              tables are available for querying. If they do not exist in the database
     *              then they are read in to the database.
     * @param inDbconn (DatabaseConnection)
     */
    private static void updateBothTables(DatabaseConnection inDbconn)
    {
        /// DECLERATION OF VARIABLES
        boolean repeat = true;
        String promptOne = "Please enter in the name of the first .csv file: ";
        String promptTwo = "Please enter in the name of the second .csv file: ";
        String tempPrevTable;
        String tempCurTable;
        
        /// DEFINEMENT OF METHOD
        do
        {
            tempPrevTable = GetDetails.getNewTable(promptOne);//Getting one of the table name.
            tempCurTable = GetDetails.getNewTable(promptTwo);//Getting the other table name.
            UpdateConfigFile.updatePreviousTable(tempPrevTable);//Setting the first table in the config file.
            UpdateConfigFile.updateCurrentTable(tempCurTable);//Setting the second table in the config file.
            /* Here we now need to check if the table exists in the Database already or if it does not.
             * The method will be in it's own static class in the package directory
             * "frontend.connection.validation"
             */
            if(!CheckExisting.findTable(inDbconn, tempPrevTable))
            {
                readFileIntoDatabase(inDbconn, tempPrevTable);
                repeat = false;
            }
            else if(!CheckExisting.findTable(inDbconn, tempCurTable))
            {
                readFileIntoDatabase(inDbconn, tempCurTable);
                repeat = false;
            }
            continueToQueries(inDbconn);
        }while(repeat);//END DO-WHILE
    }//END updateBothTables()
    
    /**
     * SUBMODULE readFileIntoDatabase
     * DESCRIPTION: This submodule reads in the file that is imported to the database that is
     *              imported as well.
     * @param inDbconn (DatabaseConnection)
     * @param table (String)
     */
    private static void readFileIntoDatabase(DatabaseConnection inDbconn, String table)
    {
        /// DECLERATION OF VARIABLES
        ValueInserter insertContents = null;
        
        /// DEFINEMENT OF METHOD
        try
        {
            ReadInCSV prevFile = new ReadInCSV();
            prevFile.readInFileLines(table);
            insertContents = new ValueInserter(inDbconn, prevFile.getInFileContents());
            insertContents.insertValues();
        }//END TRY
        catch(IOException ioex)
        {
            out.print("This file does not exist\n");
            out.println("\n"+ioex.getMessage());
        }//END CATCH
        catch(SQLException sqlex)
        {
            out.print("A problem occurred with reading in the file to the database.\n");
            out.print("The following is information for the Developers when troubleshooting.\n");
            out.println(sqlex.getMessage());
            sqlex.printStackTrace();
        }//END CATCH
    }//END readFileIntroDatabase
}//END class MainMenu
