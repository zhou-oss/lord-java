package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * 写入文件.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class WriteFile {
	
	/**
	 * 写入文件类
	 *
	 * @param 文件路径
	 * @param 留言对象集合
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void writeData(String path,ArrayList<MessageBoardClass> arr) throws IOException{
		BufferedWriter bw=new BufferedWriter(new FileWriter(path));
		for(int x=0;x<arr.size();x++){
			MessageBoardClass strl=arr.get(x);
			StringBuilder sb=new StringBuilder();
			sb.append(strl.getName()+","+strl.getTopic()+","+strl.getContact()+",");
			bw.write(sb.toString());
			bw.newLine();
			bw.flush();
		}
		bw.close();
	}
}