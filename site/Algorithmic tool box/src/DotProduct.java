import java.util.*;


public class DotProduct {
    
	static int[] temp;
	
	private static void merge(int[] items, int l, int mid, int h){
		int i = l, j = mid+1;
		int k = l;
		for(int c = l ; c <= h ; c++){
			temp[c] = items[c];
		}
		while(i <= mid && j <= h){
			
			if(temp[j]> temp[i]){
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
	private static void mergeSort(int[] items, int l, int h){
		if(l < h){
			int mid = (l+h)/2;
			mergeSort(items, l, mid);
			mergeSort(items, mid+1, h);
			merge(items, l, mid, h);
		}
	}
	private static long maxDotProduct(int[] a, int[] b) {
        
		mergeSort(a, 0, a.length-1);
		mergeSort(b, 0, b.length-1);
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }
	

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        
        
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        temp = new int[a.length];
        System.out.println(maxDotProduct(a, b));
    }
}

