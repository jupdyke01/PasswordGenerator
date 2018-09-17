package me.jupdyke01.passwordgen;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main implements ActionListener, ChangeListener {

	// Length of generated password
	private int length;
	// JSlider - Length
	private JSlider slider;
	// JTextField - Password
	private JTextField passwordField;
	// JButton - Copy
	private JButton copyButton;
	// JButton - Generate
	private JButton generateButton;
	// All possible characters that can make up the password
	String charset = "abcdefghijklmnopqrstuvwxyz1234567890.,?";
	// Random int
	Random r;

	public Main() {
		// Init random
		r = new Random();
		
		// JFrame
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JPanel
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setLayout(null);

		// JButton - Generate
		generateButton = new JButton("Generate Password");
		panel.add(generateButton);
		Dimension generateButtonSize = generateButton.getPreferredSize();
		generateButton.setBounds((800/2) - (147/2), 250, generateButtonSize.width, generateButtonSize.height);
		generateButton.addActionListener(this);

		// JButton - Copy
		copyButton = new JButton("Copy");
		panel.add(copyButton);
		Dimension copyButtonSize = copyButton.getPreferredSize();
		copyButton.setBounds((800/2) - (62/2), 125, copyButtonSize.width, copyButtonSize.height);
		copyButton.addActionListener(this);

		// JSlider - Length
		slider = new JSlider(JSlider.VERTICAL, 0, 100, 5);
		panel.add(slider);
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		Font font = new Font("Serif", Font.ITALIC, 15);
		slider.setFont(font);
		Dimension sliderSize = slider.getPreferredSize();
		slider.setBounds(700, 10, sliderSize.width, sliderSize.height + 150);
		length = slider.getValue();
		slider.addChangeListener(this);
		
		// JTextField - Password
		passwordField = new JTextField();
		panel.add(passwordField);
		passwordField.setToolTipText("password");
		passwordField.setText("password");
		passwordField.setBounds((800 / 2) - (500 / 2), 100, 500, 25);


		// Frame Options
		frame.setSize(800, 400);
		frame.setVisible(true);
	}

	public static void main(String args[]) {
		// Call main constructor
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// Button == JButton - Generate
		if (evt.getSource().equals(generateButton)) {
			// Create new password string
			String password = "";
			// Run this for the length of the text in JTextField - Password
			for (int i = 0; i < length; i++) {
				// Populate the new password string with random characters
				password = password + charset.charAt(r.nextInt(charset.length() - 1));
			}
			passwordField.setText(password);
		}
		// Button == JButton - Copy
		if (evt.getSource().equals(copyButton)) {
			// Copy JTextField - Password to clip-board
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(new StringSelection(passwordField.getText()), null);
		
		}
	}

	// Get glider value.
	@Override
	public void stateChanged(ChangeEvent evt) {
		length = slider.getValue();
	}
}
