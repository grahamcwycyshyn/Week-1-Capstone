import java.util.Scanner;

public class PigLatin {
	public static void main(String[] args) {

		System.out.println("Welcome to the Pig Latin Translator!");

		Scanner input = new Scanner(System.in);

		String answer = "y";

		do {
			
			System.out.println("Enter a word or phrase:");
			
			String userInput = input.nextLine();
			
			//If statement to check if the user has entered a word
			if(userInput.contentEquals("")) {
				
				System.out.println("Enter a word or prase:");
				
				continue;
				
			}
			
			System.out.println("Here is the pig latin!");
			
			//If statement to see if there are no spaces in the input
			if (userInput.indexOf(" ") == -1) {

				System.out.println(oneWord(userInput));

			}
			
			//Else, if there are spaces, the program will isolate each word and apply oneWord
			else {
				
				if(userInput.charAt(userInput.length() - 1) == ' ') {
					
					userInput = userInput.substring(0, userInput.length() - 1);
					
				}
				
				String result = "";
				
				while(userInput.indexOf(" ") > 0) {
					
					result = result.concat(oneWord(userInput.substring(0, userInput.indexOf(" "))));
					
					result = result + " ";
					
					userInput = userInput.substring(userInput.indexOf(" ") + 1);
					
				}
				
				System.out.println(result + oneWord(userInput));
				
			}
			
			System.out.println("Would you like to continue? (y/n)");
			
			answer = input.nextLine();

		} while (answer.contentEquals("y") || answer.contentEquals("Y"));
			
		input.close();
		
	}
	
	//This method translates one word to pig latin
	public static String oneWord(String word) {
		
		//If statement to see if there is a special character in the word
		if(checkSpecial(word) == true) {
			
			return word;
			
		}
		
		//If statement to see if there is punctuation at the end of the word 
		if(word.charAt(word.length() - 1) == '.' || word.charAt(word.length() - 1) == ',' ||
		word.charAt(word.length() - 1) == ';' || word.charAt(word.length() - 1) == ':' ||
		word.charAt(word.length() - 1) == '?' || word.charAt(word.length() - 1) == '!') {
			
			String punctuation = word.substring(word.length() - 1);
			
			word = word.substring(0, word.length() - 1);
			
			return oneWord(word) + punctuation;
			
		}
		
		//If statement that uses UniCode to see if the first letter is a vowel
		if( word.codePointAt(0) == 65 || word.codePointAt(0) == 97 ||
			word.codePointAt(0) == 69 || word.codePointAt(0) == 101 ||
			word.codePointAt(0) == 73 || word.codePointAt(0) == 105 ||
			word.codePointAt(0) == 79 || word.codePointAt(0) == 111 ||
			word.codePointAt(0) == 85 || word.codePointAt(0) == 117) {

			if(checkCase(word).contentEquals("upperCase")) {
				
				return word.concat("WAY");
			
			} 
		
			else {
				
				return word.concat("way");
				
			}
		}
			
		//If statement to check if the word starts with th, sh, etc.
		else if(word.substring(0,2).toLowerCase().contentEquals("th") ||
				word.substring(0,2).toLowerCase().contentEquals("ch") ||
				word.substring(0,2).toLowerCase().contentEquals("sh") ||
				word.substring(0,2).toLowerCase().contentEquals("wh") ||
				word.substring(0,2).toLowerCase().contentEquals("ph")) {
			
			if(checkCase(word).contentEquals("upperCase")) {
				
				return  word.substring(2).concat(word.substring(0, 2) + "AY");
			
			}
			
			if(checkCase(word).contentEquals("titleCase")) {
				
				return word.substring(2, 3).toUpperCase() + 
				word.substring(3).concat(word.substring(0, 2).toLowerCase() + "ay");
						
			}
			
			else {
				
				return word.substring(2).concat(word.substring(0, 2) + "ay");
				
			}
		}
		
		else { 
			
			if(checkCase(word).contentEquals("upperCase")) {
				
				return  word.substring(1).concat(word.substring(0, 1) + "AY");
			
			}
			
			if(checkCase(word).contentEquals("titleCase")) {
				
				if(word.length() == 2) {
					
					return word.substring(1).toUpperCase() + word.substring(0, 1).toLowerCase() + "ay";
					
				}
				
				return word.substring(1, 2).toUpperCase() + 
				word.substring(2).concat(word.substring(0, 1).toLowerCase() + "ay");

			}

			else {

				return word.substring(1).concat(word.substring(0, 1) + "ay");

			}
		}
	}
	
	
	//This method determines the case of a single word
	public static String checkCase(String word) {
		
		String upper = "upperCase";
		
		String lower = "lowerCase";
		
		String title = "titleCase";
		
		//caseCheck has trouble with words of length 1
		if(word.length() == 1 && Character.isUpperCase(word.charAt(0))) {
			
			return title;
			
		}
		
		if(Character.isUpperCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1))) {
			
			return upper;
			
		}
		
		else if(Character.isUpperCase(word.charAt(0)) && Character.isLowerCase(word.charAt(1))) {
			
			return title;
			
		}
		
		else { 
			
			return lower;
			
		}
	}
	
	//This method checks to see if the word has a special character or not
	public static boolean checkSpecial(String word) {
		
		boolean special = true;
		
		for (int i = 0; i < word.length(); i++) {

			if (!(((word.codePointAt(i) > 64) && (word.codePointAt(i) < 91)) ||
			   ((word.codePointAt(i) > 96) && (word.codePointAt(i) < 123)) ||
				(word.codePointAt(i) == 46) || (word.codePointAt(i) == 44) ||
				(word.codePointAt(i) == 59) || (word.codePointAt(i) == 58) ||
				(word.codePointAt(i) == 63) || (word.codePointAt(i) == 33))) {

				special = true;
				
				break;
				
			}
			
			else {
				
				special = false;
				
			}
		}
		
		if(special == true) {
			
			return true;
			
		}
		
		else { 
			
			return false;
			
		}
	}
}
