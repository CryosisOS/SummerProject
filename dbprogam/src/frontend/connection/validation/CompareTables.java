/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-25
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-26
 * Description: This class has the responsibility of checking if two tables are the same.
 *              In the number of columns and the same column names.
 */

package frontend.connection.validation;

//IMPORTS
import frontend.connection.DatabaseConnection;
import frontend.connection.sqlqueries.TableViews;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import static java.lang.System.out;

public class CompareTables
{
    // CLASS FIELDS
    private String reason;
    
    CompareTables()
    {
        reason = "";
    }//END DEFAULT CONSTRUCTOR
    
    /**
     * SUBMODULE compareTableTemplates
     * DESCRIPTION: This submodule checks if two tables have the same template by checking the
     *              number of columns and if they have the same fields.
     * @param inDbconn (DatabaseConnection)
     * @param tableOne (String)
     * @param tableTwo (String)
     * @return areEqual (boolean)
     */
    public static Boolean compareTableTemplates(DatabaseConnection inDbconn, String tableOne, String tableTwo)
    {
        /// DECLERATION OF VARIABLES
        boolean areEqual = false;
        String [] arrOne;
        String [] arrTwo;
        CompareTables cur = new CompareTables();
        ResultSet setOne;
        ResultSet setTwo;
        
        /// DEFINEMENT OF METHOD
        setOne = TableViews.getTableView(inDbconn, tableOne);
        arrOne = commitToArr(setOne);
        setTwo = TableViews.getTableView(inDbconn, tableTwo);
        arrTwo = commitToArr(setTwo);
        if(arrOne.length==arrTwo.length)
        {
            if(binaryCompare(arrOne, arrTwo))//When written up there will probs be better ways to do it.
            {
                areEqual = true;
            }
            else
            {
                cur.setReason("There a different columns in the table.");
                areEqual = false;
            }
        }
        else
        {
            cur.setReason("There a different number of columns.");
            areEqual = false;
        }//ENDIF
        return areEqual;
    }//END compareTable
    
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    //PRIVATE STATIC METHODS //
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    
    /**
     * SUBMODULE commitToArr
     * DESCRIPTION: This submodule takes a result set in and gets the column names and places
     *              them in array..
     * @param inSet (ResultSet)
     * @return (String Array)
     */
    private static String [] commitToArr(ResultSet inSet)
    {
        /// DECLERATION OF VARIABLES
        ResultSetMetaData set;
        String [] columnNames = null;
        
        /// DEFINEMENT OF METHOD
        try
        {
            set = inSet.getMetaData();
            columnNames = new String[set.getColumnCount()];
            for(int ii = 1; ii<=set.getColumnCount();ii++)
            {
                columnNames[ii] = set.getColumnName(ii);
            }//END FOR
        }//END TRY
        catch(SQLException sqlex)
        {
            out.print(sqlex.getMessage());
            sqlex.printStackTrace();
        }//END CATCH
        return columnNames;
    }//END commitToArr
    
    /**
     * SUBMODULE binaryCompare
     * DESCRIPTION: This submodule checks if two string arrays contain the same values in their
     *              indexes. Not necessarily in the same order.
     * @param inArrOne (String array)
     * @param inArrTwo (String array)
     * @return binaryMatchFound (boolean)
     */
    private static boolean binaryCompare(String [] inArrOne, String [] inArrTwo)
    {
        /// DECLERATION OF VARIABLES
        boolean binaryMatchFound = false;
        
        /// DEFINEMENT OF METHOD
        for(int ii = 0; ii<= inArrOne.length;ii++)
        {
            if(binarySearch(inArrTwo, inArrOne[ii]))
            {
                binaryMatchFound = true;
            }//ENDIF
        }//END FOR
        return binaryMatchFound;
    }//END binaryCompare
    
    /**
     * SUBMODULE binarySearch
     * DESCRIPTION: This submodule is passed a string array and a string and checks if the string
     *              is also inside the string array using a binary search.
     * @param inArr (String array)
     * @param index (String)
     * @return binaryMatch (boolean)
     */
    private static boolean binarySearch(String [] inArr, String index)
    {
        /// DECLERATION OF VARIABLES
        int left = 0;
        int right = (inArr.length) - 1;
        int middle;
        int indexAsc;
        int arrAsc;
        boolean binaryMatch = false;
    
    
        /// DEFINEMENT OF METHOD
        while (left <= right)
        {
            middle = (left + right) / 2;
            indexAsc = convertToASCII(index);
            arrAsc =  convertToASCII(inArr[middle]);
            if (indexAsc < arrAsc)
            {
                right = middle - 1;
            }
            else if (indexAsc > arrAsc)
            {
                left = middle + 1;
            }
            else if(indexAsc == arrAsc)
            {
                binaryMatch = true;
            }//ENDIF
        }//END WHILE
        return binaryMatch;
    }//END binarySearch
    
    /**
     * SUBMODULE convertToASCII
     * DESCRIPTION: This submodule converts a string to it's total ASCII value and returns
     *              that value to the caller.
     * @param inString (String)
     * @return ascii (int)
     */
    private static int convertToASCII(String inString)
    {
        /// DECLERATION OF VARIABLES
        int ascii = 0;
        char current;
        
        /// DEFINEMENT OF METHOD
        for(int ii = 0; ii <= inString.length() - 1;ii++)
        {
            current = inString.charAt(ii);
            ascii = (int)current;
        }//END FOR
        return ascii;
    }//END convertToASCII
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    //PUBLIC NON-STATIC METHODS //
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    
    /**
     * SUBMODULE getReason
     * DESCRIPTION: This submodule gets a copy of the class field reason.
     * @return copyReason (String)
     */
    public String getReason()
    {
        String copyReason;
        copyReason = reason;
        return copyReason;
    }//END getReasdon
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    //PRIVATE NON-STATIC METHODS //
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    
    /**
     * SUBMODULE setReason
     * DESCRIPTION: This submodule set the field class 'reason' to the imported string.
     * @param inString (String)
     */
    private void setReason(String inString)
    {
        reason = inString;
    }//END setReason
}//END class CompareTables
