package be.vdab.util;

public class Validator {
	
	// valid tot einde string of tot teken dat geen letter/number is
	public static boolean isAlphaNumeric(String input){
		boolean valid = true;
		char[] charArray = input.toCharArray();
		for(int i = 0 ; i< charArray.length | !valid ; i++){
			if(!Character.isLetterOrDigit(charArray[i])){
				valid = false;
			}
		}
		return valid;
	}
	public static boolean isValidLenth(String input, int length){
		return (input.length() <= length);
	}
	public static boolean isMinLenth(String input, int length){
		return (input.length() >= length);
	}
	public static boolean isNumber(String input){
		boolean valid = true;
		char[] charArray = input.toCharArray();
		for(int i = 0 ; i< charArray.length | !valid ; i++){
			if(!Character.isDigit(charArray[i])){
				valid = false;
			}
		}
		return valid;
	}
}
