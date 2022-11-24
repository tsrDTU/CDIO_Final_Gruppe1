package cardClasses;

/**
 * Denne type chancekort overdrages til en anden spiller af den som trak det. Den anden spiller skal følge instruktionerne når det bliver denne spillers tur
 */
public class ChanceOverdragelseskort extends Chancekort
{
    private String modtager;
    private boolean aktivt;

    @Override
    public String toString() {
        return "ChanceOverdragelseskort{" +
                "modtager='" + modtager + '\'' +
                ", aktivt=" + aktivt +
                '}';
    }

    public ChanceOverdragelseskort(String nvn, String inf, String modt)
    {
        super(nvn,inf);
        modtager=modt;
    }

    public String getModtager() {
        return modtager;
    }

    public void setModtager(String modt)
    {
        modtager=modt;
    }

    public boolean getAktivt() {
        return aktivt;
    }

    public void setAktivt(boolean akt)
    {
        aktivt=akt;
    }


}
