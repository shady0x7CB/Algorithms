import java.util.*;

public class Inversions {
	public static int merge(int arr[], int temp[], int left, int mid, int right)
	{
	  int i, j, k;
	  int invCount = 0;
	 
	  i = left;
	  j = mid; 
	  k = left;
	  while ((i <= mid - 1) && (j <= right))
	  {
	    if (arr[i] <= arr[j])
	    {
	      temp[k++] = arr[i++];
	    }
	    else
	    {
	      temp[k++] = arr[j++];
	      invCount = invCount + (mid - i);
	    }
	  }
	  while (i <= mid - 1)
	    temp[k++] = arr[i++];
	 
	  while (j <= right)
	    temp[k++] = arr[j++];
	 
	  for (i=left; i <= right; i++)
	    arr[i] = temp[i];
	 
	  return invCount;
	}
	
    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
    	  long inv_count = 0;
	  	  int mid;
    	  if (right > left)
	  	  {
	  	    mid = (right + left)/2;
	  	    inv_count  = getNumberOfInversions(a, b, left, mid);
	  	    inv_count += getNumberOfInversions(a, b, mid+1, right);
	  	    inv_count += merge(a, b, left, mid+1, right);
	  	  }
	  	  return inv_count;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length-1));
    }
}

