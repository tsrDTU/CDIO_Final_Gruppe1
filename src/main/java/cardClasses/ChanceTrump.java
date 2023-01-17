package cardClasses;

public class ChanceTrump  extends Chancekort
{
    private int beloeb;
    private boolean aktivt;
    private int indehaver;

   public ChanceTrump(String nvn, String inf, int bel)
   {
       super(nvn, inf);
       beloeb=bel;
   }

   public int getBeloeb()
    {
        return beloeb;
    }


    public int getIndehaver() {
        return indehaver;
    }

    public boolean getAktivt() {
        return aktivt;
    }

    public void setAktivt(boolean akt)
    {
        aktivt=akt;
    }
    public void setIndehaver(int indeh)
    {
        indehaver=indeh;
    }
}
