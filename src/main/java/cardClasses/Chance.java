package cardClasses;
import java.util.Arrays;


public  class Chance {
    public static Chancekort[] chanceCards=new Chancekort[20];


public Chance()
{

    chanceCards[0]=new ChanceOverdragelseskort("Til Bil","Bil: På din næste tur skal du drøne frem til et hvilket som helst ledigt felt og købe det. Hvis det ikke er nogen ledige felter skal du købe et fra en anden spiller!", "Bil");

    chanceCards[1]=new ChanceRykFremTilFelt("Start","Ryk frem til start.", 0);
    chanceCards[2]=new ChanceRyk05("Ryk05","Ryk op til 5 felter frem");
    chanceCards[3]=new ChanceKortFarve("Ryk orange","Gratis felt. Ryk frem til et orange felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.", 3);
    chanceCards[4]=new ChanceRyk1ElChMemere("Ryk 1 eller change mere","Ryk 1 felt frem eller tag et chancekort mere");
    chanceCards[5]=new ChanceOverdragelseskort("Til Skibet","Giv dette kort til skibet og tag et chancekort mere. Skib: På den næste skal du sejle frem til hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, skal du købe et af en anden spiller.","Skibet");
    chanceCards[6]=new ChanceBanktrans("For meger slik","Du har spist for meget slik. Betal M2 til banken.", -2);
    chanceCards[7]=new Chance2Farver("Vælg orange elle grønt felt","Gratis felt. Ryk frem til et orange eller grønt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.", 3, 8);
    chanceCards[8]=new ChanceKortFarve("Ryk lyseblåt","Gratis felt. Ryk frem til et lyseblåt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",5);
    chanceCards[9]=new ChanceAmnistiFeng("Amnesti","Du løslades uden omkostninger. Behold dette kort til du får brug for det.");
    chanceCards[10]=new ChanceRykFremTilFelt("ryk tilStrandpromenaden","Ryk frem til Strandpromenaden.", 23);
    chanceCards[11]=new ChanceOverdragelseskort("Til Katten","Giv dette kort til Katten og tag et chancekort mere. Kat: På den næste skal du sejle frem til hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, skal du købe et af en anden spiller.","Katten");
    chanceCards[12]=new ChanceOverdragelseskort("Til Hunden","Giv dette kort til Hunden og tag et chancekort mere. Hund: På den næste skal du sejle frem til hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, skal du købe et af en anden spiller.","Hunden");


}

}


