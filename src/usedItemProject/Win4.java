package usedItemProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


// ��ư 4�� ����, ī�� 4�� ����
// 1~4 ��ư�� ������ �� ī�尡 �� ���� �ö���� ����
public class Win4 extends JFrame {

  private CardLayout cards = new CardLayout();

  class Listener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      System.out.println("Ŭ��");
    }


  }



  JButton jb = new JButton("Ŭ��");

  Win4() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container c = this.getContentPane();
    c.setLayout(null);
    jb.setBounds(100, 30, 80, 40);
    c.add(jb);
    jb.addActionListener(new Listener());
    this.setLocation(500, 400);
    this.setSize(400, 300);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    new Win4();
  }
}
