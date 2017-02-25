import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class MaxPairwiseProduct {
    static BigInteger getMaxPairwiseProduct(BigInteger[] numbers) {
        BigInteger result = new BigInteger("0");
        int n = numbers.length;
        BigInteger max1 = new BigInteger("-1");
        BigInteger max2 = new BigInteger("-1");
        int j = 0;
        for(int i = 0 ; i < n ; i++){
        	if(numbers[i].compareTo(max1) > 0){
        		max1 = numbers[i];
        		j = i;
        	}
        }
        for(int i = 0 ; i < n ; i++){
        	if(numbers[i].compareTo(max2) > 0 && i != j){
        		max2 = numbers[i];
        	}
        }
        result = max1.multiply(max2);
        
        return result;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        BigInteger[] number = new BigInteger[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
            number[i] = new BigInteger("0");
            number[i] = BigInteger.valueOf(numbers[i]);
        }
        System.out.println(getMaxPairwiseProduct(number));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}