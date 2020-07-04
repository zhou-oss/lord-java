package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadFile {
	public static void readStudent(String path,ArrayList<LeavingMessage> arr) throws Exception {
		BufferedReader br=new BufferedReader(new FileReader(path));
		String line;
		while((line=br.readLine())!=null){
			String[] strl=line.split(",");
			LeavingMessage leavingMessage=new LeavingMessage();
			leavingMessage.setName(strl[0]);
			leavingMessage.setTopic(strl[1]);
			leavingMessage.setContact(strl[2]);
			arr.add(leavingMessage);
		}
		br.close();
	}
}

