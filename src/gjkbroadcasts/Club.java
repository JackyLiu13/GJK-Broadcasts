/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gjkbroadcasts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Club Object
 * Used to extract and sort club from text file
 * NOTE CLUBS == SPORTS TEAM
 * @author Jacky
 */
public class Club {




    
    //instance variables
    private String name, timeStart, timeEnd;
        
    //club obj constructor
    public Club (String timeStart, String name, String timeEnd){
        this.name = name;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
    
    //Reads the clubs from a file and constructs the clubs by extracting values from delimiter
    public static Club[] readClubs() throws FileNotFoundException {
        String[] info = {}; //string array to grab the text from the file temporiliy  
        Scanner s = new Scanner(new File("clubs.txt")); //reads in clubs
        int numClubs = 0; //store index size
        while (s.hasNextLine()) {
            s.nextLine();
            numClubs++; //count the index
        }
        Club[] clubs = new Club[numClubs]; //resize the clubs array
        s = new Scanner(new File("clubs.txt")); //rereads clubs for consturctors
        for (int i = 0; i < numClubs; i++) {
            info = s.nextLine().split(","); //splits the keys and tokens apart to setup api login
            clubs[i] = new Club( info[0],info[1], info[2]); //create constructors with new values found from file
        }
        return clubs; //spits out the clubs
    }
    
    //spits the club values out as a string delimited with tabs ------ polymorphism
    public String toString() {
        return (getTimeStart() + "\t" +name + "\t" + getTimeEnd()); //spits the club's value delimited by a tab
    }
    
   // sorting method -----------------
    /**BubbleSche 
     * @author Jacky
     * Modifed Bubble sort to reorder the schedule 
     * Moves to another process that catches the unproperly filtered time values
     * First uses bubble sort method that moves greater values up. Then checks if values are the same "am/pm" value. Then checks if comparing if both values is >10 to sort
     * If the previous value is a "pm" value, will "rise" in the sort
     * 
     * @param arrClub the input clubs that are to be sorted
     */
    public static void bubbleSche(Club[] arrClub) {
        int n = arrClub.length; //number of clubs running during the day
        Club temp; //temporary club for moving values
        for (int i = 0; i < n; i++) { //runs through all the clubs
            for (int j = 1; j < (n - i); j++) { //runs through all the greater value clubs 
                
                //check if the previous time character value is more than current
                if (arrClub[j - 1].getTimeStart().compareTo(arrClub[j].getTimeStart()) < 0) {
                    //swap previous element up
                    temp = arrClub[j - 1];
                    arrClub[j - 1] = arrClub[j];
                    arrClub[j] = temp;
                }
                //extra filtering
                //check if the same "am/pm" is being compared 
                if (arrClub[j - 1].getTimeStart().charAt(5) == arrClub[j].getTimeStart().charAt(5)) {
                    if (arrClub[j - 1].getTimeStart().charAt(0) == '1' && arrClub[j].getTimeStart().charAt(0) == '0' ) {
                        //swap elements  
                        temp = arrClub[j - 1];
                        arrClub[j - 1] = arrClub[j];
                        arrClub[j] = temp;
                    }
                    
                    //check if time <10 or >10 to be compared together 
                    if (arrClub[j - 1].getTimeStart().charAt(0) == arrClub[j].getTimeStart().charAt(0)) {
                        int a = Integer.parseInt(String.valueOf(arrClub[j - 1].getTimeStart().charAt(1)));
                        int b = Integer.parseInt(String.valueOf(arrClub[j].getTimeStart().charAt(1)));
                        //grabs the second value to see if greater
                        if (a > b) {
                            //swap elements  
                            temp = arrClub[j - 1];
                            arrClub[j - 1] = arrClub[j];
                            arrClub[j] = temp;
                        }
                    } 
                } else if (arrClub[j - 1].getTimeStart().charAt(5) == 'p') { //if previous val is pm, will automatically swap up since current val is am
                    //swap elements  
                    temp = arrClub[j - 1];
                    arrClub[j - 1] = arrClub[j];
                    arrClub[j] = temp;
                }
            }
        }

    }
    
//Encapsulation ----------------
    /**get the starting time
     * @return the timeStart
     */
    public String getTimeStart() {
        return timeStart;
    }

    /** get the end time
     * @return the timeEnd
     */
    public String getTimeEnd() {
        return timeEnd;
    }
}
