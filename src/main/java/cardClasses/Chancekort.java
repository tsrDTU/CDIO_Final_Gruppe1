package cardClasses;

/**
 * Forældreklassen til chancekort. Er abstract
 */
public abstract class Chancekort
{
    private String kortNavn;
    private  String kortInfo;
    private String modtager=new String();
    private int action;
    private boolean aktivt;


    Chancekort(String navn, String info)
    {
        kortNavn=navn;
        kortInfo=info;

    }
    public String getKortNavnavn() {
        return kortNavn;
    }

    public String getKortInfo() {
        return kortInfo;
    }

}

