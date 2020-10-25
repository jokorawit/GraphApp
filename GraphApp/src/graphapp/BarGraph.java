
package graphapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class BarGraph {

    protected BarGraphModel model;//คลาสหรือสมาชิกสามารถเข้าถึงได้ภายใน package เดียวกันและ sub class ของมัน
    
    protected static final Font BAR_TITLE_FONT = new Font("Calibri", Font.PLAIN, 12);//เรียกใช้ Method นั้นโดยไม่ต้องสร้าง Object ของ Class ขึ้นมา เป็นfont
    
    public BarGraph() {//ประกาศ constructor มีการกำหนดค่าเรื่มต้นให้แตะละ item เพื่อที่จะนำมาแสดงเป็นตัวกราฟแท่ง
        this.model = new BarGraphModel();
        
        
        model.setLocation(20, 50);
        model.setSize(400, 300);
        
        BarGraphModel.BarItem item = new BarGraphModel.BarItem("Lisa");
        item.percentage = 60;//กำหนดค่าให้เท่า 60
        model.addItem(item);
        
        item = new BarGraphModel.BarItem("Johnny");
        item.percentage = 90;//กำหนดค่าให้เท่า 90
        model.addItem(item);
        
        
        item = new BarGraphModel.BarItem("Linda");
        item.percentage = 50;//กำหนดค่าให้เท่า 50
        model.addItem(item);
        
        item = new BarGraphModel.BarItem("David");
        item.percentage = 65;//กำหนดค่าให้เท่า 65
        model.addItem(item);
        
        item = new BarGraphModel.BarItem("Guido");
        item.percentage = 70;//กำหนดค่าให้เท่า 70
        model.addItem(item);
        
     
    }
    
    public BarGraphModel getModel() {
        return model;
    }

    public void setModel(BarGraphModel model) {
        this.model = model;
    }
    
    public void draw(Graphics g){//เป็น Method แสดงกราฟฟิกของ กราฟแท่งขึ้นมาทั้ง 5 แท่ง
        drawItems(g);
        drawBoarder(g);
    }
    
    private void drawItems(Graphics g){//เป็น Method การทำงานของกราฟแต่ละแท่ง มีการกำหนดค่าให้ต่างๆ
        int i = 0;
        g.setFont(BAR_TITLE_FONT);
        FontMetrics fm = g.getFontMetrics(BAR_TITLE_FONT);
        
        for (BarGraphModel.BarItem item : model.items){//เข้า for เพื่อกำหนดค่าให้กราฟแต่ละแท่ง
            
            int percentHeight = ((int)((double)item.percentage/100 * model.getSize().height));//กำหนดให้ 1 ในกราฟแท่งมีค่าภายในแค่ 100 ตามความสูงในกราฟ
            
            int x = model.getX() + i * model.getHorizontalGap();//เรียกใช้ Method getX เพื่อให้อยู่ในตำแหน่งแกน X
            int y = model.getY() + model.getSize().height - percentHeight;//เรียกใช้ Method getY เพื่อให้ความสูงอยู่ในตำแหน่งแกน Y
            int w = item.width;
            int h = percentHeight;
            g.setColor(item.background==null ? new Color(136, 193, 251) : item.background);//setสี
            g.fillRect(x, y, w, h);//วาดสี่เหลี่ยมตามตัวแปร
            i++;
            //Draw the title
            int sw = fm.stringWidth(item.title);
            if (sw < item.width){//เป็นเงื่อนไขในการที่จะให้ตัวชื่อตรงกับตำแหน่งของกราฟ
                x = x + ((item.width - sw)/2);
            } else {
                x = x - ((sw - item.width)/2);
            }
            
            y = model.getY() + model.getSize().height + BAR_TITLE_FONT.getSize();//set ให้ตัวหนังสือตรงกับกราฟ ที่อยู่ในแกน Y
            g.drawString(item.title, x, y);
        }
    }
    
    private void drawBoarder(Graphics g){//setสีให้เป็นสีน้ำเงินพร้อมกับกำหนดให้มีเส้นกรอบของกราฟ
        g.setColor(Color.cyan);
        g.drawRoundRect(model.getX(), model.getY(), model.getSize().width, model.getSize().height, 5, 5);
    }
}
