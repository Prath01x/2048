package ttfe;

import java.util.Random;

public class Computerp implements PlayerInterface {

    private Random paaji;

    public Computerp() {
        this.paaji = new Random();
    }

    @Override
    public MoveDirection getPlayerMove(SimulatorInterface game, UserInterface ui) {

        throw new UnsupportedOperationException("Unimplemented method 'getPlayerMove'");
    }


}
