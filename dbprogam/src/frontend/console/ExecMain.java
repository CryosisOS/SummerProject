/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-05
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-05
 * Description: This source code contains the main method for the console application front end of this
 *              program.
 */

package frontend.console;

import java.util.*;
import static java.lang.System.out;
import general.*;

public class ExecMain
{
    public static void main(String [] args)
    {
        displayFirstSection();
    }//END main
    
    /*
     * The character length for this screen is 51 characters per line.
     */
    private static void displayFirstSection()
    {
        out.println("--------------------------------------------------");
        out.println("                   Developed by                   ");
        out.println("          Geonyosis Software Development          ");
        out.println("--------------------------------------------------");
    }//END displayFirstSection
}//END class ExecMain
