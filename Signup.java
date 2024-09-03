package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener{

    JButton create, back;
    JComboBox accountType;
    JTextField tmeter, username, name;
    JPasswordField password;
    Signup(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setTitle("New User");
        
        JPanel panel = new JPanel();
        //panel location
        panel.setBounds(30, 30, 650, 300);
        //defining border 
        panel.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "Create-Account", TitledBorder.LEADING, TitledBorder.TOP, null , Color.BLUE));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(Color.BLACK);
        add(panel);
        
        JLabel heading = new JLabel("Create Account As");
        heading.setBounds(100, 50, 140, 20);
        heading.setForeground(Color.BLACK);
        heading.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(heading);
        
        String type[]={"Admin","Customer"};
        accountType = new JComboBox(type);
        accountType.setBounds(260, 50, 150, 20);
        panel.add(accountType);
        
        JLabel meter = new JLabel("Meter Number");
        meter.setBounds(100, 90, 140, 20);
        meter.setForeground(Color.BLACK);
        meter.setFont(new Font("Tahoma", Font.BOLD, 14));
        meter.setVisible(false);
        panel.add(meter);
        
        tmeter = new JTextField();
        tmeter.setBounds(260, 90, 150, 20);
        tmeter.setVisible(false);
        panel.add(tmeter);
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(100, 130, 140, 20);
        lblusername.setForeground(Color.BLACK);
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblusername);
        
        username = new JTextField();
        username.setBounds(260, 130, 150, 20);
        panel.add(username);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100, 170, 140, 20);
        lblname.setForeground(Color.BLACK);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblname);
        
        name = new JTextField();
        name.setBounds(260, 170, 150, 20);
        panel.add(name);
        
        tmeter.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent fe) {}
            
            @Override
            public void focusLost(FocusEvent fe) 
            {
                try {
                    Conn c  = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from login where meter_no = '"+tmeter.getText()+"'");
                    while(rs.next()) {
                        name.setText(rs.getString("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(100, 210, 140, 20);
        lblpassword.setForeground(Color.BLACK);
        lblpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblpassword);
        
        password = new JPasswordField();
        password.setBounds(260, 210, 150, 20);
        panel.add(password);
        
        accountType.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ae) {
                String user = (String) accountType.getSelectedItem();
                if (user.equals("Customer")) {
                    meter.setVisible(true);
                    tmeter.setVisible(true);
                    name.setEditable(false);
                } else {
                    meter.setVisible(false);
                    tmeter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });
        
        create = new JButton("Create");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.setBounds(140, 260, 120, 25);
        create.addActionListener(this);
        panel.add(create);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(300, 260, 120, 25);
        back.addActionListener(this);
        panel.add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(415, 30, 250, 250);
        panel.add(image);
        
        setSize(700,400);
        setLocation(450,150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == create) {
            String stype = (String) accountType.getSelectedItem();
            String susername = username.getText();
            String sname = name.getText();
            String spassword = password.getText();
            String smeter = tmeter.getText();
//           
            try {
                Conn c = new Conn();
                
                String query = null;
                if (stype.equals("Admin")) {
                    query = "insert into login values('"+smeter+"', '"+susername+"', '"+sname+"', '"+spassword+"', '"+stype+"')";
                } else {
                    query = "update login set username = '"+susername+"', password = '"+spassword+"', usertype = '"+stype+"' where meter_no = '"+smeter+"'";
                }
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Account Created Successfully.");
                
                setVisible(false);
                new Login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            //open login page again
            new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}