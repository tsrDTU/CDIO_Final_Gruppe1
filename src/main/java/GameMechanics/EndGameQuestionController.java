package GameMechanics;

import TheBoard.Base;

import java.io.FileNotFoundException;

import static Files.FileReference.TitleF;
import static TheBoard.Base.AmountofPlayers;
import static TheBoard.Base.PlayerArray;
import static TheBoard.Language.dialog;

public class EndGameQuestionController {
    public static boolean AskEndQuestion(String answer_game,boolean game_running
            ,boolean answerGameOk,int[][] OwnedtrueOwnedFalse, int DialogNR, int[] PlayerSpaceNRexcact)
            throws FileNotFoundException {
        if (answer_game.equals(dialog[DialogNR+2])) {
            game_running = false;
            answerGameOk = true;
        }   //  else restart the game
        else {
            answerGameOk = true;
            GameMechanics.Cars.restart(PlayerArray, Base.fields, AmountofPlayers, Base.fieldNR());
            GameMechanics.Fields.RestartFieldTitles(/*fields*/TitleF, Base.fieldNR(), Base.fields);
            GameMechanics.Fields.RestartOwnStatus(OwnedtrueOwnedFalse, Base.fieldNR(), AmountofPlayers);
            for (int i = 0; i < AmountofPlayers; i++) {
                PlayerSpaceNRexcact[i] = 0;
            }
        }
        return true;
    }

}
