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

	private static final int WIDTH = 300;
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
		hi_scoreLabel = new JLabel();

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

		JPanel sidePanel = new JPanel();
		sidePanel.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 1));
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setPreferredSize(new Dimension(50, HEIGHT));
		sidePanel.add(scorePane);
		sidePanel.add(Box.createVerticalGlue());
		sidePanel.add(helpLabel);

		add(board, BorderLayout.CENTER);
		add(sidePanel, BorderLayout.EAST);
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

	class SaveAndRestoreConfig extends WindowAdapter {

		@Override
		public void windowOpened(WindowEvent e) {
			super.windowOpened(e);
			String hiScore = ConfigManager.loadConfig("RECORD");
			hi_scoreLabel.setText(hiScore == null ? "0000000" : hiScore);
			repaint();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			super.windowClosing(e);
			ConfigManager.saveConfig("RECORD", hi_scoreLabel.getText());
		}
	}
}