package cardClasses;

public class ChanceOverdragelseskort extends Chancekort
{
    private String modtager=new String();
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
    /*
    public String navn() {
        return navn();
    }

    public String info() {
        return info();

     */
}
