package business;

public class GamePlay {
	
	public static int randomNumberGenerator() {

		int num = (int) (Math.random() * 100);
		num ++;
		return num;
	}
	
	public static int scoreBoard(int attempts) {
		
		int score = 0;
		
		if (attempts == 0) {
			score = 50;
		}
		else if (attempts == 1) {
			score = 40;
		}
		else if (attempts == 2) {
			score = 30;
		}
		else if (attempts == 3) {
			score = 50;
		}
		else if (attempts == 4) {
			score = 10;
		}
		else if (attempts == 5){
			score = 0;
		}
		
		return score;
	}
	
	public static String guessRange(int number, int guess) {
		
		String message = "";
		
		if (guess > number) {
			
			//System.out.println("Your guess is higher");
			
			message = "Your guess is higher";
		}
		else if (guess < number) {
			
			//System.out.println("Your guess is lower");
			
			message = "Your guess is lower";
		}
//		else if(guess == number) {
//			System.out.println("You Wiiiiiiiiiiiiiiiiiiiiin!!!");
//		}
		
		return message;
	}
}
