package util;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import dao.LoginDao;
import dao.RegisterDao;
import dao.userDataDao;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.*;

public class ExcelTest {
	Workbook workbook = null;
	Sheet sheet = null;
	WritableWorkbook workbook1 = null;
	ResultSet rs=null;
	ResultSet rs1=null;
	ResultSet rs2=null;
	LoginDao dao = new LoginDao();
	RegisterDao dao1 = new RegisterDao();
	userDataDao   dao2=new userDataDao();

	public void importExcel() {
		try {
			// Excel�ļ�
			File file = new File("file/2.xls");
			// ����workbook
			workbook = Workbook.getWorkbook(file);
			// ��ȡsheetҳ
			sheet = workbook.getSheet(0);
			// ��ȡsheet����
			int rows = sheet.getRows();
			// ����sheet����ȡһ��һ�����ݣ��洢��pojo������
			String user = null;
			String passward = null;
			String sex=null;
			String name=null;
			String livel=null;
			String sum=null;
			String score=null;
			String logtime=null;
			String sql = null;
			String sql1 = null;
			String sql2 = null;
			for (int i = 1; i < rows; i++) {
				// ֱ�ӻ�ȡ��Ԫ��
				Cell userCell = sheet.getCell(0, i);
				Cell passwardCell = sheet.getCell(1, i);
				Cell sexCell = sheet.getCell(2, i);
				Cell nameCell = sheet.getCell(3, i);
				Cell livelCell = sheet.getCell(4, i);
				Cell sumCell = sheet.getCell(5, i);
				Cell scoreCell = sheet.getCell(6, i);
				Cell logtimeCell = sheet.getCell(7, i);
				// ��ȡ��Ԫ���е�����
				user = userCell.getContents();
				passward = passwardCell.getContents();
				sex = sexCell.getContents();
				name = nameCell.getContents();
				livel = livelCell.getContents();
				sum = sumCell.getContents();
				score = scoreCell.getContents();
				logtime=logtimeCell.getContents();
				sql = "insert into login values(?,?)";
				sql1 = "insert into User values(?,?,?,?,?)";
				sql2 = "insert into user_game valu"
						+ "es(?,?,?,?,?)";
				dao.saveUserInfo(sql, user, passward);
				dao1.saveUserInfo(sql1, user, passward,name,sex,logtime);
				dao2.saveUserInfo(sql2, user, name,sum,livel,score);
			}
			// ����ȡ�������ݴ������ݿ�
		} catch (Exception e) {
		} finally {
			JOptionPane.showMessageDialog(null,"����ɹ�");
			workbook.close();
		}
	}
	public void exportExcel() {
		try {
			// �������ļ�
			File file = new File("file/1.xls");
			// ����workbook
			workbook1 = Workbook.createWorkbook(file);
			// ����sheet
			WritableSheet sheet= workbook1.createSheet("�û���Ϣ", 0);
			// ��ѯ���ݿ������
			rs=dao.queryUserInfo("select * from login");
			
			Label lab=new Label(0,0,"�˺�");
			Label lab1=new Label(1,0,"����");
			
			Label user=null;
			Label password=null;
			
			sheet.addCell(lab);
			sheet.addCell(lab1);
			
			int row=1;
			// д������
			while(rs.next()) {
				user=new Label(0,row,rs.getString(1));
				password=new Label(1,row,rs.getString(2));
				sheet.addCell(user);
				sheet.addCell(password);
				row++;
			}
			workbook1.write();
			JOptionPane.showMessageDialog(null, "�����ɹ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "����ʧ��");
			e.printStackTrace();
		}finally {
			try {
				workbook1.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void exportExcel1() {
		try {
			// �������ļ�
			File file = new File("file/3.xls");
			// ����workbook
			workbook1 = Workbook.createWorkbook(file);
			// ����sheet
			WritableSheet sheet= workbook1.createSheet("�û���Ϣ", 0);
			// ��ѯ���ݿ������
			rs=dao1.queryUserInfo("select * from user");
			
			Label lab=new Label(0,0,"�˺�");
			Label lab1=new Label(1,0,"����");
			Label lab2=new Label(2,0,"�ǳ�");
			Label lab3=new Label(3,0,"�Ա�");
			Label lab4=new Label(4,0,"ע��ʱ��");
			
			Label user=null;
			Label password=null;
			Label name=null;
			Label sex=null;
			Label logtime=null;
			
			sheet.addCell(lab);
			sheet.addCell(lab1);
			sheet.addCell(lab2);
			sheet.addCell(lab3);
			sheet.addCell(lab4);
			
			int row=1;
			System.out.println(1);
			while(rs.next()) {
				user=new Label(0,row,rs.getString(1));
				password=new Label(1,row,rs.getString(2));
				name=new Label(2,row,rs.getString(3));
				sex=new Label(3,row,rs.getString(4));
				logtime=new Label(4,row,rs.getString(5));
				sheet.addCell(user);
				sheet.addCell(password);
				sheet.addCell(name);
				sheet.addCell(sex);
				sheet.addCell(logtime);
				row++;
			}
			workbook1.write();
			JOptionPane.showMessageDialog(null, "�����ɹ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "����ʧ��");
			e.printStackTrace();
		}finally {
			try {
				workbook1.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void exportExcel2() {
		try {
			// �������ļ�
			File file = new File("file/4.xls");
			// ����workbook
			workbook1 = Workbook.createWorkbook(file);
			// ����sheet
			WritableSheet sheet= workbook1.createSheet("�û���Ϣ", 0);
			// ��ѯ���ݿ������
			rs=dao.queryUserInfo("select * from user_game");
			
			Label lab=new Label(0,0,"�˺�");
			Label lab1=new Label(1,0,"�ǳ�");
			Label lab2=new Label(2,0,"�ܳ���");
			Label lab3=new Label(3,0,"ʤ��");
			Label lab4=new Label(4,0,"����");
			
			Label user=null;
			Label name=null;
			Label sum=null;
			Label livel=null;
			Label score=null;
			
			sheet.addCell(lab);
			sheet.addCell(lab1);
			sheet.addCell(lab2);
			sheet.addCell(lab3);
			sheet.addCell(lab4);
			
			int row=1;
			while(rs.next()) {
				user=new Label(0,row,rs.getString(1));
				name=new Label(1,row,rs.getString(2));
				sum=new Label(2,row,rs.getString(3));
				livel=new Label(3,row,rs.getString(4));
				score=new Label(4,row,rs.getString(5));
				sheet.addCell(user);
				sheet.addCell(name);
				sheet.addCell(livel);
				sheet.addCell(sum);
				sheet.addCell(score);
				row++;
			}
			
			workbook1.write();
			JOptionPane.showMessageDialog(null, "�����ɹ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "����ʧ��");
			e.printStackTrace();
		}finally {
			try {
				workbook1.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new ExcelTest().importExcel();
	}
}
