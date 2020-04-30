import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Game> gameList = new ArrayList<>();


    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException{

        System.out.println("Loading games...");
        loadGameList();
        for (Game g:
             gameList) {
            g.calculateElo();
        }
        int option = 0;
        do {
            System.out.println("(1) Show all games.");
            System.out.println("(2) Introduce game.");
            System.out.println("(3) Save & exit.");
            System.out.print("Select an option:  ");
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
            switch (option){
                case 1 :
                    showGames();
                    break;
                case 2:
                    Game game = createGame();
                    if(game != null)
                        gameList.add(game);
                    break;
                case 3:
                    try {
                        saveGameList();
                    }catch (IOException e){
                        System.err.println("An error has occurred when saving the game list. Error code: 101");
                    }
            }
        }while (option == 1 || option == 2);

    }

    public static Game createGame(){
        Scanner sc = new Scanner(System.in);
        int myteam_score;
        int opponent_score;
        int[] myteam_elo = new int[4];
        int[] opponent_elo = new int[5];
        int mvp;
        int current_rank;
        System.out.println("Introduce the final score. Write your team's and your opponent's rounds separated with a space. For example: 16 9.");
        myteam_score = sc.nextInt();
        opponent_score = sc.nextInt();
        System.out.println("Introduce your team elo separated by spaces (not including yours):");
        printEloList();
        for (int i = 0; i<4; i++){
            myteam_elo[i] = sc.nextInt();
        }
        System.out.println("Introduce the opponent team elo separated by spaces:");
        printEloList();
        for (int i = 0; i<5; i++){
            opponent_elo[i] = sc.nextInt();
        }
        System.out.println("Introduce your MVP's no.:");
        mvp = sc.nextInt();
        System.out.println("Were you ranked up? (Y/N)");
        sc.nextLine();
        String option = sc.nextLine();
        boolean ranked_up;
        ranked_up = option.equalsIgnoreCase("Y");
        System.out.println("Introduce your rank. \n" +
                "If you were ranked up/down, introduce the new rank.");
        current_rank = sc.nextInt();
        Game game = new Game(myteam_score, opponent_score, myteam_elo, opponent_elo, mvp, current_rank, ranked_up);
        System.out.println(game);
        System.out.println("Is this correct? (Y/N)");
        sc.nextLine();
        option = sc.nextLine();
        if (option.equalsIgnoreCase("Y")){
            System.out.println("Game saved correctly.");
            return game;
        }else{
            System.out.println("Try again. ");
            return null;
        }
    }

    private static void printEloList() {
        System.out.println("(1) S1");
        System.out.println("(2) S2");
        System.out.println("(3) S3");
        System.out.println("(4) S4");
        System.out.println("(5) SE");
        System.out.println("(6) SEM");
        System.out.println("(7) GN1");
        System.out.println("(8) GN2");
        System.out.println("(9) GN3");
        System.out.println("(10) GN4");
        System.out.println("(11) MG1");
        System.out.println("(12) MG2");
        System.out.println("(13) MGE");
        System.out.println("(14) DMG");
        System.out.println("(15) LE");
        System.out.println("(16) LEM");
        System.out.println("(17) SMFC");
        System.out.println("(18) GE");
        System.out.println("Example: 10 10 9 11");
    }

    private static void saveGameList() throws IOException {
        File file = new File("savegame.bin");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(gameList);
    }

    private static void loadGameList() throws IOException {
        try {
            File file = new File("savegame.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            gameList = (ArrayList<Game>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Warning: \"savegame.bin\" file not found.");
        } catch (ClassNotFoundException e) {
            System.err.println("No games were found.");
        }
    }

    private static void showGames(){
        for (Game g:
             gameList) {
            System.out.println(g);
        }
    }


}
