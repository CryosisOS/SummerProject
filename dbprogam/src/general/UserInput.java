/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-05
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-07
 * Description: This source code is part of the general package to be used throughout the program.
 *              This particular piece of code is for UserInput. It handles all input that is needed
 *              from the user via the keyboard.
 */

package general;

//IMPORT STATEMENTS
import java.util.*;
import static java.lang.System.out;

public class UserInput
{
    /**
     * SUBMODULE getInteger
     * DESCRIPTION: This method prompts the user with an imported string and can receive input in the form
     *              of an integer. Which it then returns to the caller of this method.
     * @param prompt (String) Which is imported into the method.
     * @return userInput (Integer) Which is exported out of the method.
     */
    public static int getInteger(String prompt)
    {
        /// DECLERATION OF VARIABLES
        int userInput = 0;//DEFAULT VALUE SET.
        boolean isValid = false;
        Scanner sc = new Scanner(System.in);
        
        /// DEFINEMENT OF VARIABLES
        do
        {
            isValid = false;
            try
            {
                out.print(prompt);
                sc.reset();
                userInput = sc.nextInt();
                isValid = true;
            }
            catch(InputMismatchException im)
            {
                out.print("This is not an integer please try again.\n");
            }
        }while(!isValid);
        return userInput;
    }//END getInteger
    
    /**
     * SUBMODULE getString
     * DESCRIPTION: This method prompts the user with an imported string and can receive input which it then
     *              returns to the caller of this method.
     * @param prompt (String) Which is imported into the method.
     * @return userInput (String) Which is exported out of the method.
     */
    public static String getString(String prompt)
    {
        /// DECLERATION OF VARIABLES
        boolean isValid = false;
        String userInput;
        Scanner sc = new Scanner(System.in);

        /// DEFINEMENT OF METHOD
        do
        {
            isValid =false;
            out.print(prompt);
            sc.reset();
            userInput = sc.nextLine();
            if(!userInput.equals(""))
            {
                isValid = true;
            }
            else
            {
                out.print("This is an invalid input, please try again.\n");
            }//ENDIF
        }while(!isValid);
        return userInput;
    }//END getString
}//END class UserInput
