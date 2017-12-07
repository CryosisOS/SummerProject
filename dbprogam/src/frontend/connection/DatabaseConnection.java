/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-06
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-07
 * Description: This source code handles the responsibility of the Database Connection.
 */

package frontend.connection;

//IMPORTS
import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.jdbc.Driver;
import java.sql.SQLException;

public class DatabaseConnection
{
    ///CLASS FIELDS
    private String host;
    private String un;
    private String pw;
    
    /*+++++++++++++++++++++++*\
    /// DEFAULT CONSTRUCTOR ///
    /*+++++++++++++++++++++++*/
    public DatabaseConnection()
    {
        host = null;
    }//END DEFAULT CONSTRUCTOR
    
    /*+++++++++++++++++++++++++*\
    /// ALTERNATE CONSTRUCTOR ///
    /*+++++++++++++++++++++++++*/
    public DatabaseConnection(String inHost, String inUn, String inPw) throws IllegalArgumentException
    {
        try
        {
            setHost(inHost);
            setUn(inUn);
            setPw(inPw);
        }//END TRY
        catch(IllegalArgumentException ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }//END CATCH
    }//END ALTERNATE CONSTRUCTOR
    
    
    /*++++++++++++++++++++++++++++++
     * SETTERS                     +
     +++++++++++++++++++++++++++++*/
    
    /**
     * SUBMODULE setHost
     * @param inHost (String)
     */
    public void setHost(String inHost)
    {
        if(validString(inHost))
        {
            host = "jdbc:mysql://"+inHost+"?autoReconnect=true&useSSL=false";
        }
        else
        {
            throw new IllegalArgumentException("The host name is invalid.");
        }//ENDIF
    }//END setHost
    
    /**
     * SUBMODULE setUn
     * @param inUn (String)
     */
    public void setUn(String inUn)
    {
        if(validString(inUn))
        {
            un = inUn;
        }
        else
        {
            throw new IllegalArgumentException("The username is invalid.");
        }//ENDIF
    }//END setUn
    
    /**
     * SUBMODULE setPw
     * @param inPw (String)
     */
    public void setPw(String inPw)
    {
        if(validString(inPw))
        {
            pw = inPw;
        }
        else
        {
            throw new IllegalArgumentException("The password is invalid.");
        }//ENDIF
    }//END setPw
    
    
    /**
     * SUBMODULE establishConnection
     * DESCRIPTION: This method tries to establish a connection to the database for this Object.
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SQLException
     */
    public void establishConnection() throws IllegalAccessException, InstantiationException, SQLException
    {
        Driver.class.newInstance();
        Connection conn= DriverManager.getConnection(host,un,pw);
    
    }//END tryConnection
    
    /**
     * SUBMODULE validString
     * DESCRIPTION: This method checks if the string is valid, regardless of the outcome it will return either
     *              true or false.
     * @param inString (String)
     * @return isValid (Boolean)
     */
    private boolean validString(String inString)
    {
        /// DECLERATION OF VARIABLES
        boolean isValid = false;
        
        /// DEFINEMENT OF METHOD
        if(!inString.equals(""))
        {
            isValid = true;
        }//ENDIF
        return isValid;
    }//END validString
    
    
    
}//END class DatabaseConnection