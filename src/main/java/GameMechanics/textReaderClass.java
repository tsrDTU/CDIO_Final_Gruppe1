package GameMechanics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class textReaderClass {
//-------------------------------------------------------------------------------
//
//      This reads text files and returns lines of text as Strings
//
//-------------------------------------------------------------------------------
    public static String textRDR(File file, String LineNR) throws FileNotFoundException {
        Scanner TXTRDRscanner = new Scanner(file);
        int ReadLineNR = Integer.parseInt(LineNR);

        for (int i = 0; i <= ReadLineNR; i++) {
            if (i == ReadLineNR - 1) {
                //
                return TXTRDRscanner.nextLine();
            } else TXTRDRscanner.nextLine();
        }
        //  Closes the scanner after use
        TXTRDRscanner.close();
        TXTRDRscanner = new Scanner(file);
        return LineNR;
    }
}
