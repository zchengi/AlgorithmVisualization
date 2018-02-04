package three.montyHallProblem;

/**
 * 三门问题
 *
 * @author cheng
 *         2018/2/4 22:27
 */
public class ThreeGatesExperiment {

    private int n;

    public ThreeGatesExperiment(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be large than 0");
        }
        this.n = n;
    }

    public void run(boolean changeDoor) {
        int wins = 0;

        for (int i = 0; i < n; i++) {
            if (play(changeDoor)) wins++;
        }
        System.out.println(changeDoor ? "Change" : "Not Change");
        System.out.println("winning rate: " + (double) wins / n);
    }

    private boolean play(boolean changeDoor) {

        //  Door 0, 1, 2
        int prizeDoor = (int) (Math.random() * 3);
        int playerChoice = (int) (Math.random() * 3);

        if (playerChoice == prizeDoor) return !changeDoor;
        else return changeDoor;
    }

    public static void main(String[] args) {

        int n = 10000000;
        ThreeGatesExperiment experiment = new ThreeGatesExperiment(n);
        experiment.run(true);
        System.out.println();
        experiment.run(false);
    }
}
