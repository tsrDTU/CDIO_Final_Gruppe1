package cardClasses;
import EgneGuiKlasser.MGUI;
import GameMechanics.Fields;
import gui_main.GUI;

import gui_fields.*;

import java.util.Objects;
import EgneGuiKlasser.*;


public  class Chance {
    private static Chancekort[] chanceCards=new Chancekort[47];
    int testKortMode=0;

    /**
     * Initialisering af chancekort
     */

    public Chance()
    {
        //System.out.println("Chance initialiseres");



        chanceCards[0]=new ChanceBetalbankenHusHotel("Oliepriserne","Oliepriserne er steget, og De skal betale kr 500 pr hus og kr 2000 pr hotel", 500, 2000);
        chanceCards[1]=new ChanceBetalbankenHusHotel("Ejendomsskat","Ejendomsskatten er steget. Ekstraudgifterne er: 800 kr pr hus, 2300 kr pr hotel.",800,2300);
        chanceCards[2]=new ChanceBetalBanken("Fuldt stop", "De har kørt frem for “fuldt stop”, Betal 1000 kroner i bøde", 1000);
        chanceCards[3]=new ChanceBetalBanken("Vognvask og smøring", "Betal for vognvask og smøring kr 300", 300);
        chanceCards[4]=new ChanceBetalBanken("2 kasser øl","Betal kr 200 for levering af 2 kasser øl", 200);
        chanceCards[5]=new ChanceBetalBanken("Reparation1 af deres vogn", "Betal 3000 for reparation af deres vogn", 3000);
        chanceCards[6]=new ChanceBetalBanken("Reparation2 af deres vogn", "Betal 3000 for reparation af deres vogn", 3000);
        chanceCards[7]=new ChanceBetalBanken("4 nye dæk", "De har købt 4 nye dæk til Deres vogn, betal kr 1000", 1000);
        chanceCards[8]=new ChanceBetalBanken("Parkeringsbøde", "De har fået en parkeringsbøde, betal kr 200 i bøde", 200);
        chanceCards[9]=new ChanceBetalBanken("bilforsikring", "Betal deres bilforsikring, kr 1000", 1000);
        chanceCards[10]=new ChanceBetalBanken("Smøger", "De har været udenlands og købt for mange smøger, betal kr 200 i told.", 200);
        chanceCards[11]=new ChanceBetalBanken("Tandlægeregning", "Tandlægeregning, betal kr 2000.", 2000);
        chanceCards[12]=new ChanceModtagFraBanken("klasselotteriet1", "De har vundet i klasselotteriet. Modtag 500 kr.", 500);
        chanceCards[13]=new ChanceModtagFraBanken("klasselotteriet2", "De har vundet i klasselotteriet. Modtag 500 kr.", 500);
        chanceCards[14]=new ChanceModtagFraBanken("Aktieudbytte1", "De modtager Deres aktieudbytte. Modtag kr 1000 af banken", 1000);
        chanceCards[15]=new ChanceModtagFraBanken("Aktieudbytte2", "De modtager Deres aktieudbytte. Modtag kr 1000 af banken", 1000);
        chanceCards[16]=new ChanceModtagFraBanken("Aktieudbytte3", "De modtager Deres aktieudbytte. Modtag kr 1000 af banken", 1000);
        chanceCards[17]=new ChanceModtagFraBanken("Eftergivet skat", "Kommunen har eftergivet et kvartals skat. Hæv i banken 3000 kr.", 3000);
        chanceCards[18]=new ChanceModtagFraBanken("Elleve rigtige", "De have en række med elleve rigtige i tipning, modtag kl 1000", 1000);
        chanceCards[19]=new ChanceModtagFraBanken("Gageforhøjelse", "Grundet dyrtiden har De fået gageforhøjelse, modtag kr 1000.", 1000);
        chanceCards[20]=new ChanceModtagFraBanken("Præmieobligation1", "Deres præmieobligation er udtrykket. De modtager 1000 kr af banken.", 1000);
        chanceCards[21]=new ChanceModtagFraBanken("Præmieobligation2", "Deres præmieobligation er udtrykket. De modtager 1000 kr af banken.", 1000);
        chanceCards[22]=new ChanceModtagFraBanken("Gamle møbler", "De har solg nogle gamle møbler på auktion. Modtag 1000 kr af banken.", 1000);
        chanceCards[23]=new ChanceModtagFraBanken("Nyttehaven", "Værdien af egen avl fra nyttehaven udgør 200 som de modtager af banken", 200);
        chanceCards[24]=new ChanceMatadorLegat("Matador-legatet", "De modtager “Matador-legatet” på kr 40.000, men kun hvis værdier ikke overstiger 15.000 kr");
        chanceCards[25]=new ChanceModtagFraHverSpiller("Fødselsdag", "Det er deres fødselsdag. Modtag af hver medspiller 200 kr.", 200);
        chanceCards[26]=new ChanceModtagFraHverSpiller("Sammenskudsgilde", "De har lagt penge ud til et sammenskudsgilde. Mærkværdigvis betaler alle straks. Modtag fra hver medspiller 500 kr.", 500);
        chanceCards[27]=new ChanceModtagFraHverSpiller("Familiefest", "De skal holde familiefest og får et tilskud fra hver medspiller på 500 kr.", 500);
        chanceCards[28]=new ChanceRykFremTilFelt("START1", "Ryk frem til START", 0);
        chanceCards[29]=new ChanceRykFremTilFelt("START2", "Ryk frem til START", 0);
        chanceCards[30]=new ChanceRykFelter("Tre frem", "Ryk tre felter frem", 3);
        chanceCards[31]=new ChanceRykFelter("Tre tilbage 1", "Ryk tre felter tilbage", -3);
        chanceCards[32]=new ChanceRykFelter("Tre tilbage 2", "Ryk tre felter tilbage", -3);
        chanceCards[33]=new ChanceRykFremTilFelt("Frederiksberg Allé", "Ryk frem til Frederiksberg Allé. Hvis De passere START, indkasser da 4000 kr.", 11);
        chanceCards[34]=new ChanceRykNaermRederi("Nærmeste rederi 1", "Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De købe det af banken.", 2);
        chanceCards[35]=new ChanceRykNaermRederi("Nærmeste rederi 2", "Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De købe det af banken.", 2);
        chanceCards[36]=new ChanceRykFremTilFelt("Mols-Linien", "Tag med Mols-Linien, flyt brikken frem og hvis De passerer START indkassér da kr 4000.", 15);
        chanceCards[37]=new ChanceRykFremTilFelt("Grønningen", "Ryk frem til Grønningen, hvis De passerer start indkasser da kr 4000", 24);
        chanceCards[38]=new ChanceRykFremTilFelt("Vimmelskaftet", "Ryk frem til Vimmelskaftet, hvis de passerer start indkasser da kr 4000", 32);
        chanceCards[39]=new ChanceRykNaermRederi("Nærmeste færge", "Tag med den nærmeste færge, hvis de passerer start indkasser da kr 4000", 0);
        chanceCards[40]=new ChanceRykFremTilFelt("Strandvejen", "Ryk frem til Strandvejen. Hvis De passere START, indkasser da 4000 kr.", 19);
        chanceCards[41]=new ChanceRykFremTilFelt("Rådhuspladsen", "Tag til Rådhuspladsen", 39);
        chanceCards[42]=new ChanceAmnistiFeng("kongens fødselsdag 1","I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det.");
        chanceCards[43]=new ChanceAmnistiFeng("kongens fødselsdag 2","I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det.");
        chanceCards[44]=new ChanceGaaIFaengsel("Fængsel", "Gå i fængsel, De indkasserer ikke 4000 kr for at passere start.");
        chanceCards[45]=new ChanceGaaIFaengsel("Fængsel", "Gå i fængsel, De indkasserer ikke 4000 kr for at passere start.");
        chanceCards[46]=new ChanceTrump("Trump chancekort", "Du har trukket Trump chancekortet og får derved ret til låne en million", 1000000);







    }

    /**
     * Træk et chancekort.
     * @return
     */
    public Chancekort traekEtChanceKort()
    {
        int kort_nr;
        int  kOk=0;

        do {
            kOk=0;

            if (testKortMode==0) {
                kort_nr = (int) (Math.random() * 47);
                //System.out.println("Normalt kort trukket");
            }
            else
            {
                //Et specifikt chancekort skal trækkes for teste dette
                kort_nr=testKortMode;
                testKortMode=0;
                //System.out.println("Testkort trukket");
            }
            //System.out.println("Chancekort nr: "+kort_nr+" trukket");

            if (kort_nr==42 || kort_nr == 43 )
            {
                 if (chanceCards[kort_nr] instanceof ChanceAmnistiFeng)
                {
                    // Hvis kortet allerede er trukket og en anden spiller har det, skal dette træk ignoreres og et nyt kort skal trækkes
                    if (((ChanceAmnistiFeng) chanceCards[kort_nr]).getAktivt()) kOk=11;
                }
                else kOk=0;
            }



        } while (kOk > 0);



        return chanceCards[kort_nr];



    }

    /**
     * Spilleren har lige slået og ramt et chancefelt. Et chancekort skal trækkes
     * @param actPlayer: Spillr som har slået.
     * @param players: Spillerliste
     * @param actField: Spillerens position på brættet
     * @param AmountofPlayers: Antallet af spillere
     * @param AmountofSpaces
     * @param gui: GUI
     * @param fields: Liste over felter
     * @return: Bilens position på brættet efter at chancekort er håndteret. det kan samme position som eller en anden.
     */
    public int chanceFieldIsHit(MGUI_Player actPlayer, MGUI_Player[] players, int actField,
                                int AmountofPlayers, int AmountofSpaces, MGUI gui, MGUI_Street[] fields)
    {
        int i, j, bilPos;
        int slut=0;
        String farvCod, modtRolle, valg;
        MGUI_Player playModt;

        bilPos=actField;
        //System.out.println("bilPos "+bilPos);

        do {

            slut=0;

            //System.out.println("Chancekort trækkes");
            Chancekort actKort = traekEtChanceKort();

            gui.showMessage(actKort.getKortInfo());

            if (actKort instanceof Chance2Farver==true)
            {
                if (((Chance2Farver) actKort).getFarve1()== 3 && ((Chance2Farver) actKort).getFarve2()==8)
                   valg = gui.getUserButtonPressed("Vælg om du vil rykke til", "Orange felt", "Grønt felt");
                else if (((Chance2Farver) actKort).getFarve1()==4)
                    valg = gui.getUserButtonPressed("Vælg om du vil rykke til", "Pink felt", "Mørkeblåt felt");
                else if (((Chance2Farver) actKort).getFarve1()==5)
                   valg = gui.getUserButtonPressed("Vælg om du vil rykke til", "Lyseblå felt", "Rødt felt");
                else //if (((Chance2Farver) actKort).getFarve1()==9)
                   valg = gui.getUserButtonPressed("Vælg om du vil rykke til", "Brunt felt", "Gult felt");


                if (valg.equals("Orange felt") || valg.equals ("Pink felt") || valg.equals("Lyseblå felt")|| valg.equals("Brunt felt"))
                {
              //      farvCod=(""+((Chance2Farver) actKort).getFarve1());
                    farvCod=String.valueOf(((Chance2Farver) actKort).getFarve1());
       //             System.out.println("Farve 1 valgt kode "+farvCod);
                }
                else if (valg.equals("Grønt felt") || valg.equals ("Mørkeblåt felt") || valg.equals("Rødt felt")|| valg.equals("Gult felt"))
                {
               //     farvCod=(""+((Chance2Farver) actKort).getFarve2());
                    farvCod=String.valueOf(((Chance2Farver) actKort).getFarve2());
      //              System.out.println("Farve 2 valgt");
                }
                else farvCod="Ingen farve";
                i=bilPos;
                j=0;
  //              System.out.println("Brugt farve kode "+farvCod);
                do
                {
                    i++;
                    j++;
                    if (i>23) i=0;
      //              System.out.println(Base.fields[i].getDescription());

                }while( fields[i].getDescription().equals(farvCod)==false && j < 25);

                bilPos=i;


            }

            if (actKort instanceof ChanceOverdragelseskort==true)
            {
      //          gui.showMessage(actKort.getKortInfo());
     //           actPlayer.setKortModtaget(true);
      //          actPlayer.setActChancekort(actKort);
                modtRolle=((ChanceOverdragelseskort) actKort).getModtager();
      //          System.out.println("Chanceoverdragelse. Modtager: "+modtRolle+" Amount of players: "+AmountofPlayers);
                for (i=0;i<AmountofPlayers;i++)
     //               for (i=0;i<Base.AmountofPlayers;i++)
     //           for (i=0;i<4;i++)
                {
      //              System.out.println("Test player "+players[i].getUserRole());
                    if (players[i].getUserRole().equals(((ChanceOverdragelseskort) actKort).getModtager()))
                    {
                        players[i].setActChancekort((ChanceOverdragelseskort) actKort);
                        players[i].setKortModtaget(true);
                        System.out.println("Chancekort er overdraget til spiller nr. "+i+". Role: "+ players[i].getUserRole());
                    }
                    /*
                    else
                    {
                        System.out.println("Modtager af overdragelseskort er ikke fundet. Ingen spillere har rollen "+((ChanceOverdragelseskort) actKort).getModtager());
                        //System.out.println("Modtager af overdragelseskort er ikke fundet. Ingen spillere har rollen"+((ChanceOverdragelseskort) actKort).getModtager());
                    }

                     */
                }
                slut=11;
                //System.out.println("ChanceOverdragelseskort eksekveret");
            }


            if (actKort instanceof ChanceAmnistiFeng==true) {

                actPlayer.setAmnistkortHaves(true);
                ((ChanceAmnistiFeng) actKort).setAktivt(true);
                ((ChanceAmnistiFeng) actKort).setIndehaver(actPlayer.getNumber());
                //System.out.println("ChanceAmnistiFeng");
            }


            if (actKort instanceof ChanceBanktrans==true)
            {
                //  Betal 2 til banken.
                if (Objects.equals(actKort.getKortNavnavn(),chanceCards[6].getKortNavnavn()))
                    actPlayer.setBalance(actPlayer.getBalance()-2);
                //  Alle Betaler til 1 person.
                else if (Objects.equals(actKort.getKortNavnavn(),chanceCards[13].getKortNavnavn())){
                    for (int k = 0; k < AmountofPlayers; k++)
                        if (players[k].getUserRole()==actPlayer.getUserRole()) // Hvis spilleren er den som har kortet
                            players[k].setBalance(players[k].getBalance()+AmountofPlayers-1);
                        else players[k].setBalance(players[k].getBalance()-1);//    hvis spiller ikke har kortet
                    }
                //  Få 2 fra banken.
                else if (Objects.equals(actKort.getKortNavnavn(),chanceCards[15].getKortNavnavn())){
                    actPlayer.setBalance(actPlayer.getBalance()+2);
                    //System.out.println("+2 has been registered");
                }
            }

            if (actKort instanceof ChanceRyk05==true)
            {
                bilPos+=5;
            }

            if (actKort instanceof ChanceKortFarve==true)
            {

                //System.out.println("ChanceKortFarve");

                farvCod=(""+((ChanceKortFarve) actKort).getFarvekode());
                i=bilPos;
                j=0;
                do
                {
                    i++;
                    j++;
                    if (i>23) i=0;

                }while( fields[i].getDescription().equals(farvCod)==false && j < 25);


                        System.out.println(fields[bilPos+1].getDescription());

                bilPos=i;
            }

           if (actKort instanceof ChanceRykFremTilFelt==true) {


                bilPos = ((ChanceRykFremTilFelt) actKort).getDestinationsFelt();
                //System.out.println("ChanceRykFremTilFelt. bilPos "+ bilPos);

            }
            if (actKort instanceof ChanceRyk1ElChMemere==true) {
                valg = gui.getUserButtonPressed("Vælg", "En chance mere", "Ryk et felt frem");
                if (valg.equals("En chance mere")) {
                   slut=11;
                } else {
                    bilPos = actField + 1;
                }
                //System.out.println("ChanceRyk1ElChMemere");


            }

            if (actKort instanceof ChanceModtagFraBanken)
            {
                actPlayer.setBalance(actPlayer.getBalance() + ((ChanceModtagFraBanken) actKort).getBeloeb());
                System.out.println("Chancekort: ChanceModtagFraBanken");
            }

            if (actKort instanceof ChanceBetalBanken)
            {
                actPlayer.setBalance(actPlayer.getBalance() - ((ChanceBetalBanken) actKort).getBeloeb());
                System.out.println("Chancekort: ChanceBetalBanken");
            }

            if (actKort instanceof ChanceModtagFraHverSpiller)
            {
                actPlayer.setBalance(actPlayer.getBalance() + ((ChanceModtagFraHverSpiller) actKort).getBeloeb());
                for (int k = 0; k < AmountofPlayers; k++)
                {
                    if (actPlayer != players[k])
                    {
                        players[k].setBalance(players[k].getBalance() - ((ChanceModtagFraHverSpiller) actKort).getBeloeb());
                    }
                }
            }

            if (actKort instanceof ChanceGaaIFaengsel)
            {
                // Felt 10 er fængslet. Når dette felt returneres håndterer Fileds at sætte brugeren i fængsel.
               bilPos=10;
            }

            if (actKort instanceof  ChanceMatadorLegat)
            {
                if (actPlayer.getBalance() <= 15000) actPlayer.setBalance(actPlayer.getBalance() + 40000);
            }
            if (actKort instanceof ChanceRykFelter)
            {
                bilPos=actField + ((ChanceRykFelter) actKort).getAntal_felter();
            }

            if (actKort instanceof ChanceRykNaermRederi)
            {

                System.out.println("ChanceRykNaermRederi");

                i=bilPos;
                j=0;
                do
                {
                    i++;
                    j++;
                    if (i>23) i=0;

                }while( fields[i].getTitle().equals("Mols-Linien")==false &&
                        fields[i].getTitle().equals("Gedser - Rostock")==false &&
                        fields[i].getTitle().equals("Rødby - Puttgarden")==false &&
                        fields[i].getTitle().equals("Helsingør - Helsingborg")==false
                        && j < 41);


                System.out.println(fields[i].getTitle());

               if (fields[i].getOwnerName()== null)
               {
                   valg = gui.getUserButtonPressed("Do you want to buy", "Yes", "No");
                   if (valg.equals("Yes"))
                   {
                       /*
                       String NewBalance = Fields.wannaBuyDoYou(OwnedtrueOwnedFalse,
                               selectedPlayer,
                               //wanttobuyYesNo,
                               PlayerArray,
                               CurrentSpaceForSelectedPlayer,
                               PlayerSpaceNRexcact,
                               JailOn, chankort, gui, fields, ownstatus, OwnerList);
                       selectedPlayer.setBalance(selectedPlayer.getBalance() + Integer.parseInt(NewBalance));
*/
                   } else
                   {

                   }
               }
                System.out.println("Owner name: "+fields[i].getOwnerName());

                bilPos=i;

            }


            if (actKort instanceof ChanceTrump)
            {
                actPlayer.setBalance(actPlayer.getBalance() + ((ChanceTrump) actKort).getBeloeb());
            }

        }while (slut > 1);
        //System.out.println("While slut");
        //System.out.println("bilPos "+bilPos);
        return bilPos;


    }



    /**
     * Sætter er specifikt chancekort, som skal trækkes næste gang. Bruges til testformål.
     * @param testNr: chancekortnummer
     */
    public void setTestKortMode(int testNr)
    {
        testKortMode=testNr;
    }


}


