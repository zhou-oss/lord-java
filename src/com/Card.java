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
	JPanel panel;//当前容器类的引用
	String name;
	boolean up;//是否正反面
	boolean canClick=false;//是否可被点击
	boolean clicked=false;//是否点击过
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
	//正面
	public void turnFront() {
		this.setIcon(new ImageIcon("pics/cards1/"+name+".gif"));
		this.up = true;
	}
	//反面
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
			int step; //移动的距离
			if(clicked)
				step=-20;
			else {
				step=20;
			}
			clicked=!clicked; //反向
			Point to=new Point(from.x,from.y-step);
			//当被选中的时候，向前移动一步/后退一步
			Remove.move(this,from,to);
		}
	}
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
