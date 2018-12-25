package kr.ac.cbnu.computerengineering.lecture1;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataManager {
	public void saveData(PressDatatype data,String filename) {
		FileWriter my_stream = null;
		try {
			File my_file = new File(filename);
			my_stream = new FileWriter(my_file,true);
			BufferedWriter out_stream = new BufferedWriter(my_stream,1024);
			out_stream.write(data.getdate().toString()+'\t'+data.gettitle()+'\t'+data.getcontents()+System.lineSeparator());
			out_stream.close();
			my_stream.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
