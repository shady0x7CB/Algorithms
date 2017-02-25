package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class Sort<T extends Comparable<T>> implements ISort<T> {

	@Override
	public IHeap<T> heapSort(ArrayList<T> unordered) {

		Heap<T> heap = new Heap<T>();
		heap.build(unordered);
		for (int i = heap.size(); i > 1; i--) {
			heap.swap(heap.getRoot(), heap.getNodeByIndex(i - 1));
			heap.updateSize(i - 1);
			heap.heapify(heap.getRoot());
		}
		heap.updateSize(unordered.size());
		return heap;
	}

	@Override
	public void sortSlow(ArrayList<T> unordered) {

		boolean swapFlag = true;
		int i, j;
		for (i = 0; i < unordered.size() && swapFlag; i++) {
			swapFlag = false;
			for (j = 0; j < unordered.size() - 1 - i; j++)
				if (unordered.get(j).compareTo(unordered.get(j + 1)) > 0) {
					T temp = unordered.get(j);
					unordered.set(j, unordered.get(j + 1));
					unordered.set(j + 1, temp);
					swapFlag = true;
				}
		}
	}

	@Override
	public void sortFast(ArrayList<T> unordered) {

		mergeSort(unordered, 0, unordered.size() - 1);
	}

	private void merge(ArrayList<T> unordered, int low, int mid, int high) {

		ArrayList<T> temp = new ArrayList<T>();
		temp = unordered;
		int i = low, l = low, r = mid + 1;
		while ((l <= mid) && (r <= high)) {
			if (unordered.get(l).compareTo(unordered.get(r)) <= 0) {
				temp.set(i, unordered.get(l));
				l++;
			} else {
				temp.set(i, unordered.get(r));
				r++;
			}
			i++;
		}
		if (l > mid) {
			for (int k = r; k <= high; k++) {
				temp.set(i, unordered.get(k));
				i++;
			}
		} else {
			for (int k = l; k <= mid; k++) {
				temp.set(i, unordered.get(k));
				i++;
			}
		}
		for (int k = low; k <= high; k++) {
			unordered.set(k, temp.get(k));
		}
	}
	private void mergeSort(ArrayList<T> unordered, int low, int high) {

		int mid;
		if (low < high) {
			mid = (low + high) / 2;
			mergeSort(unordered, low, mid);
			mergeSort(unordered, mid + 1, high);
			merge(unordered, low, mid, high);
		}
	}

	
}