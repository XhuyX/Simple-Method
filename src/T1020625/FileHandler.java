package T1020625;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class FileHandler {
	public static ArrayList <String>  readAll(String src) throws IOException {
		ArrayList <String> lines = new ArrayList <>();
		BufferedReader br = new BufferedReader (new FileReader(src));
		String line;
		while ((line=br.readLine())!=null) {
			lines.add(line);
		}
		br.close();
		return lines;
	}
	public static void addAll(ArrayList<String> arr, String path) throws IOException {
		BufferedWriter bw = new BufferedWriter (new FileWriter (path));
		for (String line : arr) {
			bw.write(line);
			bw.newLine();
		}
		bw.close();
		
	}
}
