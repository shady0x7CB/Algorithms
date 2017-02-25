import java.util.*;

public class CoveringSegments {

	static Segment[] temp;
	private static boolean compare(Segment a, Segment b){
	    
	    return a.end-b.end < 0 ? true: false;
	}
	private static void merge(Segment[] items, int l, int mid, int h){
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
	private static void mergeSort(Segment[] items, int l, int h){
		if(l < h){
			int mid = (l+h)/2;
			mergeSort(items, l, mid);
			mergeSort(items, mid+1, h);
			merge(items, l, mid, h);
		}
	}
    private static int[] optimalPoints(Segment[] segments) {
        temp = new Segment[segments.length];
    	for(int i = 0 ; i < segments.length ; i++){
    		temp[i] = new Segment(0, 0);
    	}
    	mergeSort(segments, 0, segments.length-1);
    	LinkedList<Integer> points1 = new LinkedList<Integer>();
    	int point = segments[0].end;
        points1.add(point);
        for (int i = 1; i < segments.length; ++i) {
            if (point < segments[i].start || point > segments[i].end) {
            	//System.out.println("ll " + point + ", " + segments[i].start + ", " + segments[i].end);
                point = segments[i].end;
                points1.add(point);
            }
        }
        
        int[] points= new int[points1.size()];
        for(int i = 0 ; i < points1.size() ; i++){
        	points[i] = points1.get(i);
        }
        return points;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
