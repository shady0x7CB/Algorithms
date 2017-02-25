package com.Algorithm;

public class RandomizedSelectiob {

	private int[] arr;
	private QuickSort quick;
	public RandomizedSelectiob(int[] arr){
		this.arr = arr;
		this.quick = new QuickSort(arr);
	}
	
	public int randomizedSelect(int l, int h, int i){
		if(l == h){
			return arr[l];
		}
		int q = quick.partition(l, h, 1);
		int k = q-l+1;
		if(k == i){
			return arr[q];
		}else if(i < k){
			return randomizedSelect(l, q-1, i);
		}else{
			return randomizedSelect(q+1, h, i-k);
		}
	}
}
