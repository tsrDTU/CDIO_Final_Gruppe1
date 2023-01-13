package cardClasses;

public class ChanceTrump  extends Chancekort
{
    private int beloeb;

   public ChanceTrump(String nvn, String inf, int bel)
   {
       super(nvn, inf);
       beloeb=bel;
   }

   public int getBeloeb()
    {
        return beloeb;
    }
}
