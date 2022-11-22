package cardClasses;

/**
 * Chancekortet, som giver amnisti hvis som har det havner i fængsel
 */
public class ChanceAmnistiFeng extends Chancekort
{
    private boolean aktivt;
    private int indehaver;

    public ChanceAmnistiFeng(String nvn,String inf)
    {
        super(nvn,inf);
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
