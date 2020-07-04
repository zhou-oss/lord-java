package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFile {
	public static void writeData(String path,ArrayList<LeavingMessage> arr) throws IOException{
		BufferedWriter bw=new BufferedWriter(new FileWriter(path));
		for(int x=0;x<arr.size();x++){
			LeavingMessage strl=arr.get(x);
			StringBuilder sb=new StringBuilder();
			sb.append(strl.getName()+","+strl.getTopic()+","+strl.getContact()+",");
			bw.write(sb.toString());
			bw.newLine();
			bw.flush();
		}
		bw.close();
	}
}