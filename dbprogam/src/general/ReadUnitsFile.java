/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-21
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-21
 * Description: This class handles the responsibility of reading the units.properties file.
 */

package general;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.out;

public class ReadUnitsFile
{
    /**
     * SUBMODULE getUnits
     * DESCRIPTION: This method returns the unit String, from the units.properties file, to the caller.
     * @return unitString (String)
     */
    public static String getUnits()
    {
        /// DECLERATION OF VARIABLES
        String unitString = null;//As default
        File file = null;
        Properties prop = new Properties();
        FileInputStream input = null;
        
        /// DEFINEMENT OF METHOD
        try
        {
            //Getting the non-absolute filepath for the properties file.
            file = new File(ReadConfigFile.class.getClassLoader().getResource("units.properties").getPath());
            //Starting the file Stream.
            input = new FileInputStream(file);
            
            //Loading the properties file.
            prop.load(input);
            
            //Getting the hostname from the properties file.
            unitString = prop.getProperty("units");
            
            //Closing the FileInputStream.
            input.close();
        }//END TRY
        catch(IOException ex)
        {
            if (input != null)
            {
                try
                {
                    input.close();
                }//END TRY
                catch (IOException e)
                {
                    e.printStackTrace();
                }//END CATCH
            }//ENDIF
            out.println(ex.getMessage());
            ex.printStackTrace();
        }//END CATCH
        catch(NullPointerException nex)
        {
            out.println("The file path for the properties file cannot be found or does not exist");
            out.println(nex.getMessage());
        }//END CATCH
        return unitString;
    }//END getUnits
}//END class ReadUnitsFile
