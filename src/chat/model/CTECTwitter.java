package chat.model;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import chat.controller.ChatController;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;


public class CTECTwitter
{
	private ChatController baseController;
	private Twitter twitterBot;
	private List<Status> allTheTweets;
	private List<String> tweetedWords;
	
	public CTECTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		allTheTweets = new ArrayList<Status>();
		tweetedWords = new ArrayList<String>();
		twitterBot = TwitterFactory.getSingleton();
	}
	
	public void sendTweet(String textToTweet)
	{
		try
		{
			twitterBot.updateStatus(textToTweet + " - Isaac Hill" + " @ChatbotCTEC");
		}
		catch(TwitterException tweetError)
		{
			baseController.handleErrors(tweetError);
		}
		catch(Exception otherError)
		{
			baseController.handleErrors(otherError);
		}
	}
	
	public String [] createIgnoredWordsArray()
	{
		String [] boringWords;
		int wordCount = 0;
		
		Scanner boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		while(boringWordScanner.hasNextLine())
		{
			boringWordScanner.nextLine();
			wordCount++;
		}
		
		boringWordScanner.close();
		boringWords = new String[wordCount];
		boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		
		for(int index = 0; index < boringWords.length; index++)
		{
			boringWords[index] = boringWordScanner.next();
		}
		boringWordScanner.close();
				
		
		return boringWords;
	}
	
	public void gatherTheTweets(String username)
	{
		tweetedWords.clear();
		allTheTweets.clear();
		int pageCount = 1;

		Paging statusPage = new Paging(1,100);
		
		while(pageCount <= 10)
		{
			try
			{
				allTheTweets.addAll(twitterBot.getUserTimeline(username, statusPage));
			}
			catch (TwitterException twitterError)
			{
				baseController.handleErrors(twitterError);
			}
			pageCount++;
		}
	}
	
	public String getMostPopularWord(String username)
	{
		gatherTheTweets(username);
		turnTweetsToWords();
		removeBoringWords(); 
		removeBlankWords();
		
		String information = "The tweet count is " + allTheTweets.size() + 
				" and @" + username + "  ";
		
		return information;
	}
	
	private void turnTweetsToWords()
	{
		for(Status currentTweet: allTheTweets)
		{
			String text = currentTweet.getText();
			String [] tweetWords = text.split(" ");
			for(String word : tweetWords)
			{
				tweetedWords.add(word);
			}
		}
	}
	
	private void removeBlankWords()
	{
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			if(tweetedWords.get(index).trim().equals(""))
			{
				tweetedWords.remove(index);
				index--;
			}
		}
	}
	
	private void removeBoringWords()
	{
		String [] boringWords = createIgnoredWordsArray();
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			for(int boringIndex = 0; boringIndex < boringWords.length; boringIndex++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(boringWords[boringIndex]))
				{
					tweetedWords.remove(index);
					index--;
					boringIndex = boringWords.length;
				}
			}
		}
	}
	
	private String caluclateTopWord()
	{
		String results = "";
		String topWord = "";
		int mostPopularIndex = 0;
		int popularCount = 0;
		
		for (int index = 0; index < tweetedWords.size(); index++)
		{
			int currentPopularity = 0;
			for (int searched = index + 1; searched < tweetedWords.size(); searched++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(tweetedWords.get(searched)))
				{
					currentPopularity++;
				}
			}
			if(currentPopularity > popularCount)
			{
				popularCount = currentPopularity;
				mostPopularIndex = index;
				topWord = tweetedWords.get(mostPopularIndex);
			}
			currentPopularity = 0;
		}
		results += topWord + ", and it occurred " + popularCount + "times.";
		results += "\nThat means it has a percentage of " + ((double)popularCount)/tweetedWords.size() + "%. ";
		
		return results;
	}
	
	private String removePunctuation(String currentString)
	{
		String punctuation = ".,':;\"(){}[]<>-";
		
		String scrubbedString = "";
		for(int i = 0; i < currentString.length(); i++)
		{
			if(punctuation.indexOf(currentString.charAt(i)) == -1)
			{
				scrubbedString += currentString.charAt(i);
			}
		}
		return scrubbedString;
	}
	
	public String investigation()
	{
		String results = "";
		
		Query query = new Query("School");
		query.setCount(100);
		query.setGeoCode(new GeoLocation(40.5169, -111.8702), 500, Query.KILOMETERS);
		query.setSince("2017-01-10");
		try
		{
			QueryResult result = twitterBot.search(query);
			results += "Count : " + result.getTweets().size() + "\n";
			for(Status tweet : result.getTweets())
			{
				results += "@" + tweet.getUser().getName() + ": " + tweet.getText() + "\n";
			}
		}
		catch(TwitterException error)
		{
			error.printStackTrace();
		}
		
		return results;
		
	}
}
