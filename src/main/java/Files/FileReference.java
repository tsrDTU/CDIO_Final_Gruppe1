package Files;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Objects;

//  *This class sets up the path to txt files in the
public class FileReference {
//------------------------------------------------------------------------------------------
//      Field Descriptions
//------------------------------------------------------------------------------------------
//public static String TitleF = new File("src/main/Field Guts/Title");
    //public static File DescriptionF = new File("src/main/Field Guts/description");
    public static final String DescriptionSt = new File("src/main/Field Guts/description").getAbsolutePath();
    public static File DescriptionF = new File(DescriptionSt);


    public static final String TitleSt = new File("src/main/Field Guts/Title").getAbsolutePath();
    public static File TitleF = new File(TitleSt);



    //private static final InputStream subtextI = this.getClass().getResourceAsStream("src/main/Field Guts/subtext");
    //public static InputStream subtextF = FileReference.class.getResourceAsStream("src/main/Field Guts/subText");
    public static final String subtextSt = new File("src/main/Field Guts/subText").getAbsolutePath();
    public static File subtextF = new File(subtextSt);

    public static final String rentSt = new File("src/main/Field Guts/rent").getAbsolutePath();
    public static File rentF = new File(rentSt);

    public static final String CostToOwnFieldSt = new File("src/main/Field Guts/CostToOwnField").getAbsolutePath();
    public static File CostToOwnFieldF = new File(CostToOwnFieldSt);


    /*
    public static File CostToOwnFieldFP() {
        CostToOwnFieldF = new File(String.valueOf(CostToOwnFieldF.getParentFile().mkdirs()));
        return CostToOwnFieldF;
    }
    */

//------------------------------------------------------------------------------------------
//      Languages for
//------------------------------------------------------------------------------------------

}
