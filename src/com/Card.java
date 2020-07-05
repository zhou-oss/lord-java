package com;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * ������.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class Card extends JLabel implements MouseListener{
	
	/** The frame. */
	JFrame frame;
	
	/** The panel. */
	JPanel panel;//��ǰ�����������
	
	/** The name. */
	String name;
	
	/** The up. */
	boolean up;//�Ƿ�������
	
	/** The can click. */
	boolean canClick=false;//�Ƿ�ɱ����
	
	/** The clicked. */
	boolean clicked=false;//�Ƿ�����
	
	/**
	 * ��ʼ������.
	 *
	 * @param ����
	 * @param ��������
	 * @param ��Ƭ״̬
	 */
	public Card(JFrame frame,String name,boolean up){
		this.frame=frame;
		this.name=name;
		this.up=up;
	    if(this.up)
	    	this.turnFront();
	    else {
			this.turnRear();
		}
		this.setSize(71, 106);
		this.setVisible(true);
		this.addMouseListener(this);
	}
	
	/**
	 * ������.
	 */
	//����
	public void turnFront() {
		this.setIcon(new ImageIcon("pics/cards1/"+name+".gif"));
		this.up = true;
	}
	
	/**
	 * �򿪷���.
	 */
	//����
	public void turnRear() {
		this.setIcon(new ImageIcon("pics/cards1/rear.gif"));
		this.up = false;
	}
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	    */
	    
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param arg0
	    * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	    */
	    
	public void mouseEntered(MouseEvent arg0) {}
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param arg0
	    * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	    */
	    
	public void mouseExited(MouseEvent arg0) {}
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	    */
	    
	public void mousePressed(MouseEvent e) {
		if(canClick)
		{
			Point from=this.getLocation();
			int step; //�ƶ��ľ���
			if(clicked)
				step=-20;
			else {
				step=20;
			}
			clicked=!clicked; //����
			Point to=new Point(from.x,from.y-step);
			//����ѡ�е�ʱ����ǰ�ƶ�һ��/����һ��
			Remove.move(this,from,to);
		}
	}
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param arg0
	    * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	    */
	    
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
