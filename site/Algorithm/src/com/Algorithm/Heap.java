package com.Algorithm;

public class Heap {

	private int[] arr;
	private int size;
	public Heap(int[] arr){
		this.arr = arr;
		size = 0;
	}
	
	public int left(int index){
		return 2 * index + 1;
	}
	public int right(int index){
		return 2 * index +2;
	}
	public int parent(int index){
		return (index-1)/2;
	}
	public void maxHeapify(int index){
		int left = left(index);
		int right = right(index);
		int largest = index;
		if(left < this.size && arr[left] > arr[index]){
			largest = left;
		}
		if(right < this.size && arr[right] > arr[largest]){
			largest = right;
		}
		if(largest!=index){
			swap(index, largest);
			maxHeapify(largest);
		}
	}
	public void buildMaxHeap(){
		this.size = arr.length;
		for(int i = arr.length-1/2 ; i >=0 ; i--){
			maxHeapify(i);
		}
	}
	public void heapSort(){
		buildMaxHeap();
		for(int i = arr.length-1 ; i>=1 ; i--){
			swap(0, i);
			this.size--;
			maxHeapify(0);
		}
	}
	
	public void swap(int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int[] getArr() {
		return arr;
	}
	public void setArr(int[] arr) {
		this.arr = arr;
	}
	
}
