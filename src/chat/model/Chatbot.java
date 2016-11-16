package chat.model;

import java.util.ArrayList;

/**
 * Base version of the 2015 Chatbot class. Only stub methods are provided. Students will complete methods as part
 * of the project.
 * @author Isaac Hill
 * @version 1.0 10/14/16
 */
public class Chatbot
{
	private ArrayList<String> memesList;
	private ArrayList<String> politicalTopicList;
	private String userName;
	private String content;
	
	/**
	 * Creates an instance of the Chatbot with the supplied username.
	 * @param userName The username for the chatbot.
	 */
	public Chatbot(String userName)
	{
		this.memesList = new ArrayList<String>();
		this.politicalTopicList = new ArrayList<String>();
		this.userName = userName;
		this.content = new String("dogs");
		this.buildMemesList();
		this.buildPoliticalTopicsList();
	}
	
	private void buildMemesList()
	{
		memesList.add("doge");
		memesList.add("cute animals");
		memesList.add("grumpy cat");
		memesList.add("dat boi");
		memesList.add("willy wonka");
		memesList.add("harambe");
		memesList.add("john cena");
		memesList.add("pawn stars");
		memesList.add("pepe");
		memesList.add("ken bone");
		memesList.add("bad luck brian");
		memesList.add("michael phelps");
		memesList.add("spongebob");
		memesList.add("pope");
		memesList.add("arthur");
		memesList.add("danny phantom");
		memesList.add("boi");
		memesList.add("battlefield 1");
		memesList.add("icup");
	}
	
	private void buildPoliticalTopicsList()
	{
		politicalTopicList.add("Democrat");
		politicalTopicList.add("Republican");
		politicalTopicList.add("11/8/16");
		politicalTopicList.add("liberal");
		politicalTopicList.add("conservative");
		politicalTopicList.add("Clinton");
		politicalTopicList.add("Trump");
		politicalTopicList.add("Kaine");
		politicalTopicList.add("Pence");
		politicalTopicList.add("Stein");
		politicalTopicList.add("Johnson");
		politicalTopicList.add("election");
		politicalTopicList.add("Obama");
		politicalTopicList.add("Washington");
		politicalTopicList.add("Lincoln");
		politicalTopicList.add("Jackson");
		politicalTopicList.add("Hillary");
		politicalTopicList.add("McCain");
	}
	
	/**
	 * Checks the length of the supplied string. Returns false if the supplied String is empty or null,
	 * otherwise returns true. 
	 * @param currentInput
	 * @return A true or false based on the length of the supplied String.
	 */
	public boolean lengthChecker(String currentInput)
	{
		boolean hasLength = false;
		
		if (currentInput != null && currentInput.length() > 0)
		{
			hasLength = true;
		}
		
		return hasLength; 
	}
	
	/**
	 * Checks if the supplied String matches the content area for this Chatbot instance.
	 * @param currentInput The supplied String to be checked.
	 * @return Whether it matches the content area.
	 */
	public boolean contentChecker(String currentInput)
	{
		boolean hasContent = false;
		
		String temp = "⎲";
		String temp1 = "⎳";
		System.out.println(temp);
		System.out.println(temp1);
		
		if (currentInput.toLowerCase().contains(content.toLowerCase()))
		{
			hasContent = true;
		}
		
		return hasContent;
	}
	
	/**
	 * Checks if supplied String matches ANY of the topics in the politicalTopicsList. Returns
	 * true if it does find a match and false if it does not match.
	 * @param currentInput The supplied String to be checked.
	 * @return Whether the String is contained in the ArrayList.
	 */
	public boolean politicalTopicChecker(String currentInput)
	{
		boolean hasPoliticalTopic = false;
		int index;
		
		for (index = 0; index < 18; index++)
		{
			if (currentInput.equals(politicalTopicList.get(index)))
			{
				hasPoliticalTopic = true;
			}
		}
		return hasPoliticalTopic;
	}
	
	
	/**
	 * Checks to see that the supplied String value is in the current memesList variable.
	 * @param currentInput The supplied String to be checked.
	 * @return Whether the supplied String is a recognized meme.
	 */
	public boolean memeChecker(String currentInput)
	{
		boolean hasMemes = false;
		int index;
		
		for (index = 0; index < 19; index++)
		{
			if (currentInput.equalsIgnoreCase(memesList.get(index)))
			{
				hasMemes = true;
			}
		}
		return hasMemes;
	}
	
	/**
	 * Returns the username of this Chatbot instance.
	 * @return The username of the Chatbot.
	 */
	public String getUserName()
	{
		return userName;
	}
	
	/**
	 * Returns the content area for this Chatbot instance.
	 * @return The content area for this Chatbot instance.
	 */
	public String getContent()
	{
		return content;
	}
	
	/**
	 * Getter method for the memesList object.
	 * @return The reference to the meme list.
	 */
	public ArrayList<String> getMemesList()
	{
		return memesList;
	}
	
	/**
	 * Getter method for the politicalTopicList object.
	 * @return The reference to the political topic list.
	 */
	public ArrayList<String> getPoliticalTopicList()
	{
		return politicalTopicList;
	}
	
	/**
	 * Updates the content area for this Chatbot instance.
	 * @param content The updated value for the content area.
	 */
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public boolean keyboardMashChecker(String currentInput)
	{
		boolean keyboardMash = false;
		
		if (currentInput.equals("sdf"))
		{
			keyboardMash = true;
		}
		
		if (currentInput.equals("dfg"))
		{
			keyboardMash = true;
		}
		
		if (currentInput.equals("cvb"))
		{
			keyboardMash = true;
		}
		
		if (currentInput.equals(",./"))
		{
			keyboardMash = true;
		}
		
		return keyboardMash;
	}
	
	public boolean inputHTMLChecker(String currentInput)
	{
		String trimmed = currentInput.replaceAll(" ", "");
		
		boolean inputHTML = false;
		
		if((trimmed.startsWith("<") && trimmed.contains(">")) && (trimmed.length() > 2 && !currentInput.endsWith("  ") && !currentInput.endsWith("F> </a>")))
		{
			inputHTML = true;
		}
		
		return inputHTML;
	}
	
	public boolean inputHTMLCheckers(String currentInput)
	{
		boolean inputHTML = false;
		
		if(currentInput.contains("<B>"))
		{
			int index = currentInput.indexOf("<B>") + 3;
			String sub = currentInput.substring(index);
			
			if(sub.contains("</B>"))
			{
				inputHTML = true;
			}
		}
		
		else if(currentInput.contains("<I>"))
		{
			int index = currentInput.indexOf("<I>") + 3;
			String sub = currentInput.substring(index);
			
			if(sub.contains("</i>"))
			{
				inputHTML = true;
			}
		}
		
		else if(currentInput.contains("<P>"))
		{
			inputHTML = true;
		}
		
		else if(currentInput.contains("<A"))
		{
			int index = currentInput.indexOf("<A") + 2;
			String sub = currentInput.substring(index);
			
			if(sub.contains("</a>"))
			{
				inputHTML = true;
			}
		}
		
		
		return inputHTML;
	}
	public boolean twitterChecker(String currentInput)
	{
		boolean twitter = false;
		
		if(currentInput.contains("#"))
		{
			int index = currentInput.indexOf("#");
			
			if (index == 0)
			{
				twitter = true;
			}
		}
		else if(currentInput.contains("@"))
		{
			int index = currentInput.indexOf("@");

			if (index == 0)
			{
				twitter = true;
			}
		}
		
		return twitter;
	}
	public boolean quitChecker(String currentInput)
	{
		boolean quit = false;
		
		if (currentInput.equals("quit"))
		{
			quit = true;
		}
		
		return quit;
	}
}
