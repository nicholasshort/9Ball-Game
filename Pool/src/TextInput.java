//Not My Code
//Credit to Asad
public class TextInput {
	
	    static String text = "";
	    static int textLength;

	    /**
	     * Method to add character to the text string.
	     * @param c character to be added to the text string.
	     */
	    public static void addText(char c) {
	        if(textLength+1<=9) {
	            text+=c;
	            textLength++;
	        }
	    }

	    /**
	     * Method to remove the last character from the text string.
	     */
	    public static void removeText() {
	        if(textLength>0) {
	            text = text.substring(0, text.length()-1);
	            textLength--;
	        }
	    }
	    public static String getText() {
	        return text;
	    }
	
}
