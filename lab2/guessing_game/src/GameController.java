import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GameController extends ExceptionHandler implements Runnable{
    private GuessGameModel guessGameModel;
    public static ArrayList<GameController> controller = new ArrayList<>();
    private BufferedReader in;
    private PrintWriter out;

    public GameController(){

    }

    @Override
    public void run() {

    }
}
