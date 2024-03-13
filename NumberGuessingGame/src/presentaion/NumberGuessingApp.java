package presentaion;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import business.GamePlay;
import business.Validation;

public class NumberGuessingApp extends JFrame{
	
	private static int randomNumber = GamePlay.randomNumberGenerator(); 
	private static int life = 5;
	private static String quit = "";
	private static int attempt;
	private static int totalScore;
	
	private JTextField lifesField;
	private JTextField currentScoreField;
	private JTextField totalScoreField;
	private JTextArea guessingField;
	
	private JLabel beginLabel;
	
	private JButton startButton;
	private JButton exitButton;
	private JButton submitButton;
		
	public NumberGuessingApp() {
		
		initComponents();
	}
	
	private void initComponents() {
		
		setTitle("Number Guessing Game");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		//Top
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());

		JLabel lifeLabel = new JLabel("Life(s): ");
		topPanel.add(lifeLabel);
		lifesField = new JTextField(5);
		lifesField.setText(String.valueOf(life));
		lifesField.setEditable(false);
		topPanel.add(lifesField);
		
		JLabel currentScoreLabel = new JLabel("Current Score: ");
		topPanel.add(currentScoreLabel);
		currentScoreField = new JTextField(5);
		currentScoreField.setEditable(false);
		topPanel.add(currentScoreField);
		
		JLabel totalScoreLabel = new JLabel("Total Score: ");
		topPanel.add(totalScoreLabel);
		totalScoreField = new JTextField(5);
		totalScoreField.setEditable(false);
		topPanel.add(totalScoreField);
		
		//Label
		JPanel justLabelPanel = new JPanel();
		beginLabel = new JLabel("Start guessing (1 - 100)");
		beginLabel.setVisible(false);
		justLabelPanel.add(beginLabel);
		
		//Middle
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new FlowLayout());
		
		guessingField = new JTextArea(7, 30);
		guessingField.setLineWrap(true);
		guessingField.setWrapStyleWord(true);
		middlePanel.add(guessingField);
		
		//Bottom
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		
		startButton = new JButton("START");
		startButton.setForeground(Color.BLACK);
		startButton.setBackground(Color.GREEN);
		
		
		bottomPanel.add(startButton);

		exitButton = new JButton("QUIT");
		exitButton.setForeground(Color.WHITE);
		exitButton.setBackground(Color.RED);
		exitButton.setEnabled(false);
		bottomPanel.add(exitButton);
		
		submitButton = new JButton("ENTER");
		submitButton.setForeground(Color.BLACK);
		submitButton.setBackground(Color.GREEN);
		submitButton.setEnabled(false);
		bottomPanel.add(submitButton);
		
		exitButton.addActionListener(action -> {
			
			quitGame();
			startButton.setEnabled(true);
			exitButton.setEnabled(false);
			submitButton.setEnabled(false);
			
			beginLabel.setVisible(false);

		});
		
		submitButton.addActionListener(action -> {
			takeGuess();
		});
		
		startButton.addActionListener(action -> {
			
			exitButton.setEnabled(true);
			submitButton.setEnabled(true);
			startButton.setEnabled(false);
			
			beginLabel.setVisible(true);


			startGame();
		});
		
		setLayout(new GridLayout(0, 1, 0, 10));
		add(topPanel);
		add(justLabelPanel);
		add(middlePanel);
		add(bottomPanel);
			
	}

	private void startGame() {

		//System.out.println("Number to guess(from start): "+randomNumber);
		
		totalScore = GamePlay.scoreBoard(5);

		currentScoreField.setText(String.valueOf(totalScore));
		totalScoreField.setText(String.valueOf(totalScore));
		
		lifesField.setText(String.valueOf(5));
		
	}

	private void quitGame() {
		
		quit = "q";
		currentScoreField.setText(String.valueOf(totalScore));
		totalScoreField.setText(String.valueOf(totalScore));
		
		randomNumber = GamePlay.randomNumberGenerator();
		
		quit = "";
		
	}
	
//	private void printRandomNumber() {
//		
//		System.out.println("Number to guess: "+randomNumber);
//
//	}
	
	private void disableButtons() {
		
		exitButton.setEnabled(false);
		submitButton.setEnabled(false);
		startButton.setEnabled(true);
	}
	
	private void takeGuess() {
		
		Validation validate = new Validation();
		
		String isEmptyMsg = "";
		
		isEmptyMsg += validate.isPresent(guessingField.getText(), "Your Guess");
		
		if(!isEmptyMsg.isBlank()) {
			
			String title = "Invalid Entry";
			JOptionPane.showMessageDialog(null, isEmptyMsg,  title, JOptionPane.WARNING_MESSAGE);
			
			guessingField.requestFocusInWindow();
			return;
		}
		
		String isInvalidMsg = "";
		
		isInvalidMsg += validate.isInteger(guessingField.getText(), "Your Guess");
		
		if(!isInvalidMsg.isBlank()) {
			
			String title = "Invalid Entry";
			JOptionPane.showMessageDialog(null, isInvalidMsg, title, JOptionPane.ERROR_MESSAGE);
			
			guessingField.requestFocusInWindow();
			return;
		}
		
		String isLargerMsg = "";
		
		isLargerMsg += validate.isLarger(guessingField.getText(), "Your Guess");
		
		if(!isLargerMsg.isBlank()) {
			
			String title = "Invalid Entry";
			JOptionPane.showMessageDialog(null, isLargerMsg, title, JOptionPane.ERROR_MESSAGE);
			
			guessingField.requestFocusInWindow();
			return;
		}
		
		String isSmallerMsg = "";
		
		isSmallerMsg += validate.isSmaller(guessingField.getText(), "Your Guess");
		
		if(!isSmallerMsg.isBlank()) {
			
			String title = "Invalid Entry";
			JOptionPane.showMessageDialog(null, isSmallerMsg, title, JOptionPane.ERROR_MESSAGE);
			
			guessingField.requestFocusInWindow();
			return;
		}
		
		//printRandomNumber();
		
		int yourGuess = Integer.parseInt(guessingField.getText());
				
		if(!quit.equalsIgnoreCase("Q")) {
			
			GamePlay.guessRange(randomNumber, yourGuess);
			
			if(yourGuess == randomNumber) {
				
				GamePlay.scoreBoard(attempt);
				
				JOptionPane.showMessageDialog(null, "Correct guess\nYou WiiiiiiiiiiiiiiN!!!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
				
				beginLabel.setText("Continue guessing (1 - 100)");
				
				totalScore += GamePlay.scoreBoard(attempt);
				currentScoreField.setText(String.valueOf(totalScore));
				
				if(!quit.equalsIgnoreCase("Q")) {
					
					randomNumber = GamePlay.randomNumberGenerator();
					
					//printRandomNumber();
				}
			}
			else if(life <= 1) {
				
				attempt = 5;
				
				GamePlay.scoreBoard(attempt);
				
				JOptionPane.showMessageDialog(null, "You lost :(\nThe number to guess was "+randomNumber, "Bad Luck", JOptionPane.INFORMATION_MESSAGE);
				
				beginLabel.setText("Thank you for playing");
				
				totalScore += GamePlay.scoreBoard(attempt);
				currentScoreField.setText(String.valueOf(totalScore));
				totalScoreField.setText(String.valueOf(totalScore));
				
				disableButtons();
				
				if(!quit.equalsIgnoreCase("Q")) {
					
					life = 5;
					attempt = 0;
					randomNumber = GamePlay.randomNumberGenerator();
					
					//printRandomNumber();
				}
			}
			else {
				
				life --;
				lifesField.setText(String.valueOf(life));
				attempt ++;
				
				String range = GamePlay.guessRange(randomNumber, yourGuess);
				beginLabel.setText(range);

			}
			
		}

	}

	public static void main(String[] args) {
		
		java.awt.EventQueue.invokeLater(() -> {
			
			JFrame frame = new NumberGuessingApp();
		});
	}

}
