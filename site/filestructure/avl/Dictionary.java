package eg.edu.alexu.csd.filestructure.avl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dictionary implements IDictionary{

	private IAVLTree<String> avlTree = new AVLTree<String>();
	private Scanner scan;
	private int size = 0;
	
	
	@Override
	public void load(File file) {
		// TODO Auto-generated method stub
		try {
			scan = new Scanner(file);
			while(scan.hasNextLine()){
				insert(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(String word) {
		if(exists(word)){
			return false;
		}
		avlTree.insert(word);
		size++;
		return true;
	}

	@Override
	public boolean exists(String word) {
		// TODO Auto-generated method stub
		return avlTree.search(word);
	}

	@Override
	public boolean delete(String word) {
		if(exists(word) == false){
			return false;
		}
		avlTree.delete(word);
		size--;
		return true;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return avlTree.height();
	}

}
