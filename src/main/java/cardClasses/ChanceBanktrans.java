package cardClasses;

public class ChanceBanktrans extends Chancekort
{
     private int overfoersel;

    public ChanceBanktrans(String nvn,String inf,int tr)
    {
        super(nvn,inf);
        overfoersel=tr;
    }
}
