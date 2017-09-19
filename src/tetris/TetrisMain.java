package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TetrisMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel scoreLabel, hi_scoreLabel;
	private Board board;
	private static String str_hiscore = "";
	
	private static final int WIDTH = 305;
	private static final int HEIGHT = 500;
	

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {

			TetrisMain game = new TetrisMain();
			game.setVisible(true);
		});
	}

	public TetrisMain() {

		initUI();
	}

	private JFrame getInstance() {
		return this;
	}

	private void initUI() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addWindowListener(new SaveAndRestoreConfig());
		setResizable(false);
		setTitle("Tetris");

		scoreLabel = new JLabel();
		hi_scoreLabel = new JLabel("0000000");

		JPanel scorePane = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 1));
		scorePane.add(new JLabel("Score"));
		scorePane.add(scoreLabel);
		scorePane.add(new JLabel("Hi-Score"));
		scorePane.add(hi_scoreLabel);

		board = new Board(this);
		board.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.black));

		JLabel helpLabel = new JLabel("<html><a href='#'>Help</a></html>");
		helpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		helpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helpLabel.setAlignmentX(CENTER_ALIGNMENT);
		helpLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				board.pause();
				new GameWizard(getInstance(), true).setVisible(true);
				board.pause();
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 1));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(55, HEIGHT));
		panel.add(scorePane);
		panel.add(Box.createVerticalGlue());
		panel.add(helpLabel);

		add(board, BorderLayout.CENTER);
		add(panel, BorderLayout.EAST);
		board.start();

		pack();
		setLocationRelativeTo(null);

	}

	public JLabel getScoreLabel() {

		return scoreLabel;
	}

	public void checkHiScore(String score) {
		if (scoreLabel.getText().compareTo(hi_scoreLabel.getText()) > 0)
			hi_scoreLabel.setText(scoreLabel.getText());
	}
	
	class SaveAndRestoreConfig extends WindowAdapter{
		
		@Override
		public void windowOpened(WindowEvent e) {
			super.windowOpened(e);
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			super.windowClosing(e);
		}
	}
}