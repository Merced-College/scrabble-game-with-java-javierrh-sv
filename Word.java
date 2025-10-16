public class Word implements Comparable<Word> {
    private String word;
    private String definition;


    //Default constructor
    public Word() {
        word = "null";
        definition = "none";
    }


    //Parameterized constructor
    public Word(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }


    //Assigns points based on the length of the user's word
    public int getPoints(String word) {
        //Get the length of the word
        int wordLength = word.length();
        //Initialize points to 0
        int points = 0;


        //Based on the size of the word, assign a different value
        //to points - 1-2 letters is 1 point, 3 is 2 points, 4 is 3 points
        switch(wordLength) {
            case 1:
                points = 1;
                break;
            case 2:
                points = 1;
                break;
            case 3:
                points = 2;
                break;
            case 4:
                points = 3;
                break;
        }//end switch
       
        return points;


    }//end getPoints
   
    //Getters and setters
    public void setWord(String word) {
        this.word = word;
    }


    public void setDefinition(String definition) {
        this.definition = definition;
    }


    public String getWord() {
        return word;
    }


    public String getDefinition() {
        return definition;
    }


    //Overriding toString
    @Override
    public String toString() {
        return word + ": " + definition;
    }


    //Overriding compareTo to only compare our words, not
    //the whole object
    //  (Comparing the whole object would result in an error,
    //  as our user word has no definition)
    @Override
    public int compareTo(Word other) {
        return this.word.compareTo(other.word);
    }


}










