package util;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * 给容器加背景图片.
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
      * @param 图片
      */
     public BackgroundPanel(Image image) {  
        this.image = image;  
    }  
    
    
        /* (非 Javadoc)
        * 
        * 
        * @param g
        * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
        */
        
    // 固定背景图片，允许这个JPanel可以在图片上添加其他组件  
    protected void paintComponent(Graphics g) {  
         g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
     }  
 }  