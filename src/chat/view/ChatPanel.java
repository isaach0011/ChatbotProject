package chat.view;

import chat.controller.ChatController;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 * Constructs the objects ChatController, SpringLayout, JTextArea, JTextField, JButton, and JLabel
 * @author Isaac Hill
 *
 */
public class ChatPanel extends JPanel
{
	private ChatController baseController;
	private SpringLayout baseLayout;
	private JTextArea chatDisplay;
	private JTextField chatField;
	private JButton chatButton;
	private JButton searchTwitterButton;
	private JButton sendTwitterButton;
	private JButton saveButton;
	private JButton loadButton;
	private JLabel chatLabel;
	
	public ChatPanel(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(20, 65);		
		chatField = new JTextField(60);		
		chatButton = new JButton("Chat");				
		chatLabel = new JLabel("Chatbot");		
		searchTwitterButton = new JButton("Search Twitter");		
		sendTwitterButton = new JButton("Send Tweet");		
		saveButton = new JButton("Save");				
		loadButton = new JButton("Load");
		

		setupChatDisplay();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	/**
	 * Sets up the JTextField with certain conditions.
	 */
	private void setupChatDisplay()
	{
		chatDisplay.setEditable(false);
		chatDisplay.setEnabled(false);
		chatDisplay.setLineWrap(true);
		chatDisplay.setWrapStyleWord(true);
	}
	
	/**
	 * Initializes all GUI components and adds them to the Frame.
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setPreferredSize(new Dimension(900,600));
		this.setBackground(Color.lightGray);
		
		chatLabel.setFont(new Font("BigNoodleTitling", Font.ITALIC, 50));
		
		this.add(chatDisplay);
		this.add(chatButton);
		this.add(searchTwitterButton);
		this.add(sendTwitterButton);
		this.add(saveButton);
		this.add(loadButton);
		this.add(chatField);
		this.add(chatLabel);
	}
	
    /**
	 * Dumps auto-generated code from window builder.
	 */
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, sendTwitterButton);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 0, SpringLayout.WEST, saveButton);
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 252, SpringLayout.EAST, saveButton);
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 0, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 0, SpringLayout.WEST, chatField);
		baseLayout.putConstraint(SpringLayout.NORTH, sendTwitterButton, 15, SpringLayout.SOUTH, searchTwitterButton);
		baseLayout.putConstraint(SpringLayout.EAST, sendTwitterButton, 0, SpringLayout.EAST, chatDisplay);
		baseLayout.putConstraint(SpringLayout.NORTH, searchTwitterButton, 0, SpringLayout.NORTH, chatButton);
		baseLayout.putConstraint(SpringLayout.EAST, searchTwitterButton, 0, SpringLayout.EAST, chatDisplay);
		baseLayout.putConstraint(SpringLayout.NORTH, chatDisplay, 5, SpringLayout.SOUTH, chatLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, chatLabel, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatLabel, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 45, SpringLayout.SOUTH, chatField);
		baseLayout.putConstraint(SpringLayout.NORTH, chatField, 6, SpringLayout.SOUTH, chatDisplay);
		baseLayout.putConstraint(SpringLayout.EAST, chatField, -82, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatDisplay, -57, SpringLayout.EAST, this);
		
	}
	
	/**
	 * Sets actions for buttons when they are pressed.
	 */
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String userWords = chatField.getText();
				String botResponse = baseController.useChatbotCheckers(userWords);
				
				chatDisplay.setText("You said: " + userWords + "\n" + "Chatbot said: " + botResponse);
				chatField.setText("");
			}
		});
		
		searchTwitterButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
		
		sendTwitterButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
		
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
	}
}
