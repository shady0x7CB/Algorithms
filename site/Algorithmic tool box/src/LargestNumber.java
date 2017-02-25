import java.util.*;

public class LargestNumber {
    
	static String[] temp;
	private static boolean compare(String a, String b){
	    String ab = a.concat(b);
	    String ba = b.concat(a);
	    return ab.compareTo(ba) > 0 ? true: false;
	}
	private static void merge(String[] items, int l, int mid, int h){
		int i = l, j = mid+1;
		int k = l;
		for(int c = l ; c <= h ; c++){
			temp[c] = items[c];
		}
		while(i <= mid && j <= h){
			
			if(compare(temp[i], temp[j])){
				items[k++] = temp[i++];
			}else{
				items[k++] = temp[j++];
			}
			
		}
		while(i <= mid){
			items[k++] = temp[i++];
		}
		while(j <= h){
			items[k++] = temp[j++];
		}
		
	}
	private static void mergeSort(String[] items, int l, int h){
		if(l < h){
			int mid = (l+h)/2;
			mergeSort(items, l, mid);
			mergeSort(items, mid+1, h);
			merge(items, l, mid, h);
		}
	}
	private static String largestNumber(String[] a) {
        
		mergeSort(a, 0, a.length-1);
        String result = "";
        for (int i = 0; i < a.length; i++) {
            result += a[i];
        }
        
        return result;
    }

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        temp = new String[a.length];
        //System.out.println(compare("21", "2"));
        //mergeSort(a, 0, a.length-1);
//		for(int i = 0 ; i < a.length ; i++){
//			System.out.println(a[i]);
//		}
        System.out.println(largestNumber(a));
    }
}

