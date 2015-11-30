package be.vdab.util;

public class Validator {
	
	// empty ook true
    public static boolean isAlphaNumeric(String input) {
        boolean valid = true;
        char[] charArray = input.toCharArray();
        if(input.isEmpty()) valid = false;
        for (int i = 0; i < charArray.length && valid; i++) {
            if (!Character.isLetterOrDigit(charArray[i])) {
                valid = false;
            }
        }
        return valid;
    }
    
	public static boolean isMaxLenth(String input, int length){
		return (input.length() <= length);
	}
	
	public static boolean isMinLenth(String input, int length){
		return (input.length() >= length);
	}
	
	public static boolean isInteger(String input){
		boolean valid = true;
		char[] charArray = input.toCharArray();
		if(input.isEmpty()) valid = false;
		for(int i = 0 ; i< charArray.length && valid ; i++){
			if(!Character.isDigit(charArray[i])){
				valid = false;
			}
		}
		return valid;
	}
}
