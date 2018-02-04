package three.winningThePrize;

/**
 * 游戏宝箱中奖问题
 *
 * @author cheng
 *         2018/2/4 23:00
 */
public class WinningPrize {

    private double chance;
    private int playTime;
    private int n;

    public WinningPrize(double chance, int playTime, int n) {

        if (chance < 0.0 || chance > 1.0) {
            throw new IllegalArgumentException("chance must be between 0 and 1!");
        }
        if (playTime <= 0 || n <= 0) {
            throw new IllegalArgumentException("playTime or n mush be larger than 0!");
        }
        this.chance = chance;
        this.playTime = playTime;
        this.n = n;
    }

    public void run() {
        int wins = 0;
        for (int i = 0; i < n; i++) {
            if (play()) wins++;
        }
        System.out.println("winning rate: " + (double) wins / n);
    }

    private boolean play() {
        for (int i = 0; i < playTime; i++) {
            if (Math.random() < chance) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        double change = 0.2;
        int playTime = 10;
        int n = 10000000;

        WinningPrize exp = new WinningPrize(change, playTime, n);
        exp.run();

        /*
         * 中奖概率为 20% 的情况下，抽 n 次奖一定中奖的概率：
         *
         * 5次 66.72%
         * 6次 73.77%
         * 7次 79.01%
         * 8次 83.21%
         * 9次 86.56%
         * 10次 89.26%
         */
    }
}
