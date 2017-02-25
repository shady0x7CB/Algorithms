import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        int ans = 1;
        for (int number = n ; number > 0; ans++) {
            if (number <= 2 * ans) {
                summands.add(number);
                number -= number;
            } else {
                summands.add(ans);
                number -= ans;
            }
        }
        return summands;
    }
    
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

