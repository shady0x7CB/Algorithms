package com.Algorithm;

import java.util.Arrays;

public class DeterministicSelection {

	int[] arr;
	private QuickSort quick;
	public DeterministicSelection(int[] arr){
		this.arr = arr;
		this.quick = new QuickSort(arr);
	}
	public int choosePivot(int[] arr){
		if(arr.length == 1){
			return arr[0];
		}
		int[][] a = new int[(arr.length) / 5][5];
		int[] remain = new int[arr.length%5];
		fillArr(arr, remain, 0, arr.length-1, a);
//		for(int c = 0 ; c < a.length ; c++){
//			for(int j = 0 ; j < a[0].length ; j++){
//				System.out.print(a[c][j] + " ");
//			}
//			System.out.print("\n");
//		}
//		System.out.println("remain");
//		for(int i = 0 ; i < remain.length ; i++){
//			System.out.print(remain[i] + " ");
//		}
//		System.out.print("\n");
		for(int j = 0 ; j < a.length ; j++){
			Arrays.sort(a[j]);
		}
		Arrays.sort(remain);
		int[] c = new int[(int)Math.ceil(((double)arr.length) / 5.0)];
		int j = 0;
		
		for(j = 0 ; j < a.length ; j++){
			c[j] = a[j][a[0].length/2];
			//System.out.print("/**/ " + c[j]);
		}
//		System.out.print("\n");
//		System.out.println("c.length" + c.length + " j " + j);
		while(j < c.length){
			c[j] = remain[remain.length/2];
			j++;
		}
//		System.out.println("c ");
//		for(int i = 0 ; i < c.length ; i++){
//			System.out.print(c[i] + " ");
//		}
//		System.out.println("");
		int mOfm = select(c, 0, c.length-1, c.length/2);
		return mOfm;
	}
	public int select(int[] arr, int l, int h, int i){
		if(h==l){
			return arr[0];
		}
		int mOfm = choosePivot(arr);
		int q = partition(arr, l, h, mOfm);
		int k = q-l+1;
		if(k == i){
			return arr[q];
		}else if(i < k){
			return select(arr, l, q-1, i);
		}else{
			return select(arr, q+1, h, i-k);
		}
	}
	public void exchange(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private int partition(int[] arr, int l, int h, int p){
		int index = 0;
		for(int w = 0; w < arr.length ; w++){
			if(arr[w] == p){
				index = w;
				break;
			}
		}
		int pivot = arr[index];
		int i = l;
		exchange(arr, l, index);
		for(int n = 0 ; n <= h ; n++){
			//System.out.print("hey" + arr[n]+ " ");
		}
		for(int j = l+1 ; j <= h ; j++){
			if(arr[j] <= pivot){
				i += 1;
				//System.out.println("lets see " + i + " " + j + " " + l + " " + h + " " + arr[i] + " " + arr[j]);
				exchange(arr, i, j);
			}
		}
		exchange(arr, l, i);
		return i;
	}
	
	public void fillArr(int[] a, int[] remain, int l, int h, int[][] b){
		int k = l;
		for(int i = 0 ; i < b.length ; i++){
			for(int j = 0 ; j < b[0].length ; j++){
				if(k <= h){
					b[i][j] = a[k++];
				}else{
					break;
				}
				
			}
		}
		for(int i = 0 ; i < remain.length ; i++){
			remain[i] = a[k++];
		}
	}
}
