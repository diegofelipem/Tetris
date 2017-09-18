package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Tetris extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel statusbar;
	private Board board;

	public Tetris() {

		initUI();
	}

	private JFrame getInstance() {
		return this;
	}

	private void initUI() {

		statusbar = new JLabel("0");
		board = new Board(this);

		JPanel southPane = new JPanel(new BorderLayout(5, 5));
		southPane.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.black));
		southPane.add(statusbar, BorderLayout.WEST);

		JLabel helpLabel = new JLabel("<html><a href='#'>HELP</a></html>");
		helpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		helpLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				board.pause();
				new GameWizard(getInstance(), true).setVisible(true);
				board.pause();
			}
		});

		southPane.add(helpLabel, BorderLayout.EAST);
		add(southPane, BorderLayout.SOUTH);
		add(board, BorderLayout.CENTER);
		board.start();

		setResizable(false);
		setPreferredSize(new Dimension(250, 500));
		setTitle("Tetris");
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public JLabel getStatusBar() {

		return statusbar;
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {

			Tetris game = new Tetris();
			game.setVisible(true);
		});
	}
}