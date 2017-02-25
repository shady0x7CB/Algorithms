
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class FractionalKnapsack {
	

	static Item[] items;
	static Item[] temp; 

	
	private static boolean compare(Item a, Item b){
		double r1 = (double)a.value/(double)a.weight;
		double r2 = (double)b.value/(double)b.weight;
		return r1 > r2;
	}
	private static void merge(int l, int mid, int h){
		int i = l, j = mid+1;
		int k = l;
		for(int c = l ; c <= h ; c++){
			temp[c] = items[c];
		}
		while(i <= mid && j <= h){
			
			if(compare(temp[i], temp[j])){
				items[k++] = temp[j++];
			}else{
				items[k++] = temp[i++];
			}
			
		}
		while(i <= mid){
			items[k++] = temp[i++];
		}
		while(j <= h){
			items[k++] = temp[j++];
		}
		
	}
	private static void mergeSort(int l, int h){
		if(l < h){
			int mid = (l+h)/2;
			mergeSort(l, mid);
			mergeSort(mid+1, h);
			merge(l, mid, h);
		}
	}
	private static double getOptimalValue(int capacity, int[] values, int[] weights) {
		
		
		for(int i = 0 ; i < items.length ; i++){
        	Item item = new Item(weights[i], values[i]);
        	items[i] = item;
        	//System.out.println(items[i].weight);
        }
        mergeSort(0, items.length-1);
//        for(int i = 0 ; i < items.length ; i++){
//        	System.out.println(items[i].weight + ", " + items[i].value);
//        }
        
        double value = 0;
    	
    	for(int i = weights.length-1 ; i >= 0  ; i--){
    		if(capacity > 0){
    			value += Math.min(items[i].weight, capacity) * (double)((double)items[i].value / (double)items[i].weight);
    			capacity -= Math.min(items[i].weight, capacity);
    		}
    	}
        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        items = new Item[n];
        temp = new Item[n];
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
class Item {
	
	public int weight, value;
	public Item(int weight, int value){
		this.weight = weight;
		this.value = value;
	}
}