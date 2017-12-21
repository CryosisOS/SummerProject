/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-21
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-21
 * Description: This Object contains the fields that are common to all other objects in this package.
 */

package frontend.connection.createObjects;

//IMPORTS
import frontend.connection.DatabaseConnection;
import java.sql.Connection;

public abstract class ObjectConnection
{
    //CLASS FIELDS
    private DatabaseConnection dbconn;
    private Connection con;
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // REST OF BODY //
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    /**
     * ALTERNATE CONSTRUCTOR
     * @param inDbconn (DatabaseConnection)
     */
    public ObjectConnection(DatabaseConnection inDbconn)
    {
        dbconn = inDbconn;
    }//END ALTERNATE CONSTRUCTOR
    
    /**
     * SUBMODULE getCon
     * DESCRIPTION: This submodule returns the private class field Connection con
     * @return con (Connection)
     */
    protected Connection getCon()
    {
        return con;
    }//END getCon
    
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // PROTECTED DOING METHODS //
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    
    /**
     * SUBMODULE setUpConnection
     * DESCRIPTION: This submodule sets up the class field con from the Object dbconn.
     */
    protected void setUpConnection()
    {
        con = dbconn.getConn();
    }//END setUpConnection
}//END class ObjectConnection
