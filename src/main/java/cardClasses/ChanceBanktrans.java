package cardClasses;

/**
 * Banktransaktion
 * overførsel: Beløbet, som overføres. negativt, hvis det trækkes fra din konto. Positivt hvis penge ættes ind
 * modtager:0= spiller,1=banken
 * afsender:0=dig,1=banken,2=alle andre spillere
 */
public class ChanceBanktrans extends Chancekort
{
     private int beloeb, modtager, afsender;


    @Override
    public String toString() {
        return "ChanceBanktrans{" +
                "beloeb=" + beloeb +
                ", modtager=" + modtager +
                ", afsender=" + afsender +
                '}';
    }

    public ChanceBanktrans(String nvn, String inf, int bel, int mo, int af)
    {
        super(nvn,inf);
        beloeb=bel;
        modtager=mo;
        afsender=af;
    }

    public int getBeloeb() {
        return beloeb;
    }

    public int getModtager() {
        return modtager;
    }
    public int getAfsender()
    {
        return afsender;
    }


}
