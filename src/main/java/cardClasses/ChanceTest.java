package cardClasses;

import static org.junit.jupiter.api.Assertions.*;

class ChanceTest {

    public static void main(String[] args) {
        System.out.println("main startet");
        Chance mjChance=new Chance();
        Chancekort kortTrukket =mjChance.traekEtChanceKort();
        System.out.println(kortTrukket.getClass());
        if (kortTrukket.getClass().equals(ChanceBanktrans.class) )
        {
            System.out.println("Afsender: "+((ChanceBanktrans) kortTrukket).getAfsender());
            System.out.println("Modtager: "+((ChanceBanktrans) kortTrukket).getModtager());
            System.out.println("Beløb: "+((ChanceBanktrans) kortTrukket).getBeloeb());

        }
        if (kortTrukket.getClass().equals(ChanceAmnistiFeng.class))
        {
            System.out.println("Kort aktivt: "+ ((ChanceAmnistiFeng) kortTrukket).getAktivt()) ;
            System.out.println("Indehaver: "+((ChanceAmnistiFeng) kortTrukket).getIndehaver());
        }
        if (kortTrukket.getClass().equals(Chance2Farver.class))
        {
            System.out.println("Farve 1: "+((Chance2Farver) kortTrukket).getFarve1());
            System.out.println("Farve 2: "+((Chance2Farver) kortTrukket).getFarve2());
        }
        if (kortTrukket.getClass().equals(ChanceRykFremTilFelt.class))
        {
            System.out.println("Destination felt: "+ ((ChanceRykFremTilFelt) kortTrukket).getDestinationsFelt());
        }
        if (kortTrukket.getClass().equals(ChanceOverdragelseskort.class))
        {
            System.out.println("Modtager: "+((ChanceOverdragelseskort) kortTrukket).getModtager());
            System.out.println("Aktivt"+((ChanceOverdragelseskort) kortTrukket).getAktivt());
        }
        if (kortTrukket.getClass().equals(ChanceKortFarve.class))
        {
            System.out.println("Farve: "+ ((ChanceKortFarve) kortTrukket).getFarvekode());

        }

        System.out.println(kortTrukket.getKortNavnavn());
        System.out.println(kortTrukket.getKortInfo());
        System.out.println(kortTrukket);

    }




}