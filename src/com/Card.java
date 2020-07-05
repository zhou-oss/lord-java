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
 * 卡牌类.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class Card extends JLabel implements MouseListener{
	
	/** The frame. */
	JFrame frame;
	
	/** The panel. */
	JPanel panel;//当前容器类的引用
	
	/** The name. */
	String name;
	
	/** The up. */
	boolean up;//是否正反面
	
	/** The can click. */
	boolean canClick=false;//是否可被点击
	
	/** The clicked. */
	boolean clicked=false;//是否点击过
	
	/**
	 * 初始化卡牌.
	 *
	 * @param 容器
	 * @param 卡牌名称
	 * @param 卡片状态
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
	 * 打开正面.
	 */
	//正面
	public void turnFront() {
		this.setIcon(new ImageIcon("pics/cards1/"+name+".gif"));
		this.up = true;
	}
	
	/**
	 * 打开反面.
	 */
	//反面
	public void turnRear() {
		this.setIcon(new ImageIcon("pics/cards1/rear.gif"));
		this.up = false;
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	    */
	    
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param arg0
	    * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	    */
	    
	public void mouseEntered(MouseEvent arg0) {}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param arg0
	    * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	    */
	    
	public void mouseExited(MouseEvent arg0) {}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	    */
	    
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
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param arg0
	    * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	    */
	    
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
