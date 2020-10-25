
package graphapp; 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BarGraphApp extends JPanel { // เอคเทนเฟรมแบบไม่มีหัวเรื่อง

    JSlider slider; //สร้างแถบบาร์
    JComboBox<String> names; //คอมโบบอค เก็บชื่อ (สตริง)

    private BarGraph graph;  //ประกาศเรียกใช้class บาร์การ์ฟ
    private BarGraphModel model; //ประกาศเรียกใช้class บาร์กราฟโมเดล
    
    public BarGraphApp() {  //สร้างคอนสตัคเตอร์
        setBackground(new Color(27, 29, 77));  //เซตสีพื้นหลัง
        this.graph = new BarGraph();  //obj bargraph
        this.model = graph.getModel();
        slider = new JSlider(); //obj slider
        slider.setMaximum(100); //ให้ค่าสูงสุดเป็น100
        slider.setMajorTickSpacing(10); //ขยับทีละ10หน่วย
        slider.setSnapToTicks(false); //ให้ขยับเท่าไรก็ได้
        slider.setPaintTicks(true); // ให้เห็นไอขีดๆ
        
        
        slider.addChangeListener(new ChangeListener() { //เป็นตัวเหมือนactionlistener
            @Override
            public void stateChanged(ChangeEvent e) {   
                int index = names.getSelectedIndex();
                model.itemAt(index).percentage = slider.getValue();
                repaint(); //ให้วาดใหม่
            }
        });
        
        names = new JComboBox<String>(new String[]{"Lisa", "John", "Linda", "David", "Guido"}); //สร้างคอมโบบอคเก็บชื่อ5คน
        //ใส่ Action กรณีกด คลิกเพื่อเลือกข้อมูลที่อยู่ใน Combobox ว่าต้องการจะให้กราฟใครเลื่อนขึ้น
        names.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = names.getSelectedIndex(); //จะคืนค่าตำแหน่งของข้อมูลที่ถูกเลือกโดยคืนค่ามาเป็นตัวแปรประเภท int (เริ่มนับตำแหน่งแรกที่ ศุนย์)
                slider.setValue(model.itemAt(index).percentage);
            }
        });
        
        names.setSelectedIndex(0); //การกำหนดให้เลือกข้อมูล ณ ตำแหน่งที่กำหนด
        
        add(slider); //แอดสไลด์เดอเข้าไป
        add(names); //แอดชื่อเข้าไป
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //เรียกใช้paint
        graph.draw(g); //วาดรูปกราฟ
    }

    public static void main(String[] args) { //สร้างเมธอดเมน
        // TODO code application logic here
        JFrame frame = new JFrame("Bar Graph"); //เรียกjframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //กดกากบาทปิดได้
        frame.setSize(460, 420); //ขนาดเฟรม
        frame.setLocationRelativeTo(null); //จัดให้ window อยู่กลางหน้าจอ
        frame.add(new BarGraphApp());  //เอาbargraphapp ยัดเข้าไปในเฟรม
        frame.setVisible(true); //ให้มองเห็น
    }

}
