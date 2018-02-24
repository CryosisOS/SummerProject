package general.maintenance;

public class Cleaner
{
    /**
     * SUBMODULE cleanFileExtension
     * @param inFilename (String)
     * @return outFilename (String)
     * DESCRIPTION: This method returns all the characters to the left of the "."
     */
    public static String cleanFileExtension(String inFilename)
    {
        String outFilename;
        String [] split = inFilename.split(".");
        outFilename = split[0];
        return outFilename;
    }//END cleanFileExtensions
}//END class Cleaner
