/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gjkbroadcasts;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Jacky
 */
public class TwitterHelper {
    public static boolean validApi = false;
    
    public static Twitter setUpLogin(String consKey, String consSec, String acceTok, String acceSec)  {
        
        
        //configure the api (copied setup from https://twitter4j.org/en/configuration.html)
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consKey)//logs you in to the twittwe account
                .setOAuthConsumerSecret(consSec)
                .setOAuthAccessToken(acceTok)
                .setOAuthAccessTokenSecret(acceSec);
        //Create a new instance of a twitter page
        Twitter testTwit = new TwitterFactory(cb.build()).getInstance();
        try { 
            //tries reading 1st page and 1st tweet to test if it works
            Paging paging = new Paging(1,1);
            //puts the tweets in a list, and see if it works
            List<Status> statuses = testTwit.getUserTimeline(paging);
            //inform it does work
            System.out.println("Valid API c: \nKeys and Tokens are valid");
            //api is not valid
            validApi = true;
        }
        catch (TwitterException e){
            validApi = false;
            System.out.println("API Error :c \nInvalid keys and tokens") ;
        }
        return testTwit;
    }
    
    //grabs the keys from file, file read
    public static Twitter readKeysLogin() throws FileNotFoundException, IOException {
        
        String[] keys = {}; //string array to seperate keys and tokens        
        Scanner s = new Scanner(new File("keys.txt")); //reads in keys and tokens
        keys = s.nextLine().split(","); //splits the keys and tokens apart to setup api login
        return setUpLogin(keys[0], keys[1], keys[2], keys[3]);
    }
    
    
    //Grabs the latest tweets
    public static String readLatest(Twitter twitter, String handle) throws TwitterException {
        if (handle == ""){
            handle = twitter.getScreenName();
        }
        Paging paging = new Paging(1, 10);
        //First param of Paging() is the page number, second is the number per page (this is capped around 200 )
        String tweets = "<html>"; //store the tweets to spit out *Note*  HTML is used inorder to implement proper lines, however will be removed in the proper project
        List<Status> statuses = twitter.getUserTimeline(handle,paging); //statues on profiles are stored as lists
        //grabs all the statuses 
        for (Status status : statuses) {
            //appends onto the string to be returned
            tweets += ("@" + status.getUser().getScreenName() + " - " + status.getText() + "<br/>");
        }    
        tweets += "</html>"; //end the html text
        
        return tweets; //send out the tweets
    }

    //Grabs the account's handle name
    public static String getHandle(Twitter twitter, String handle) throws TwitterException{
        if (handle == ""){
        return twitter.verifyCredentials().getScreenName();
        }
        else{
            return handle;
        }
    }
    //Grabs the profile url, only used within this page to download the profile pages
    private static URL getPFP(Twitter twitter) throws TwitterException, MalformedURLException {
        User user = twitter.showUser(twitter.getId()); //grabs the id of user to get its profile picture web url
        return new URL(user.getProfileImageURL());  //return the url of the profile picture
    }
    //Download the profile picture from Twitter page
    public static ImageIcon grabPfp(Twitter twitter) {
        try {
            BufferedImage img = ImageIO.read(TwitterHelper.getPFP(twitter)); //Load the image into Java from the webpage URL       
            File file = new File("pfp.jpg");  //The file that is to be loaded on to        
            ImageIO.write(img, "jpg", file);  //Write the image from the URL to local file       
            return new ImageIcon("pfp.jpg");  //set the image up
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage() + "\nError grabbing image from online");
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\nError Reading Input, using default profile picture");
        } catch (TwitterException e) {
            System.out.println(e.getMessage() + "\nTwitter Key Error");
        }
        //spits out the default profile picture
        return new ImageIcon("school.jpg");
    }
}
