package com;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Card extends JLabel implements MouseListener{
	JFrame frame;
	JPanel panel;//��ǰ�����������
	String name;
	boolean up;//�Ƿ�������
	boolean canClick=false;//�Ƿ�ɱ����
	boolean clicked=false;//�Ƿ�����
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
	//����
	public void turnFront() {
		this.setIcon(new ImageIcon("pics/cards1/"+name+".gif"));
		this.up = true;
	}
	//����
	public void turnRear() {
		this.setIcon(new ImageIcon("pics/cards/cardbg.png"));
		this.up = false;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
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
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
