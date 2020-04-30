import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Game implements Serializable {


    private int myteam_score;
    private int opponent_score;
    private int[] myteam_elo;
    private int[] opponent_elo;
    private int mvp;
    private int current_rank;
    private boolean ranked_up;
    private int elo;

    public Game(int myteam_score, int opponent_score, int[] myteam_elo, int[] opponent_elo, int mvp, int current_rank, boolean ranked_up) {
        this.myteam_score = myteam_score;
        this.opponent_score = opponent_score;
        this.myteam_elo = myteam_elo;
        this.opponent_elo = opponent_elo;
        this.mvp = mvp;
        this.current_rank = current_rank;
        this.ranked_up = ranked_up;
        calculateElo();
    }

    public int getMyteam_score() {
        return myteam_score;
    }

    public void setMyteam_score(int myteam_score) {
        this.myteam_score = myteam_score;
    }

    public int getOpponent_score() {
        return opponent_score;
    }

    public void setOpponent_score(int opponent_score) {
        this.opponent_score = opponent_score;
    }

    public int[] getMyteam_elo() {
        return myteam_elo;
    }

    public void setMyteam_elo(int[] myteam_elo) {
        this.myteam_elo = myteam_elo;
    }

    public int[] getOpponent_elo() {
        return opponent_elo;
    }

    public void setOpponent_elo(int[] opponent_elo) {
        this.opponent_elo = opponent_elo;
    }

    public int getMvp() {
        return mvp;
    }

    public void setMvp(int mvp) {
        this.mvp = mvp;
    }

    public int getCurrent_rank() {
        return current_rank;
    }

    public void setCurrent_rank(int current_rank) {
        this.current_rank = current_rank;
    }

    public void calculateElo(){
        elo = ((5 * (myteam_score - opponent_score)) / 3) + teamsElo() + mvp;
    }

    public int teamsElo(){
        int myteam = 0;
        for (int i = 0; i<4; i++){
            myteam += myteam_elo[i];
        }
        myteam += current_rank;
        int opponent = 0;
        for (int i = 0; i<5; i++){
            opponent += opponent_elo[i];
        }
        return opponent - myteam;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public boolean isRanked_up() {
        return ranked_up;
    }

    public void setRanked_up(boolean ranked_up) {
        this.ranked_up = ranked_up;
    }

    @Override
    public String toString() {
        return  "------------------------------------\n" +
                "[YOUR TEAM] " + myteam_score + " - " + opponent_score + " [OPPONENT TEAM]\n" +
                "YOUR TEAM ELO: \n" + Arrays.toString(myteam_elo) + "\n" +
                "OPPONENT TEAM ELO: \n" + Arrays.toString(opponent_elo) + "\n" +
                "YOUR MVPs: " + mvp + "\n" +
                "YOUR RANK: " + current_rank + "\n" +
                "ELO: " + elo + "\n" +
                "------------------------------------\n";
    }
}
