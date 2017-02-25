import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
    	if(m < 5){
    		return m;
    	}
    	int numberOfCoins = 0, rest = m;
    	if(m >= 10){
        	numberOfCoins += m/10;
        	rest = m%10;
        }
    	if(rest >= 5){
    		numberOfCoins += rest/5;
        	rest = m%5;
    	}
    	if(rest >= 1){
    		numberOfCoins += rest;
    		rest = 0;
    	}
    	
        return numberOfCoins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

