/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-14
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-26
 * Description: This source code takes care of reading in the CSV file and separating it into
 *              a string array that will be a class field for this Object.
 */

//IMPORTS
package frontend.console.fileio;

//IMPORTS
import java.io.*;
import general.ReadConfigFile;

public class ReadInCSV
{
    //CLASS FIELDS
    private String [][] inFileContents;
    private int indexesFilled;
    private int numFileLines;
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // REST OF BODY //
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    
    /**
     * SUBMODULE setInFileContentsRowSize
     * DESCRIPTION: This submodule set the number of rows the class field inFileContents to the
     *              integer minus one that is returned when the method getNumFileLines is called.
     */
    private void setInFileContentsRowSize()
    {
        inFileContents = new String[(getNumFileLines() - 1)][1];
        for(int ii = 0;ii<inFileContents.length;ii++)
        {
            for(int jj = 0; jj<inFileContents[0].length;jj++)
            {
                inFileContents[ii][jj] = null;
            }//END FOR
        }//END FOR
    }//END setInFileContentsSize
    
    /**
     * SUBMODULE setInFileContentsColumnSize
     * DESCRIPTION: This submodule takes an imported value and sets the number of columns of the
     *              class field array to the number that is imported.
     * @param numCols (Integer)
     */
    private void setInFileContentsColumnSize(int numCols)
    {
        int rows = inFileContents.length;
        inFileContents = new String[rows][numCols];
        for(int ii = 0;ii<inFileContents.length;ii++)
        {
            for(int jj = 0; jj<inFileContents[0].length;jj++)
            {
                inFileContents[ii][jj] = null;
            }//END FOR
        }//END FOR
        indexesFilled = 0;
    }//END setInFileContentsColumnSize
    
    public String[][] getInFileContents()
    {
        /// DECLERATION OF VARIABLES
        String[][] copyArr = new String[inFileContents.length][inFileContents[0].length];
        
        /// DEFINEMENT OF METHOD
        for(int ii=0;ii<inFileContents.length;ii++)
        {
            for(int jj=0;jj<inFileContents[0].length;jj++)
            {
                copyArr[ii][jj] = inFileContents[ii][jj];
            }//ENDFOR
        }//ENDFOR
        return copyArr;
    }//END getInFileContents
    
    /**
     * SUBMODULE incrementIndexesFilled
     * DESCRIPTION: When this submodule is called, it increments the number of lines that are filled
     *              in the array that is the class field inFileContents.
     */
    private void incrementIndexesFilled()
    {
        indexesFilled++;
    }//END incrementIndexesFilled
    
    /**
     * SUBMODULE setNumFileLines
     * DESCRIPTION: This submodule takes an imported integer and sets the field numFileLines to
     *              the imported integer.
     * @param inNumLines (Integer)
     */
    private void setNumFileLines(int inNumLines)
    {
        numFileLines = inNumLines;
    }//END setNumFileLines
    
    /**
     * SUBMODULE getNumFileLines
     * DESCRIPTION: This submodule, when called returns the value of the class field numFileLines
     *              to the caller.
     * @return numFileLines (Integer)
     */
    private int getNumFileLines()
    {
        return numFileLines;
    }//END getNumFileLines
    
    /*
     * The files that are to be read in master be placed inside the same folder
     * and that file path needs to be placed inside the config.properties file for this project.
     */
    
    /**
     * SUBMODULE readFileNumLines
     * DESCRIPTION: This submodule opens the file with the name that is imported and reads the
     *              number of lines that is on it.
     * @param fileName (String)
     * @throws IOException (Exception)
     */
    public void readFileNumLines(String fileName) throws IOException
    {
        /// DECLERATION OF VARIABLES
        int numLines;
        String line;
        String wholeFilePath = ReadConfigFile.getCSVFilePath()+fileName;
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
    
        /// DEFINEMENT OF METHOD
        try
        {
            fileStream = new FileInputStream(wholeFilePath);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
        
            numLines = 0;
            line = bufRdr.readLine();
            while(line != null)
            {
                numLines++;
                line = bufRdr.readLine();
            }//END WHILE
            fileStream.close();
            setNumFileLines(numLines);
            setInFileContentsRowSize();
        }//END TRY
        catch(IOException e)
        {
            throw e;
        }//END CATCH
    }//END readFileNumLines
    
    /**
     * SUBMODULE readInFileLines
     * DESCRIPTION: This submodule reads in the file lines and process the ones that need to processed.
     * @param fileName (String)
     * @throws IOException (Exception)
     */
    public void readInFileLines(String fileName) throws IOException
    {
        /// DECLERATION OF VARIABLES
        String line;
        //SOMEWHERE IN THE getCSVFilePath Method it is returning null.
        String wholeFilePath = ReadConfigFile.getCSVFilePath()+fileName;
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        
        /// DEFINEMENT OF METHOD
        if(numFileLines==0)
        {
            readFileNumLines(fileName);
        }//ENDIF
        try
        {
            fileStream = new FileInputStream(wholeFilePath);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();//Skip the firstLine
            for(int ii=0;ii<getNumFileLines();ii++)
            {
                line = bufRdr.readLine();
                if(line != null)
                {
                    processLine(line);
                }//ENDIF
            }//END FOR
        }//END TRY
        catch(IOException e)
        {
            throw e;
        }//END CATCH
        
    }//END readInFileLines
    
    /**
     * SUBMODULE processLine
     * DESCRIPTION: This submodule takes a string as an import and process it to fill the array
     * @param inLine (String)
     */
    private void processLine(String inLine)
    {
        /// DECLERATION OF VARIABLES
        int numCols;
        String [] processedLine;
        
        /// DEFINEMENT OF METHOD
        processedLine = inLine.split(",");
        numCols = processedLine.length;
        if(numCols != inFileContents[0].length)
        {
            setInFileContentsColumnSize(numCols);
        }//ENDIF
        addLineContent(processedLine);
    }//END processLine
    
    private void addLineContent(String [] inProcessedLine)
    {
        for(int ii=0;ii<inFileContents[0].length;ii++)
        {
            inFileContents[indexesFilled][ii] = inProcessedLine[ii];
        }//END FOR
        incrementIndexesFilled();
    }//END addLineContent
    
}//END class ReadInCSV
