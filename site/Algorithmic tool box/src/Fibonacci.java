import java.util.Scanner;

public class Fibonacci {
  private static long calc_fib(int n) {
	  int f0 = 0, f1 = 1, f2 = 0;
	  if(n == 0){
		  return f0;
	  }
	  if(n == 1){
		  return f1;
	  }
	  for(int i = 1 ; i < n ; i++){
		  f2 = f0 + f1;
		  f0 = f1;
		  f1 = f2;
	  }
	  return f2;
	  
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
