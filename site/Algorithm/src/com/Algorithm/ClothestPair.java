package com.Algorithm;

import java.awt.Point;
import java.util.LinkedList;

public class ClothestPair {

	Point[] points;
	Point[] tempMergArr;
	public ClothestPair(Point[] points){
		this.points = points;
		tempMergArr = new Point[points.length];
	}
	public void mergeSort(int low, int high, char xOry){
		if(low < high){
			int mid = (low + high)/2;
			mergeSort(low, mid, xOry);
			mergeSort(mid+1, high, xOry);
			merge(low, mid, high, xOry);
		}
	}
	public void merge(int lowerIndex, int middle, int higherIndex, char xOry){
		for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = points[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if(xOry == 'x'){
            	if (tempMergArr[i].getX() <= tempMergArr[j].getX()) {
                    points[k] = tempMergArr[i];
                    i++;
                } else {
                    points[k] = tempMergArr[j];
                    j++;
                }
            }else{
            	if (tempMergArr[i].getY() <= tempMergArr[j].getY()) {
                    points[k] = tempMergArr[i];
                    i++;
                } else {
                    points[k] = tempMergArr[j];
                    j++;
                }
            }
            k++;
        }
        while (i <= middle) {
            points[k] = tempMergArr[i];
            k++;
            i++;
        }
	}
	public double d(Point[] points){
		return Math.sqrt(Math.pow((points[0].getX() - points[1].getX()), 2) 
				+ Math.pow((points[0].getY() - points[1].getY()), 2));
	}
	public Point[] clothestPair(Point[] px, Point[] py){
		if(px.length <= 3){
			Point[] best1 = new Point[2];
			Point[] p = new Point[2];
			p[0] = new Point(px[0]);
			p[1] = new Point(px[1]);
			double min = d(p);
			best1 = p;
			for(int i = 0 ; i < px.length ; i++){
				for(int j = i +1 ; j < px.length; j++){
					p[0] = new Point(px[i]);
					p[1] = new Point(px[j]);
					if(d(p) < min){
						min = d(p);
						best1 = p;
					}
				}
			}
			return best1;
		}
		Point[] qx = new Point[px.length/2];
		for(int i = 0 ; i < qx.length ; i++){
			qx[i] = px[i];
		}
		Point[] rx = new Point[px.length - qx.length];
		for(int i = 0 ; i < rx.length ; i++){
			rx[i] = px[qx.length+i];
		}
		Point[] qy = new Point[py.length/2];
		for(int i = 0 ; i < qy.length ; i++){
			qy[i] = py[i];
		}
		Point[] ry = new Point[py.length - qy.length];
		for(int i = 0 ; i < ry.length ; i++){
			ry[i] = py[qy.length + i];
		}
		
		Point[] point1 = clothestPair(qx, qy);
		Point[] point2 = clothestPair(rx, ry);
		System.out.println("point1 is");
		for(int i = 0 ; i < point1.length ; i++){
			System.out.println(point1[i]);
		}
		System.out.println("point2 is");
		for(int i = 0 ; i < point2.length ; i++){
			System.out.println(point2[i]);
		}
		double delta = Math.min(d(point1), d(point2));
		Point[] point3 = closestSplitPair(px, py, delta);
		System.out.println("point3 is");
		for(int i = 0 ; i < point3.length ; i++){
			System.out.println(point3[i]);
		}
		return best(point1, point2, point3);
	}
	public Point[] closestSplitPair(Point[] px, Point[] py, double delta){
		double xDash = px[px.length/2-1].getX();
		LinkedList<Point> s = new LinkedList<Point>();
		for(int i = 0 ; i < py.length ; i++){
			if(py[i].getX() <= xDash + delta && py[i].getX() >= xDash-delta){
				s.add(new Point(py[i]));
			}
		}
		Point[] sy = new Point[s.size()];
		for(int i = 0 ; i < s.size() ; i++){
			sy[i] = s.get(i);
		}
		double dest = delta;
		Point[] bestPair = new Point[2];
//		System.out.println(s);
//		System.out.println(xDash);
//		System.out.println(delta);
		for(int i = 0 ; i < sy.length ; i++){
			for(int j = 1 ; j < Math.min(8, sy.length-i) ; j++){
				Point[] point = new Point[2];
				point[0] = new Point(sy[i]);
				point[1] = new Point(sy[i+j]);
//				System.out.println("sss is ");
//				for(int v = 0 ; v < point.length ; v++){
//					System.out.println(point[v]);
//				}
				if(d(point) < dest){
					bestPair = point;
					dest = d(point);
				}
			}
		}
//		System.out.println("///////");
//		for(int i = 0 ; i < bestPair.length ; i++){
//			System.out.println(bestPair[i]);
//		}
//		System.out.println("///////");
		return bestPair;
	}
	public Point[] best(Point[] point1, Point[] point2, Point[] point3){
		if(d(point1) == Math.min(Math.min(d(point1), d(point2)), d(point3))){
			return point1;
		}else if(d(point2) == Math.min(Math.min(d(point1), d(point2)), d(point3))){
			return point2;
		}else{
			return point3;
		}
		
	}
	
}
