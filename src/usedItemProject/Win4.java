package usedItemProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


// 버튼 4개 생성, 카드 4개 생성
// 1~4 버튼을 누르면 각 카드가 맨 위로 올라오게 구현
public class Win4 extends JFrame {

  private CardLayout cards = new CardLayout();

  class Listener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      System.out.println("클릭");
    }


  }



  JButton jb = new JButton("클릭");

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
