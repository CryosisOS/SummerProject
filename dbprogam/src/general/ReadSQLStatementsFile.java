/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-21
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-25
 * Description: This class handles the responsibility of reading the sqlstatements.properties file.
 */
package general;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.out;

public class ReadSQLStatementsFile
{
    /**
     * SUBMODULE getCreateTableStatement
     * DESCRIPTION: This submodule reads the sqlstatements.properties file for the
     *              SQL Statement for creating the table.
     * @return stmt (String)
     */
    public static String getCreateTableStatement()
    {
        /// DECLERATION OF VARIABLES
        String stmt = null;//As default
        File file = null;
        Properties prop = new Properties();
        FileInputStream input = null;
        
        /// DEFINEMENT OF METHOD
        try
        {
            //Getting the non-absolute filepath for the properties file.
            file = new File(ReadConfigFile.class.getClassLoader().getResource("sqlstatements.properties").getPath());
            //Starting the file Stream.
            input = new FileInputStream(file);
            
            //Loading the properties file.
            prop.load(input);
            
            //Getting the hostname from the properties file.
            stmt = prop.getProperty("createtable");
            
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
        return stmt;
    }//END getCreateTableStatement
    
    /**
     * SUBMODULE getInsertStatement
     * DESCRIPTION: This submodule reads the sqlstatements.properties file and gets the
     *              property for the insertValue
     * @return stmt (String)
     */
    public static String getInsertStatement()
    {
        /// DECLERATION OF VARIABLES
        String stmt = null;//As default
        File file = null;
        Properties prop = new Properties();
        FileInputStream input = null;
        
        /// DEFINEMENT OF METHOD
        try
        {
            //Getting the non-absolute filepath for the properties file.
            file = new File(ReadConfigFile.class.getClassLoader().getResource("sqlstatements.properties").getPath());
            //Starting the file Stream.
            input = new FileInputStream(file);
            
            //Loading the properties file.
            prop.load(input);
            
            //Getting the hostname from the properties file.
            stmt = prop.getProperty("insertvalues");
            
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
        return stmt;
    }//END getCreateTableStatement
    
    /**
     * SUBMODULE getTableViewStatements
     * DESCRIPTION: This submodule reads the sqlstatments.properties file and and returns the
     *              string stored at the property 'gettableview'
     * @return stmt (String)
     */
    public static String getTableViewStatement()
    {
        /// DECLERATION OF VARIABLES
        String stmt = null;//As default
        File file = null;
        Properties prop = new Properties();
        FileInputStream input = null;
        
        /// DEFINEMENT OF METHOD
        try
        {
            //Getting the non-absolute filepath for the properties file.
            file = new File(ReadConfigFile.class.getClassLoader().getResource("sqlstatements.properties").getPath());
            //Starting the file Stream.
            input = new FileInputStream(file);
            
            //Loading the properties file.
            prop.load(input);
            
            //Getting the hostname from the properties file.
            stmt = prop.getProperty("gettableview");
            
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
        return stmt;
    }//END getTableViewStatement
    
    public static String getTableList()
    {
        /// DECLERATION OF VARIABLES
        String stmt = null;//As default
        File file = null;
        Properties prop = new Properties();
        FileInputStream input = null;
        
        /// DEFINEMENT OF METHOD
        try
        {
            //Getting the non-absolute filepath for the properties file.
            file = new File(ReadConfigFile.class.getClassLoader().getResource("sqlstatements.properties").getPath());
            //Starting the file Stream.
            input = new FileInputStream(file);
            
            //Loading the properties file.
            prop.load(input);
            
            //Getting the hostname from the properties file.
            stmt = prop.getProperty("tablelist");
            
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
        return stmt;
    }//END getTableList
}//END class ReadSQLStatementsFile
