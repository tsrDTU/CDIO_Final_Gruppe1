package Files;

import java.io.File;

//  *This class sets up the path to txt files in the
public class FileReference {
//------------------------------------------------------------------------------------------
//      Field Descriptions
//------------------------------------------------------------------------------------------

    public static File DescriptionF = new File("src/main/Field Guts/description");
    //public static boolean DescriptionF = new File("src/main/Field Guts/description").getParentFile().mkdirs();
    /*
    public static File DescriptionFP() {
        DescriptionF.getParentFile().mkdirs();
        DescriptionF = new File(String.valueOf(DescriptionF.getParentFile().mkdirs()+String.valueOf(DescriptionF)));
        return DescriptionF;
    }
    */
    public static File TitleF = new File("src/main/Field Guts/Title");
    /*
    public static File TitleFP() {
        TitleF.getParentFile().mkdirs();
        TitleF = new File(String.valueOf(TitleF.getParentFile().mkdirs()));
        return TitleF;
    }
    */
    public static File subtextF = new File("src/main/Field Guts/subText");
    /*
    public static File subtextFP() {
        subtextF.getParentFile().mkdirs();
        subtextF = new File(String.valueOf(subtextF.getParentFile().mkdirs()));
        return subtextF;
    }
    */
    public static File rentF = new File("src/main/Field Guts/rent");
    /*
    public static File rentFP() {
        rentF.getParentFile().mkdirs();
        rentF = new File(String.valueOf(rentF.getParentFile().mkdirs()));
        return rentF;
    }
    */
    public static File CostToOwnFieldF = new File("src/main/Field Guts/CostToOwnField");
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
