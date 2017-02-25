package com.Algorithm;

import java.util.Random;

public class QuickSort {

	int[] arr;
	Random rand = new Random();
	long counter;
	long counter1;
	public QuickSort(int[] arr){
		this.arr = arr;
		counter = 0;
		counter1 = 0;
	}
	
	public void quickSort(int l, int h){
		if(l < h){
			int pivot = partition(l, h, 0);
			
			quickSort(l, pivot-1);
			quickSort(pivot+1, h);
		}
	}
	public int partition(int l, int h, int p){
//		counter += h-l;
//		System.out.println("**********************");
//		for(int i = l ; i <= h ; i++){
//			System.out.println(arr[i]);
//		}
//		System.out.println("**********************");
	    /*int len = h-l+1;
	    int mid = 0;
		if(len % 2 != 0) mid = l + ((len-1)/2);
	    else mid = l + ((len/2) - 1);
		int first = 0;
		int middle = 0;
		int last = 0;
		int pivot = 0;
		if(arr[l] > arr[mid]){
			last = l;
			first = mid;
		}else{
			last = mid;
			first = l;
		}
		if(arr[h] > arr[last]){
			middle = last;
		}else{
			if(arr[h] < arr[first]){
				middle = first;
			}
			else{
				middle = h;
			}
		}
		pivot = arr[middle];
		exchange(l, middle);
		System.out.println("first is " + first);
		System.out.println("second is " + middle);
		System.out.println("third is " + last);
		System.out.println("pivot is " + pivot);*/
		//int random = l + rand.nextInt(h-l+1);
		int pivot = p;//arr[random];
		exchange(l, p/*random*/);
		int c = l;
		for(int i = l+1 ; i <= h ; i++){
			if(arr[i] <= pivot){
				c+=1;
				exchange(i, c);
			}
		}
		exchange(c, l);
		return c;
	}
	
	public long getCounter(){
		return counter;
	}
	public void exchange(int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}
