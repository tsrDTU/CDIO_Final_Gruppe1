package cardClasses;

/**
 * Pillerens bil rykkes et antal felter. Fremad hvis værdien af antal_felter er et positivt tal, tilbage hvis værdien af antal_felter er negativ
 */
public class ChanceRykFelter extends Chancekort
{
    private int antal_felter;

    ChanceRykFelter(String nvn, String inf, int ant_fel)
    {
        super(nvn, inf);
        antal_felter=ant_fel;
    }

    public int getAntal_felter()
    {
        return antal_felter;
    }
}
