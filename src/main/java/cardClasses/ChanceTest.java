package cardClasses;

import static org.junit.jupiter.api.Assertions.*;

class ChanceTest {

    public static void main(String[] args) {
        System.out.println("main startet");
        Chance mjChance=new Chance();
        Chancekort kortTrukket =mjChance.traekEtChanceKort();
        System.out.println(kortTrukket.getClass());
        System.out.println(kortTrukket.getKortNavnavn());
        System.out.println(kortTrukket.getKortInfo());
    }




}