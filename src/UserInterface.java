import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
public class UserInterface {
    static JFrame window;

    /**
     * 0 = start
     * 1 = spiel
     * 2 = ende
     */
    public static int state = 0;

    // int game = 1;
    private static JPanel gameScreen;
    private static JPanel startScreen;
    private static JPanel endScreen;

    public UserInterface() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setTitle("VierGewinnt");
        window.setResizable(false);
    }

    private static void initializeGameScreen() {

        addHomeButton();

        int labelHeight = 50;
        int buttonHeight = 50;
        int chipWidth = Board.chipDiameter;

        for (int column = 0; column < Game.COLUMNS; column++) {
            JButton button = new JButton("\u25CF");
            int yPosition = 5 + labelHeight + 10;
            button.setBounds((column * chipWidth) + (column * Board.spacing) + 211, yPosition + 50, chipWidth + 3, buttonHeight + 1);
            int targetColumn = column;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game.playChip(targetColumn);
                }
            });

            button.setVisible(true);
            gameScreen.add(button);
        }
        addNewGameButton(gameScreen);

        Board board = new Board();
        board.setBounds(0, 0, 800, 600);
        gameScreen.add(board);
    }

    public static void initializeStartScreen() {
        Startseite startseite = new Startseite();
        startseite.setBounds(0, 0, 800, 600);
        startScreen.add(startseite);
    }

    private static void initializeStartScreenButton() {
        JButton startbutton = new JButton("START");
        startbutton.setBounds(270, 170, 250, 150);
        startbutton.setForeground(Color.RED);

        startbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.startNewGame(); // TODO Verbesserung
                state = 1;
                updateUI();
            }
        });
        startScreen.add(startbutton);
    }

    private static void addNewGameButton(JPanel screen) {
        JButton newGameButton = new JButton("\u21BA");
        newGameButton.setBounds(680, 60, 100, 50);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUI();
                Game.startNewGame();
            }
        });
        newGameButton.setVisible(true);
        screen.add(newGameButton);
    }

    private static void addHomeButton() {
        JButton HomeButton = new JButton("HOME");
        HomeButton.setBounds(20, 60, 100, 50);
        HomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = 0;
                updateUI();

            }
        });
        HomeButton.setVisible(true);
        gameScreen.add(HomeButton);
    }
   //
    // private static void initializeEndScreen() {
     //   JButton homebutton = new JButton("HOME");
       // homebutton.setBounds(300, 400, 200, 100);

        //homebutton.addActionListener(new ActionListener() {
          //  @Override
            //public void actionPerformed(ActionEvent e) {
              //  state = 0;
                //updateUI();
           // }
        //});

       // endScreen.add(homebutton);
    //}


    public static void setWinner(int winner) {

        JButton weiterbutton = new JButton("WEITER");
        weiterbutton.setBounds(600, 500, 100, 50);

        weiterbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = 0;
                updateUI();
            }
        });

        gameScreen.add(weiterbutton, 0);
        // weiter button
        // gamescreen hinzufuegen
    }

    public static void updateUI() {
        window.getContentPane().removeAll();
        if (state == 0) {
            // new startScreen
            startScreen = new JPanel();
            startScreen.setLayout(null);
            // initialize StartScreen
            initializeStartScreenButton();
            // window add startScreen
            window.add(startScreen);
            initialize();
        } else if (state == 1) {
            gameScreen = new JPanel();
            gameScreen.setLayout(null);
            initializeGameScreen();
            window.add(gameScreen);
        //} else if (state == 2) {
        //    endScreen = new JPanel();
        //    endScreen.setLayout(null);
            // window.add(endScreen);
            //initializeEndScreen();
        }

        window.setVisible(true);

    }
    public static void initialize() {
        initializeStartScreen();
        initializeStartScreenButton();
        startScreen.setVisible(true);
    }
}