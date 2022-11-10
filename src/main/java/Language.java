import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Language {

    public Language() throws FileNotFoundException {
    }


    static void initializeDialog(String[] dialog, String sprog) throws IOException {
        String file = "src/main/Language/Dansk"; // file location
    if (sprog=="Dansk") {
        dialog[0] = "Der er forhåndsvalgt terninger med 6 kanter. Tast enter for at vælge dette. Ellers indtast det ønskede antal kanter  (2 - 5) og tast enter";
        dialog[1] = "Hvem er Spiller ";
        dialog[2] = "Hvem er Spiller 2?";
        dialog[3] = "Hvem starter spillet?";
        dialog[4] = "Det er";
        dialog[5] = " der har tur";
        dialog[6] = "Rul med terningerne";
        dialog[7] = " Du har fået en ekstra tur, fordi du ramte felt 8.";
        dialog[8] = " Har vundet med en score på:";
        dialog[9] = " Nyt spil?";
        dialog[10] = "Ja";
        dialog[11] = "Nej";


// søg på youtube: java reading txt files
            //dialog[12] = reader.readLine();
            //Path file = Paths.get("src/main/Language/Dansk");
            //BufferedReader reader = new BufferedReader(new FileReader(file));

            //  String expected_value = "Hello, world!";


            //String read = Files.readString(file).get(0);
               /* throw IOException {
                String expected_value = "Hello, world!";
                String read = Files.readAllLines(file).get(0);
                assertEquals(expected_value, read);
                }*/


        } else if (sprog.equals("Francias")) {
            dialog[0] = "Un dé à six faces est choisi par défaut. Veuillez appuyer sur Entrée pour le sélectionner. Ou entrez le nombre de faces (2 - 5) que vous souhaitez:";
            dialog[1] = "Qui est le joueur ";
            dialog[2] = "Qui est le joueur 2?";
            dialog[3] = "Qui commence le jeu?";
            dialog[4] = "Il est";
            dialog[5] = " El jouant";
            dialog[6] = "Veuillez lancer les dés";
            dialog[7] = "Vous avez un jet de dé supplémentaire, car vous avez touché la case 8.";
            dialog[8] = "a gagné avec le score";
            dialog[9] = "Un nouveau jeu?";
            dialog[10] = "Oui";
            dialog[11] = "Non";

        } else if (sprog.equals("English")) {
            dialog[0] = "A dice with six sides are default chosen. Please press enter to select this. Or enter the number of sides (2 - 5) you wish:";
            dialog[1] = "Who is Player ";
            dialog[2] = "Who is Player 2?";
            dialog[3] = "Who starts the game?";
            dialog[4] = "It is";
            dialog[5] = " playing";
            dialog[6] = "Please roll the dices";
            dialog[7] = " You have an additional dice roll, because you have hit field 8.";
            dialog[8] = " has won with the score:";
            dialog[9] = " A new game?";
            dialog[10] = "Yes";
            dialog[11] = "No";

        } else if (sprog.equals("German")) {
            dialog[0] = "Es gibt vorgewählte Würfel mit 6 Kanten. Drücken Sie die Eingabetaste, um dies auszuwählen. Geben Sie andernfalls die gewünschte Anzahl von Kanten (2 - 5) ein und drücken Sie die Eingabetaste";
            dialog[1] = "Wer ist Spieler ";
            dialog[2] = "Wer ist Spieler 2?";
            dialog[3] = "Wer startet das Spiel?";
            dialog[4] = "Es ist";
            dialog[5] = " spielen";
            dialog[6] = "Bitte würfeln";
            dialog[7] = " Du hast einen zusätzlichen Würfelwurf, weil du Feld 8 getroffen hast.";
            dialog[8] = " hat mit der Partitur gewonnen:";
            dialog[9] = " Ein neues Spiel?";
            dialog[10] = "Ja";
            dialog[11] = "Nein";


        }

    }

}

