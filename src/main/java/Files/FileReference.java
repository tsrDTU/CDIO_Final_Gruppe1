package Files;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Objects;

//  *This class sets up the path to txt files in the Language and BoardCreator classes
public class FileReference {
//public static String TitleF = new File("src/main/Field Guts/Title");
    //public static File DescriptionF = new File("src/main/Field Guts/description");
//----------------------------------------------------------------------------------------
//
//                              Fields Definitions
//
//----------------------------------------------------------------------------------------
    public static File DescriptionF = new File("src/main/Field_Guts/description");
    //public static File TitleF = new File("src/main/Field_Guts/Title");
    public static File TitleF = new File("src/main/Field_Guts/Title");
    public static File subtextF = new File("src/main/Field_Guts/subText");
    public static File rentF = new File("src/main/Field_Guts/CostToOwnField");
    public static final String CostToOwnFieldSt = new File("src/main/Field_Guts/CostToOwnField").getPath();
    public static File CostToOwnFieldF = new File(CostToOwnFieldSt);
//------------------------------------------------------------------------------
//
//                                  Language
//
//------------------------------------------------------------------------------
    public static File Dansk = new File("src/main/Language/Dansk");
    public static File Francias = new File("src/main/Language/French");
    public static File German = new File("src/main/Language/German");
    public static File English = new File("src/main/Language/English");
}
