package chat.view;

import javax.swing.JFrame;
import chat.controller.ChatController;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Constructs the objects ChatController and ChatPanel.
 * @author Isaac Hill
 *
 */
public class ChatFrame extends JFrame
{
	
	private ChatController baseController;
	private ChatPanel basePanel;
	private ArrayList<String> messageList;
	private File file;
	
	public ChatFrame(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		basePanel = new ChatPanel(baseController);
		this.file = new File("src/chat/view/files/messages.txt");
		this.messageList = new ArrayList<String>();
		setupFrame();
	}
	
	/**
	 * Sets up the window with certain conditions.
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setTitle(this.windowMessagePicker());
		this.setSize(new Dimension(900, 600));
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private String windowMessagePicker()
	{
		
		buildMessageList();
		
		String windowMessage = messageList.get((int) (Math.random() * messageList.size()));
		
		if(windowMessage.equalsIgnoreCase("Electric Boogaloo"))
		{
			return "Chatbot 2: " + windowMessage;
		}
		
		return "Chatbot: " + windowMessage;

	}
	
	private void buildMessageList()
	{
		try 
		{

	        Scanner sc = new Scanner(file);

	        while (sc.hasNextLine()) 
	        {
	            String i = sc.nextLine();
	            messageList.add(i);
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) 
		{
	        e.printStackTrace();
	    }
	}
}


