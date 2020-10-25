package graphapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class BarGraphModel {
    
    public static class BarItem {//กำหนดตัวแปรเพื่อนำไปใช้ใน Method ต่างๆ เช่น ตัวกราฟแท่ง เป็นต้น
        public int index;
        public int percentage;//ตัวกรอบสี่เหลี่ยมขอบฟ้า
        public int width;
        public String title;//ชื่อต่างๆที่สร้างขึ้นภายใต้กราฟ
        public Color background;
        
        public BarItem(String title){
            this.title = title;
        }
    }
    
    public static final int DEFAULT_ITEM_WIDTH = 40;
    
    protected List<BarItem> items = new ArrayList<BarItem>();//สร้าง Baritem เพื่อให้แสดงเป็นตัวกราฟแท่งขึ้นมา เป็น Array คลาสหรือสมาชิกสามารถเข้าถึงได้ภายใน package เดียวกันและ sub class ของมัน
    protected Point location = new Point();//จุดของตำแหน่งใน BarGraph คลาสหรือสมาชิกสามารถเข้าถึงได้ภายใน package เดียวกันและ sub class ของมัน
    protected Dimension size =  new Dimension();//ขนาดกรอบสี่เหลี่ยม คลาสหรือสมาชิกสามารถเข้าถึงได้ภายใน package เดียวกันและ sub class ของมัน
    protected int horizontalGap = DEFAULT_ITEM_WIDTH * 2;//ช่องว่างระหว่างกราฟแต่ละแท่ง คลาสหรือสมาชิกสามารถเข้าถึงได้ภายใน package เดียวกันและ sub class ของมัน
    
    public BarGraphModel() {

    }

    public void setHorizontalGap(int horizontalGap) {
        this.horizontalGap = horizontalGap;
    }
    
    public int getHorizontalCenterBarPosition(){
        return getX() + (getSize().width - (DEFAULT_ITEM_WIDTH+horizontalGap));
    }

    
    public int getHorizontalGap() {
        return horizontalGap;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(int width, int height) {//ขนาดกว้างยาวของกราฟแท่งแต่ละอัน
        size.width = width;
        size.height = height;
    }

    public void setLocation(int x, int y) {//จุดตำแหน่งของแกน X และ แกน Y
        this.location.x = x;
        this.location.y = y;
    }

    public Point getLocation() {
        return location;
    }
    
    public int getX(){
        return location.x;
    }
    
    public int getY(){
        return location.y;
    }
    
    public void addItem(BarItem item){//การเปลี่ยนแปลงของตัวกราฟ เมื่อเราไปทำงานกับกราฟแท่งอื่น อันเก่าก็จะมีค่าเท่าเดิม
        item.width = item.width == 0 ? DEFAULT_ITEM_WIDTH: item.width;
        items.add(item);
    }
    
    public BarItem itemAt(int index){//การชี้ตำแหน่งของตัวกราฟแท่ง
        return items.get(index);
    }
    
    public BarItem removeItemAt(int index){//ลบตัวกราฟจากตำแหน่งที่เลือกอยู่ตอนนั้น เมื่อเราเลื่อนตัว Bar ลงสุด ในการฟฟิกของกราฟแท่งตัวที่เราเลื่อนลดลงสุดก็จะหายไปในตำแหน่งนั้น
        return items.remove(index);
    }
}
