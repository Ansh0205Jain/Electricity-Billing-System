package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener{

    String meter;
    JButton back;
    Paytm(String meter) {
        this.meter = meter;
        setTitle("Payment Gateway");
        JEditorPane j = new JEditorPane();
        //setEditable(false) so that u cant change text
        j.setEditable(false);
        
        try {
            j.setPage("https://paytm.com/online-payments");
        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load<html>");
            
        }
        
        JScrollPane pane = new JScrollPane(j);
        add(pane);
        
        back = new JButton("Back");
        back.setBounds(640, 20, 80, 30);
        back.addActionListener(this);
        j.add(back);
        
        setSize(1000, 600);
        setLocation(300, 100);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new PayBill(meter);
    }
    
    public static void main(String[] args) {
        new Paytm("");
    }
}