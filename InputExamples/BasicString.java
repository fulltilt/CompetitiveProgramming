import java.util.*;
import java.io.*;

// see Competitive Programming 3 p. 234-5
class BasicString {
	public static boolean isVowel(char ch) {
		String vowels = "aeiouAEIOU";
		for (int i = 0; i < vowels.length(); i++) {
			if (ch == vowels.charAt(i)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		StringBuilder ex1 = new StringBuilder();

		// #1
		File f = new File("stringInput.txt");
    Scanner sc = new Scanner(f);
    while (sc.hasNext()) {
      String line = sc.nextLine();
      if (line.indexOf(".......") == 0) {
      	break;
      } else {
      	ex1.append(" " + line);
      }
    }
    System.out.println(ex1);

    // #2
    String T = "I love CS3233 Competitive Programming. I also love AlGoRiThM. Of course I love my wife Grace Suryani very much too.";
    String P = "love";
    int length = T.length();
    int start = 0;
    while (start != -1) {
    	int temp = T.indexOf(P, start);
    	if (temp != -1) {
    		System.out.print(temp + " ");
    		start = temp + 1;
    	} else {
    		System.out.println();
    		break;
    	}
    }

    // #3
    char[] charStr = ex1.toString().toCharArray();
    length = charStr.length;
    int i, letters, consonants, vowels, digits;
    for (i = letters = consonants = vowels = digits = 0; i < length; i++) {
    	if (Character.isDigit(charStr[i])) {
    		++digits;
    	}

    	if (Character.isLetter(charStr[i])) {
    		++letters;
    		if (isVowel(charStr[i])) {
    			++vowels;
    		}
    	}
    }

    consonants = letters - vowels;
    System.out.println("Consonants: " + consonants);
    System.out.println("Vowels: " + vowels);
    System.out.println("Digits: " + digits);

    // #4
    String[] tokens = ex1.toString().split(" ");
    Arrays.sort(tokens);

    // #5
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    T = T.replace(".", "");
    tokens = T.split(" ");
    String most = "";
    int max = 0;
    for (i = 0; i < tokens.length; i++) {
    	if (map.get(tokens[i]) != null) {
    		int temp = map.get(tokens[i]);
    		map.put(tokens[i], ++temp);
    		if (temp > max) {
    			max = temp;
    			most = tokens[i];
    		}
    	} else {
    		map.put(tokens[i], 1);
    	}
    }
    System.out.println(most + ": " + max);
	}
}
