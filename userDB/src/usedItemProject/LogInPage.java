package usedItemProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

public class LogInPage extends JFrame implements ActionListener {
  public static LogInPage logInPage; // J�봽�젅�엫 setVisible 愿��젴

  // �뜲�씠�꽣�뿰�룞
  UserDB userDB = new UserDB();

  // 濡쒓렇�씤�븳 �쑀��(�떛湲��넠) �뼸湲�
  LogInUser logInUser = LogInUser.getInstance();

  // UI
  Container body;
  // �긽�떒 ���씠��
  JLabel title = new JLabel("          Log In Page");

  // 以묒븰 濡쒓렇�씤 �뙣�꼸
  JPanel logPanel = new JPanel();
  JPanel idPanel = new JPanel();
  JLabel idLb = new JLabel("�븘�씠�뵒 :");
  JTextField idTxt = new JTextField(10); // �엯�젰��
  JPanel pwdPanel = new JPanel();
  JLabel pwdLb = new JLabel("鍮꾨�踰덊샇 :");
  JPasswordField pwdTxt = new JPasswordField(10);// �엯�젰��

  // �븯�떒 踰꾪듉 �뙣�꼸
  JPanel btnPanel = new JPanel();
  JButton logInBtn = new JButton("濡쒓렇�씤");
  JButton signUpBtn = new JButton("�쉶�썝媛��엯");

  public LogInPage() {
    this.setTitle("硫붿씤�럹�씠吏�");
    this.setSize(400, 400);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    body = getContentPane();
    body.setLayout(new GridLayout(3, 1));
    title.setFont(new Font("Courier", Font.PLAIN, 30));
    body.add(title, BorderLayout.NORTH);
    this.setLogPanel();
    body.add(logPanel);
    this.setButtonPanel();
    body.add(btnPanel);
    this.setVisible(true);
    // 珥덇린 �솕硫대떒�뿉 蹂댁씪 寃껋� setVisible�쐞�뿉

    // �쉶�썝媛��엯 李쎈쓣�슦湲�
    signUpBtn.addActionListener(this);

    // 濡쒓렇�씤 踰꾪듉 �늻瑜쇱떆.
    logInBtn.addActionListener(this);

  }

  void setButtonPanel() {
    btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
    btnPanel.add(logInBtn);
    btnPanel.add(signUpBtn);
  }

  void setLogPanel() {
    logPanel.setSize(300, 200);
    logPanel.setLayout(new GridLayout(2, 1, 20, 10));
    Font logFont = new Font("Courier", Font.PLAIN, 20);
    idLb.setFont(logFont);
    idTxt.setFont(logFont);
    pwdLb.setFont(logFont);
    pwdTxt.setFont(logFont);

    idPanel.add(idLb);
    idPanel.add(idTxt);
    pwdPanel.add(pwdLb);
    pwdPanel.add(pwdTxt);

    logPanel.add(idPanel);
    logPanel.add(pwdPanel);
  }

  public static void main(String[] args) {
    logInPage = new LogInPage();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == signUpBtn) { // �쉶�썝媛��엯 踰꾪듉 �늻瑜쇱떆
      try {
        new SignUpPage(); // �쉶�썝媛��엯 �럹�씠吏�
      } catch (ClassNotFoundException | SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      setVisible(false); // 李� �븞蹂댁씠寃� �븯湲�
    } else if (e.getSource() == logInBtn) { // 濡쒓렇�씤 踰꾪듉 �늻瑜쇱떆
      String inputId = idTxt.getText();
      String inputPwd = new String(pwdTxt.getPassword());
      System.out.println(logInUser.getId());
      System.out.println(logInUser.getNick());

      if (inputId.equals("") || inputPwd.equals("")) {
        JOptionPane.showMessageDialog(null, "�븘�씠�뵒�� 鍮꾨�踰덊샇 紐⑤몢 �엯�젰�빐二쇱꽭�슂", "濡쒓렇�씤 �떎�뙣",
            JOptionPane.ERROR_MESSAGE);
        System.out.println("濡쒓렇�씤 �떎�뙣 > 濡쒓렇�씤 �젙蹂� 誘몄엯�젰");
      }

      else if (inputId != null && inputPwd != null) {
        if (userDB.loginCheck(inputId, inputPwd)) { // 濡쒓렇�씤 �꽦怨듭떆
          System.out.println("濡쒓렇�씤 �꽦怨�");
          JOptionPane.showMessageDialog(null, "濡쒓렇�씤�뿉 �꽦怨듯븯���뒿�땲�떎");
          // 濡쒓렇�씤�븳 �쑀���쓽 �젙蹂� ���옣.
          userDB.getLogInUserInfo(inputId);
          // 濡쒓렇�씤 �꽦怨� �씠�썑 �솕硫� �쓣�슦湲�
          new AfterLoginPage();
        } else {
          System.out.println("濡쒓렇�씤 �떎�뙣 > 濡쒓렇�씤 �젙蹂� 遺덉씪移�");
          JOptionPane.showMessageDialog(null, "濡쒓렇�씤�뿉 �떎�뙣�븯���뒿�땲�떎", "濡쒓렇�씤 �떎�뙣",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }



}
