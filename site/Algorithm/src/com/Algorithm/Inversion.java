package com.Algorithm;

public class Inversion {

	int[] a ;
	int[] he;
	long counter ;
	public Inversion(int[] a){
		this.a = a;
		he = new int[a.length];
		counter = 0;
	}
	public void ineversionCounter(int l, int h){
		if(l < h){
			int mid = (l + h)/2;
			ineversionCounter(l, mid);
			ineversionCounter(mid+1, h);
			counter(l, mid, h);
		}
	}
	public void counter(int l, int mid, int h){
		for(int v = l ; v <= h ; v++){
			he[v] = a[v];
		}
		int i = l, j = mid+1;
		int k = l;
		while(i <= mid && j <= h){
			if(he[i] <= he[j]){
				a[k++] = he[i++];
			}else{
				a[k++] = he[j++];
				counter += mid-i+1;
			}
		}
		while(i <= mid){
			a[k++] = he[i++];
		}
		while(j <= h){
			a[k++] = he[j++];
		}
		
	}
	public long getCounter(){
		return this.counter;
	}
}
