package zhrfrd.terranova;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private JPanel panelMain = new JPanel();
    private JButton buttonStart = new JButton("Start!");
    private Font font;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = SCREEN_WIDTH / 3 * 2; // ASPECT RATIO 16:9

    /**
     * Create a MainFrame object that contains all the graphical objects of the game.
     */
    public MainFrame() {
        initializeLayout();
    }

    /**
     * Create the layout of the window by positioning all the main components to the MainFrame.
     */
    private void initializeLayout() {
        panelMain = new JPanel();
	    font = panelMain.getFont().deriveFont(50);

	    panelMain.setFont(font);
        panelMain.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
	    panelMain.setFocusable(true);
        panelMain.add(buttonStart);

        buttonStart.addActionListener(e -> startGame());

        add(panelMain);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Start the game.
     */
    private void startGame() {
        System.out.println("START!");
    }
}
