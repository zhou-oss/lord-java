package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadFile.
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class ReadFile {
	
	/**
	 * 读取文件内容
	 *
	 * @param 文件路径
	 * @param 留言集合
	 * @throws Exception the exception
	 */
	public static void readStudent(String path,ArrayList<MessageBoardClass> arr) throws Exception {
		BufferedReader br=new BufferedReader(new FileReader(path));
		String line;
		while((line=br.readLine())!=null){
			String[] strl=line.split(",");
			MessageBoardClass leavingMessage=new MessageBoardClass();
			leavingMessage.setName(strl[0]);
			leavingMessage.setTopic(strl[1]);
			leavingMessage.setContact(strl[2]);
			arr.add(leavingMessage);
		}
		br.close();
	}
}

