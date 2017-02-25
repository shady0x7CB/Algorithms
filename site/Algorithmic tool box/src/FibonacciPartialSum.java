import java.util.*;

public class FibonacciPartialSum {
 
	
	private static long pisano(long m) {
        long f0 = 0, f1 = 1, f = f0+f1, i = 0;
        for(i = 0 ; i < m * m ; i++){
        	f = (f0+f1)%m;
        	f0 = f1;
        	f1 = f;
        	if(f0 == 0 && f1 == 1){
        		break;
        	}
        }
        return i+1;
    }
	private static long getFibonacciHugeNaive(long n, long m) {
    	n = n%pisano(m);
    	if (n <= 1)
            {return n;}
        
        
        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current)%m;
        }
        return current % m;
    }
	private static long getFibonacciSumNaive(long n) {
        n += 2;
        return (getFibonacciHugeNaive(n, 10000)  - 1);
    }
	private static long getFibonacciPartialSumNaive(long from, long to) {
		if(from == to){
			return getFibonacciHugeNaive(from, 10);
		}
		return (getFibonacciSumNaive(to) - getFibonacciSumNaive(from-1))%10;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSumNaive(from, to));
    }
}

