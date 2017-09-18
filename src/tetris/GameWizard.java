package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class GameWizard extends JDialog {

	private static final long serialVersionUID = 1L;
	private final String HELPTEXT = "Commads:\r\n\r\n"
			+ "- \"N\" key - new game\r\n"
			+ "- \"P\" key - pause and run game\r\n"
			+ "- Up Arrow -  rotate right\r\n"
			+ "- Down Arrow - down one line piece\r\n"
			+ "- Left and Right Arrow - move piece to right/left\r\n"
			+ "- Space Bar - drop down piece\r\n";

	public GameWizard(Frame owner, boolean isModal) {
		super(owner, isModal);

		setPreferredSize(new Dimension(owner.getSize().width-10, 200));
		setUndecorated(true);
		setBackground(Color.WHITE);
		JTextPane infoPane = new JTextPane();
		infoPane.setEditable(false);
		infoPane.setText(HELPTEXT);

		JButton okButton = new JButton("OK");
		okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		okButton.addActionListener(e -> {
			dispose();
		});
		
		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPane.setBackground(Color.white);
		buttonPane.add(okButton);
		
		getContentPane().add(infoPane, BorderLayout.CENTER);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(owner);
	}
}
