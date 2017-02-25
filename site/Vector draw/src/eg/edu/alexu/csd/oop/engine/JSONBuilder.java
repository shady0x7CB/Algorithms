package eg.edu.alexu.csd.oop.engine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import eg.edu.alexu.csd.oop.draw.Shape;

public class JSONBuilder {
	
	public void buildJSON(Shape[] shapes, String path){
		try {
			File jsonFile = new File(path);
			if (!jsonFile.exists()) {
				jsonFile.createNewFile();
			}
			FileWriter fw = new FileWriter(jsonFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("{\"shapes\": {\n");
			if(shapes!=null){
				bw.write("\t\"number of shapes\": "+shapes.length+",\n");
				for(int i=0; i<shapes.length; i++){
					bw.write("\t\""+shapes[i].getClass().getName()+"\": {\n");
					bw.write("\t\t\"properties\": {\n");
					if(shapes[i].getProperties()==null){
						bw.write("\t\t\t\"null\"\n");
					}
					else {
						bw.write("\t\t\t\"notNull\"\n");
						int size = shapes[i].getProperties().keySet().size();
						int counter = 0;
						for(String key : shapes[i].getProperties().keySet()){
							bw.write("\t\t\t\""+key+"\": "+shapes[i].getProperties().get(key));
							if(counter<size-1) bw.write(",");
							bw.write("\n");
							counter++;
						}
					}
					bw.write("\t\t},\n");
					bw.write("\t\t\"position\": {\n");
					if(shapes[i].getPosition()==null){
						bw.write("\t\t\t\"xValue\": null,\n");
						bw.write("\t\t\t\"yValue\": null\n");
					}
					else {
						bw.write("\t\t\t\"xValue\": "+shapes[i].getPosition().x+",\n");
						bw.write("\t\t\t\"yValue\": "+shapes[i].getPosition().y+"\n");
					}
					bw.write("\t\t},\n");
					if(shapes[i].getColor()==null)
						bw.write("\t\t\"setColor\": null,\n");
					else
						bw.write("\t\t\"setColor\": "+shapes[i].getColor().getRGB()+",\n");
					if(shapes[i].getFillColor()==null)
						bw.write("\t\t\"fillColor\": null\n\t}");
					else	
						bw.write("\t\t\"fillColor\": "+shapes[i].getFillColor().getRGB()+"\n\t}");
					if(i<shapes.length-1) bw.write(",\n");
				}
			}
			bw.write("\n}}");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}