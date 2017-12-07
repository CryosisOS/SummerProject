/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-06
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-07
 * Description: This class handles the responsibility of reading the config file at the beginning of the
 *              program to initialise fields that are hard coded in.
 */


package general;

//IMPORTS
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.Properties;
import static java.lang.System.out;

/* The config file is hardcoded in the sense that any modification to the file will cause the program
 * to not run and crash.*/
public class ReadConfigFile
{
    public static String getHostProperty()
    {
        /// DECLERATION OF VARIABLES
        String hostname = null;//As default
        File file = null;
        Properties prop = new Properties();
        FileInputStream input = null;
        
        /// DEFINEMENT OF METHOD
        try
        {
            //Getting the non-absolute filepath for the properties file.
            file = new File(ReadConfigFile.class.getClassLoader().getResource("config.properties").getPath());
            //Starting the file Stream.
            input = new FileInputStream(file);
            
            //Loading the properties file.
            prop.load(input);
            
            //Getting the hostname from the properties file.
            hostname = prop.getProperty("hostname");
            
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
        return hostname;
    }
}//END class ReadConfigFile
