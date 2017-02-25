package eg.edu.alexu.csd.oop.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.shapes.Circle;
import eg.edu.alexu.csd.oop.shapes.Ellipse;
import eg.edu.alexu.csd.oop.shapes.Line;
import eg.edu.alexu.csd.oop.shapes.Rectangle;
import eg.edu.alexu.csd.oop.shapes.Square;
import eg.edu.alexu.csd.oop.shapes.Triangle;

public class ClassFinder {
	
	public List<Class<? extends Shape>> find(){
		String classPath = System.getProperty("java.class.path");
		String[] paths = classPath.split(System.getProperty("path.separator"));
		List<Class<? extends Shape>> list = new LinkedList<Class<? extends Shape>>();
		list.add(Circle.class);
		list.add(Ellipse.class);
		for(String path : paths){
			if(detectJarFile(path)){
				try {
					JarFile jarFile = new JarFile(path);
					list = jarFileIterator(jarFile, true, null, list);
				    jarFile.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		return findJars(list);
	}
	private List<Class<? extends Shape>> findJars(List<Class<? extends Shape>> list){
		File file = new File(System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"shady.txt");
		if(file.exists())
			try {
				Scanner reader = new Scanner(file);
				while(reader.hasNextLine()){
					String pathToJar = reader.nextLine();
					JarFile jarFile = new JarFile(pathToJar);
					list = jarFileIterator(jarFile, false, pathToJar, list);
				    jarFile.close();
				}
				reader.close();
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		return list;
	}
	@SuppressWarnings({ "unchecked" })
	private  List<Class<? extends Shape>> jarFileIterator(JarFile file, boolean check, String pathToJar, List<Class<? extends Shape>> list){
		try{
			URLClassLoader cl = null;
			if(check == false){
				URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
				cl = URLClassLoader.newInstance(urls);
			}
			Enumeration<?> e = file.entries();
		    while (e.hasMoreElements()) {
		        JarEntry je = (JarEntry) e.nextElement();
		        if(je.isDirectory() || !je.getName().endsWith(".class")){
		            continue;
		        }
		        String className = je.getName().substring(0,je.getName().length()-6);
		    	className = className.replace('/', '.');
		    	Class<?> c;
		    	if(check == true){
		    		c = Class.forName(className);
		    	}else{
		    		c = cl.loadClass(className);
		    	}
		    	if(Shape.class.isAssignableFrom(c) && !c.isInterface() && !Modifier.isAbstract(c.getModifiers())){
			    	list.add((Class<? extends Shape>) c);
		    	}
		    }
		}catch(Exception e){}
		return list;
	}
	
	private boolean detectJarFile(String file){
	      try {
	        JarFile jarFile = new JarFile(file);
	        jarFile.close();
	      } catch (java.util.zip.ZipException e) {
	        return false;
	      } catch (IOException e) {
			
	    	  return false;
		  } 
	      return true;
	    }
}