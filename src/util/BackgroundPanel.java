package util;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * �������ӱ���ͼƬ.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class BackgroundPanel extends JPanel {  

     /** The image. */
     //private static final long serialVersionUID = -6352788025440244338L;  
     private Image image=null;  
  
     /**
      * Instantiates a new background panel.
      *
      * @param ͼƬ
      */
     public BackgroundPanel(Image image) {  
        this.image = image;  
    }  
    
    
        /* (�� Javadoc)
        * 
        * 
        * @param g
        * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
        */
        
    // �̶�����ͼƬ���������JPanel������ͼƬ������������  
    protected void paintComponent(Graphics g) {  
         g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
     }  
 }  