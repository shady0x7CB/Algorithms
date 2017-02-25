import java.util.*;

public class LCM {
  private static long GCD(long a, long b){
	  if(b < 1){
		  return a;
	  }
	  return GCD(b, a%b);
  }
  private static long lcm_naive(int a, int b) {
    
    return ((long) a * b)/ ((long) GCD(a, b));
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcm_naive(a, b));
  }
}
