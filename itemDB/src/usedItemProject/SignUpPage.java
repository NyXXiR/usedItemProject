package usedItemProject;


import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

public class SignUpPage extends JFrame implements ActionListener {
  // UserDB�뿰�룞
  UserDB userDB = new UserDB();

  // �쉶�썝媛��엯 �젙蹂댁엯�젰
  JLabel[] labelList = new JLabel[7];
  String strList[] = {"�븘�씠�뵒 : ", "鍮꾨�踰덊샇 : ", "鍮꾨�踰덊샇 �옱�솗�씤: ", "�씠    由� : ", "二�    �냼 : ",
      "�뿰 �씫 泥� : ", "�땳 �꽕 �엫 : "};

  JTextField inputId = new JTextField(10);
  JButton idCheckBtn = new JButton("ID 以묐났 泥댄겕");
  JPasswordField inputPwd = new JPasswordField(20);
  JPasswordField inputPwdRe = new JPasswordField(20);
  JTextField inputName = new JTextField(10);
  JTextField inputAddress = new JTextField(35);
  JTextField inputPhone = new JTextField(15);
  JLabel limit = new JLabel("-�쓣 遺숈씠吏�留덉꽭�슂.");
  JTextField inputNick = new JTextField(10);

  JPanel[] formPanelList = new JPanel[7];
  JPanel allFormPanel = new JPanel();

  // 踰꾪듉
  JPanel btnPanel = new JPanel();
  JButton joinBtn = new JButton("�쉶�썝媛��엯");
  JButton cancelBtn = new JButton("痍⑥냼");

  public SignUpPage() throws ClassNotFoundException, SQLException {
    this.setTitle("�쉶�썝媛��엯 �솕硫�");
    this.setSize(800, 800);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // �쐢�룄�슦 �솕硫� 媛��슫�뜲�뿉 �넃湲�
    Dimension frameSize = getSize();
    Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((windowSize.width - frameSize.width) / 2,
        (windowSize.height - frameSize.height) / 2); // �솕硫� 以묒븰�뿉 �쓣�슦湲�

    // 而댄룷�꼳�듃�뱾
    // (1)���씠�� (�긽�떒)
    JLabel title = new JLabel("�쉶�썝媛��엯", JLabel.CENTER);
    title.setForeground(new Color(5, 0, 153));
    title.setFont(new Font("Courier", Font.BOLD, 50));

    // (2)�젙蹂댁엯�젰 �뙣�꼸(以묒븰)
    Font Fieldfont = new Font("arian", Font.BOLD, 20);
    inputId.setFont(Fieldfont);
    inputPwd.setFont(Fieldfont);
    inputPwdRe.setFont(Fieldfont);
    inputName.setFont(Fieldfont);
    inputAddress.setFont(Fieldfont);
    inputPhone.setFont(Fieldfont);
    inputNick.setFont(Fieldfont);

    Font formFont = new Font("Courier", Font.PLAIN, 30);
    for (int i = 0; i < strList.length; i++) {
      labelList[i] = new JLabel(strList[i]);
      labelList[i].setFont(formFont);
      formPanelList[i] = new JPanel();
      formPanelList[i].setLayout(new FlowLayout(FlowLayout.LEFT));
      formPanelList[i].add(labelList[i]);
    }

    Font limitFont = new Font("Courier", Font.PLAIN, 20);
    formPanelList[0].add(inputId);
    formPanelList[0].add(idCheckBtn);
    formPanelList[1].add(inputPwd);
    formPanelList[2].add(inputPwdRe);
    formPanelList[3].add(inputName);
    formPanelList[4].add(inputAddress);
    formPanelList[5].add(inputPhone);
    limit.setFont(limitFont);
    formPanelList[5].add(limit);
    formPanelList[6].add(inputNick);

    allFormPanel.setLayout(new GridLayout(8, 1, 0, 30)); // 8�뻾,1�뿴
    for (int i = 0; i < formPanelList.length; i++) {
      allFormPanel.add(formPanelList[i]);
    }

    // (3)踰꾪듉 �뙣�꼸(�븯�떒)
    Font buttonFont = new Font("Courier", Font.PLAIN, 30);
    joinBtn.setFont(buttonFont);
    cancelBtn.setFont(buttonFont);

    btnPanel.add(joinBtn);
    btnPanel.add(cancelBtn);

    // (1),(2),(3)諛곗튂
    this.add(title, BorderLayout.NORTH);
    this.add(allFormPanel, BorderLayout.CENTER);
    this.add(btnPanel, BorderLayout.SOUTH);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    joinBtn.addActionListener(this);
    cancelBtn.addActionListener(this);
    idCheckBtn.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String myId = inputId.getText(); // inputId�뒗 id�뀓�뒪�듃�븘�뱶
                                     // inputId.getText()�뒗 �뀓�뒪�듃�븘�뱶�뿉 �쟻�엺 臾몄옄.
    if (e.getSource() == joinBtn) { // 媛��엯踰꾪듉 �닃���쓣 �븣

      String myPwd = new String(inputPwd.getPassword());
      String myName = inputName.getText();
      String myAddress = inputAddress.getText();
      String myNick = inputNick.getText();
      int myPhone = 0;

      String myPwdRe = new String(inputPwdRe.getPassword());
      if (myId.equals("") || myPwd.equals("") || myAddress.equals("") || myNick.equals("")
          || inputPhone.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "�젙蹂대�� 紐⑤몢 �엯�젰�빐二쇱꽭�슂", "�삤瑜�",
            JOptionPane.ERROR_MESSAGE);
      } else {
        try {
          myPhone = Integer.valueOf(inputPhone.getText());
        } catch (NumberFormatException e1) {
          JOptionPane.showMessageDialog(null, "�쟾�솕踰덊샇瑜� �닽�옄濡� �엯�젰�빐二쇱꽭�슂", "�삤瑜�",
              JOptionPane.ERROR_MESSAGE);
        }
        if (!userDB.getIdByCheck(myId)) { // 以묐났�씪 寃쎌슦
          JOptionPane.showMessageDialog(null, "id 以묐났泥댄겕瑜� �빐二쇱꽭�슂", "�븘�씠�뵒 �삤瑜�",
              JOptionPane.ERROR_MESSAGE);
        } else if (!myPwd.equals(myPwdRe)) {
          JOptionPane.showMessageDialog(null, "鍮꾨�踰덊샇媛� �꽌濡� 留욎� �븡�뒿�땲�떎", "鍮꾨�踰덊샇 �삤瑜�",
              JOptionPane.ERROR_MESSAGE);
        } else {
          if (myPhone != 0) {
            userDB.insert(myId, myPwd, myName, myAddress, myNick, myPhone);
            JOptionPane.showMessageDialog(null, " " + myName + " �떂, �쉶�썝媛��엯�뿉 �꽦怨듯븯�뀲�뒿�땲�떎 ");
            LogInPage.logInPage.setVisible(true);
            dispose();
          }
        }
      }
    } else if (e.getSource() == cancelBtn) {// 痍⑥냼踰꾪듉 �닃���쓣 �븣
      LogInPage.logInPage.setVisible(true);
      dispose(); // �쁽�옱 李� �걚�뒗 硫붿냼�뱶
    } else if (e.getSource() == idCheckBtn) {// id 以묐났泥댄겕 �닃���쓣 �뻹
      // id�뀓�뒪�듃諛뺤뒪�뿉 媛� �뾾�쑝硫� 硫붿꽭吏� 異쒕젰, �엳�쑝硫� DB�뿰�룞�븳�떎.
      if (myId.trim().equals("")) {
        JOptionPane.showMessageDialog(null, "ID瑜� �엯�젰�빐二쇱꽭�슂.");
        inputId.requestFocus();// id text諛뺤뒪濡� �룷而ㅼ뒪�씠�룞
      } else {
        if (userDB.getIdByCheck(myId)) { // 以묐났�븘�땲�떎.(�궗�슜媛��뒫)
          JOptionPane.showMessageDialog(null, myId + "�뒗 �궗�슜媛��뒫�빀�땲�떎.");
        } else { // 以묐났�씠�떎.
          JOptionPane.showMessageDialog(null, myId + "�뒗 以묐났�맂 id�엯�땲�떎.", "以묐났 �븘�씠�뵒 �삤瑜�", 1);
          inputId.setText("");// id text諛뺤뒪 吏��슦湲�
          inputId.requestFocus();// id text諛뺤뒪�뿉 而ㅼ꽌�넃湲�

        }
      }

    }
  }
}
// [UI李몄“] https://october-east-sea.tistory.com/73
