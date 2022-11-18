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
    public static File DescriptionF = new File("src/main/Field Guts/description");
    public static File TitleF = new File("src/main/Field Guts/Title");

    public static File subtextF = new File("src/main/Field Guts/subText");

    public static File rentF = new File("src/main/Field Guts/rent");

    public static final String CostToOwnFieldSt = new File("src/main/Field Guts/CostToOwnField").getPath();
    public static File CostToOwnFieldF = new File(CostToOwnFieldSt);


//------------------------------------------------------------------------------------------
//      Languages for
//------------------------------------------------------------------------------------------

}
