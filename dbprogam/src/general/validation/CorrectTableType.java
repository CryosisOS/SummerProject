/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-13
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-27
 * Description: This class' responsibility is to continue the program after the connection to the
 *              database has been established.
 */

package general.validation;

/**
 * This class is a Singleton Class.
 */
public class CorrectTableType
{
    private static CorrectTableType instance = null;
    private String tableType;
    
    /*+++++++++++++++++++++*\
     * DEFAULT CONSTRUCTOR *
    \*+++++++++++++++++++++*/
    public CorrectTableType()
    {
        //EXISTS ONLY TO DEFEAT INSTANTIATION
    }//END DEFAULT CONSTRUCTOR
    
    public void setTableType(String inType) throws IllegalArgumentException
    {
        if(inType.equals("STRING")||inType.equals("PERCENTAGE")||inType.equals("DATE"))
        {
            tableType = inType;
        }
        else
        {
            throw new IllegalArgumentException("This is not a correct table type.\n");
        }//ENDIF
    }//END
    
    public static CorrectTableType getInstance()
    {
        if(instance == null)
        {
            instance = new CorrectTableType();
        }
        return instance;
    }//END getInstance
    
    private String getTableType()
    {
        return tableType;
    }//END getTableType
}//END class CorrectTableType