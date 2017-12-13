/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-13
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-13
 * Description: This class handles the responsibility of updating the config file as fields are changed.
 */

package general;

//IMPORTS
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.Properties;
import static java.lang.System.out;

public class UpdateConfigFile
{
    
    public static void updatePreviousTable(String prevTable)
    {
        /// DECLERATION OF VARIABLES
        File file = null;
        Properties prop = new Properties();
        FileOutputStream output = null;
    
        /// DEFINEMENT OF METHOD
        try
        {
            //Getting the non-absolute filepath for the properties file.
            file = new File(ReadConfigFile.class.getClassLoader().getResource("config.properties").getPath());
            //Starting the file Stream.
            output = new FileOutputStream(file);
        
            //Getting the hostname from the properties file.
            prop.setProperty("prevtable",prevTable);
            prop.store(out, null);
            //Closing the FileInputStream.
            output.close();
        }//END TRY
        catch(IOException ex)
        {
            if (output != null)
            {
                try
                {
                    output.close();
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
    }//END updatePreviousTable
    
    public static void updateCurrentTable(String curTable)
    {
        /// DECLERATION OF VARIABLES
        File file = null;
        Properties prop = new Properties();
        FileOutputStream output = null;
    
        /// DEFINEMENT OF METHOD
        try
        {
            //Getting the non-absolute filepath for the properties file.
            file = new File(ReadConfigFile.class.getClassLoader().getResource("config.properties").getPath());
            //Starting the file Stream.
            output = new FileOutputStream(file);
        
            //Getting the hostname from the properties file.
            prop.setProperty("curtable",curTable);
            prop.store(out, null);
            //Closing the FileInputStream.
            output.close();
        }//END TRY
        catch(IOException ex)
        {
            if (output != null)
            {
                try
                {
                    output.close();
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
    }//END updateCurTable
}
