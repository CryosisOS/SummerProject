/*
 * Author: Nathan van der Velde
 * Date Created: 2017-12-14
 * Last Modified By: Nathan van der Velde
 * Date Last Modified: 2017-12-14
 * Description: This source code takes care of reading in the CSV file and separating it into
 *              a string array that will be a class field for this Object.
 */

package frontend.console.FileIO;

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
    
    private void incrementIndexesFilled()
    {
        indexesFilled++;
    }//END incrementIndexesFilled
    
    private void setNumFileLines(int inNumLines)
    {
        numFileLines = inNumLines;
    }//END setNumFileLines
    
    private int getNumFileLines()
    {
        return numFileLines;
    }//END getNumFileLines
    
    /**
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
            throw new IOException();
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
        int lineNum;
        String line;
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
            
            lineNum = 0;
            line = bufRdr.readLine();
            lineNum++;
            for(int ii=lineNum;ii<=getNumFileLines();ii++)
            {
                line = bufRdr.readLine();
                processLine(line);
            }//ENDFOR
        }//END TRY
        catch(IOException e)
        {
            throw new IOException();
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
        for(int ii=0;ii<=inFileContents[0].length;ii++)
        {
            inFileContents[indexesFilled][ii] = inProcessedLine[ii];
        }//END FOR
        incrementIndexesFilled();
    }//END addLineContent
    
}//END class ReadInCSV
