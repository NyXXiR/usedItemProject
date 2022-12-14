package usedItemProject;

import java.awt.*;
import java.awt.event.*;
// �뙣�꼸 �쟾�솚 李멸퀬: https://blog.naver.com/10hsb04/221612791503
import javax.swing.*;

public class AfterLoginPage extends JFrame implements ActionListener {
  UserDB userDB = new UserDB();
  // 濡쒓렇�씤 �꽦怨듯븳 硫ㅻ쾭�쓽 �븘�씠�뵒瑜� �뼸�뒗 諛⑸쾿
  LogInUser logInUser = LogInUser.getInstance();
  // (1)LogInUser logInUser = LogInUser.getInstance();
  // (2)logInUser.getId();
  // �샊�� (1) LogInUser.getInstance().getId();
  // 濡쒓렇�씤�럹�씠吏��뿉�꽌 �떎�뙣�떆, 濡쒓렇�븘�썐�떆 logInUser�쓽 �븘�씠�뵒媛�(null)

  JButton logOutBtn = new JButton("濡쒓렇�븘�썐");
  JButton resignUserBtn = new JButton("�쉶�썝�깉�눜");
  JButton showUserInfoBtn = new JButton("媛쒖씤�젙蹂댁“�쉶");
  JButton editUserInfoBtn = new JButton("媛쒖씤�젙蹂댁닔�젙");

  JPanel northPanel = new JPanel(); // �긽�떒 �뙣�꼸
  JPanel southPanel = new JPanel(); // �븯�떒 �뙣�꼸

  // 媛쒖씤�젙蹂댁닔�젙 �뀓�뒪�듃�븘�뱶 //�븘�씠�뵒�뒗 蹂�寃� 紐삵븯寃�
  JPasswordField updatePwd = new JPasswordField(logInUser.getPwd(), 20);
  JTextField updateName = new JTextField(logInUser.getName(), 10);
  JTextField updateAddress = new JTextField(logInUser.getAddress(), 35);
  JTextField updatePhone = new JTextField(String.valueOf(logInUser.getPhone()), 15);
  JLabel limit = new JLabel("-�쓣 遺숈씠吏�留덉꽭�슂.");
  JTextField updateNick = new JTextField(logInUser.getNick(), 10);

  AfterLoginPage() {

    setLocation(500, 300);
    setSize(500, 600);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container cont = this.getContentPane();
    JPanel viewPanel = new JPanel();
    cont.add(viewPanel);

    viewPanel.setLayout(new BorderLayout());
    viewPanel.add(northPanel, BorderLayout.NORTH);
    southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS)); // �븯�떒(怨좎젙) �뙣�꼸�쓽 �젅�씠�븘�썐
                                                                       // �꽕�젙

    northPanel.add(logOutBtn);
    northPanel.add(resignUserBtn);
    northPanel.add(showUserInfoBtn);
    northPanel.add(editUserInfoBtn);

    logOutBtn.addActionListener(this);
    resignUserBtn.addActionListener(this);
    showUserInfoBtn.addActionListener(this);
    editUserInfoBtn.addActionListener(this);

    firstSouth(); // 珥덇린�솕硫�
    viewPanel.add(southPanel);
    this.setVisible(true);

  }

  void firstSouth() { // 珥덇린 諛� 泥ル쾲吏� �뙣�꼸�쓽 �솕硫�
    southPanel.add(new JLabel("珥덇린�솕硫� �뙣�꼸"));
  }

  void resignSouth() { // (1)�쉶�썝�깉�눜 �뙣�꼸
    southPanel.add(new JLabel("�쉶�썝�깉�눜 �뙣�꼸"));
    JPanel idPanel = new JPanel();
    JLabel idLb = new JLabel("�븘�씠�뵒 : ");
    JTextField idTxt = new JTextField(10); // �엯�젰��
    JPanel pwdPanel = new JPanel();
    JLabel pwdLb = new JLabel("鍮꾨�踰덊샇 : ");
    JPasswordField pwdTxt = new JPasswordField(10);// �엯�젰��
    JButton confirmResignBtn = new JButton("�쉶�썝�깉�눜 吏꾪뻾");
    idPanel.add(idLb);
    idPanel.add(idTxt);
    pwdPanel.add(pwdLb);
    pwdPanel.add(pwdTxt);
    southPanel.add(idPanel);
    southPanel.add(pwdPanel);
    southPanel.add(confirmResignBtn);

    LogInUser user = userDB.getLogInUserInfo(logInUser.getId());
    confirmResignBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String inputId = idTxt.getText();
        String inputPwd = new String(pwdTxt.getPassword());
        if (user.getId().equals(inputId) && user.getPwd().equals(inputPwd)) {
          int confirm = JOptionPane.showConfirmDialog(null, "�젙留먮줈 �깉�눜�븯�떆寃좎뒿�땲源�?",
              "�쉶�썝�깉�눜 �솗�씤", JOptionPane.YES_NO_OPTION);
          if (confirm == 0) {
            userDB.delete(inputId);
            JOptionPane.showMessageDialog(null, "�쉶�썝�깉�눜 �꽦怨�!");
            setVisible(false); // �쁽�옱 李� �븞蹂댁씠寃� �븯湲�
          }
        } else {
          JOptionPane.showMessageDialog(null, "�쉶�썝�깉�눜 �떎�뙣!", "�삤瑜�", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }

  void showUserInfoSouth() { // (2)媛쒖씤�젙蹂댁“�쉶 �뙣�꼸
    southPanel.add(new JLabel(logInUser.getName() + "�떂(" + logInUser.getNick() + ")�쓽 �뙣�꼸"));
    String infoStr = String.format(
        "�븘�씠�뵒 : %s \n �씠由� : %s \n 二쇱냼 : %s \n �땳�꽕�엫 : %s \n �쟾�솕踰덊샇 : %d \n", logInUser.getId(),
        logInUser.getName(), logInUser.getAddress(), logInUser.getNick(), logInUser.getPhone());

    JTextArea infoArea = new JTextArea(infoStr);
    infoArea.setFont(new Font("Courier", Font.PLAIN, 20));
    southPanel.add(infoArea);
  }

  void editSouth() { // (3)媛쒖씤�젙蹂댁닔�젙 �뙣�꼸
    // UI
    southPanel
        .add(new JLabel(logInUser.getName() + "(" + logInUser.getNick() + ")�떂�쓽 媛쒖씤�젙蹂댁닔�젙 �뙣�꼸"));

    // �씪踰�
    JLabel[] labelList = new JLabel[5];
    String strList[] = {"鍮꾨�踰덊샇 : ", "�씠    由� : ", "二�    �냼 : ", "�뿰 �씫 泥� : ", "�땳 �꽕 �엫 : "};
    for (int i = 0; i < labelList.length; i++) {
      labelList[i] = new JLabel(strList[i]);
    }

    // �씪踰� + �뀓�뒪�듃�븘�뱶 = formPanelList
    JPanel[] formPanelList = new JPanel[5];
    Font formFont = new Font("Courier", Font.PLAIN, 30);
    for (int i = 0; i < strList.length; i++) {
      formPanelList[i] = new JPanel();
      formPanelList[i].setLayout(new FlowLayout(FlowLayout.LEFT));
      formPanelList[i].add(labelList[i]);
      formPanelList[i].setFont(formFont);
    }
    formPanelList[0].add(updatePwd);
    formPanelList[1].add(updateName);
    formPanelList[2].add(updateAddress);
    formPanelList[3].add(updatePhone);
    formPanelList[3].add(limit);
    formPanelList[4].add(updateNick);

    // formPanelList 8媛� �젙�젹
    JPanel allFormPanel = new JPanel();
    allFormPanel.setLayout(new GridLayout(8, 1, 0, 30)); // 8�뻾,1�뿴
    for (int i = 0; i < formPanelList.length; i++) {
      allFormPanel.add(formPanelList[i]);
    }

    southPanel.add(allFormPanel);
    JButton editBtn = new JButton("媛쒖씤�젙蹂� �닔�젙");
    southPanel.add(editBtn);


    editBtn.addActionListener(new ActionListener() {
      String updatePwd2 = new String(updatePwd.getPassword());

      @Override
      public void actionPerformed(ActionEvent e) {
        if (updatePwd2.trim().equals("") || updateAddress.getText().trim().equals("")
            || updateNick.getText().trim().equals("") || updatePhone.getText().trim().equals("")) {
          JOptionPane.showMessageDialog(null, "�젙蹂대�� 紐⑤몢 �엯�젰�빐二쇱꽭�슂", "�삤瑜�", 2);
        } else {
          int myPhone = 0;
          try {
            myPhone = Integer.valueOf(updatePhone.getText());
          } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "�쟾�솕踰덊샇瑜� �닽�옄濡� �엯�젰�빐二쇱꽭�슂.", "�삤瑜�",
                JOptionPane.ERROR_MESSAGE);
          }
          if (myPhone != 0) {
            userDB.userUpdate(updatePwd2, updateName.getText(), updateAddress.getText(),
                updateNick.getText(), myPhone, logInUser.getId());
            JOptionPane.showMessageDialog(null, "�쉶�썝�젙蹂닿� �닔�젙�릺�뿀�뒿�땲�떎.");
          }
        }
      }
    });

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String btnVal = e.getActionCommand(); // 踰꾪듉媛� 諛쏄린
    southPanel.removeAll(); // �븯�떒 �뙣�꼸 吏��슦湲�
    if (btnVal.equals("濡쒓렇�븘�썐")) {
      logInUser.setId(null); // 濡쒓렇�븘�썐�떆 logInUser �젙蹂대뱾 珥덇린�솕(null)
      logInUser.setPwd(null);
      logInUser.setAddress(null);
      logInUser.setNick(null);
      logInUser.setPhone(0);

      dispose();
      LogInPage.logInPage.setVisible(true);
    } else if (btnVal.equals("�쉶�썝�깉�눜")) {
      resignSouth();
    } else if (btnVal.equals("媛쒖씤�젙蹂댁“�쉶")) {
      showUserInfoSouth();
    } else if (btnVal.equals("媛쒖씤�젙蹂댁닔�젙")) {
      editSouth();
    }
    southPanel.updateUI(); // �뙣�꼸 �솕硫� �뾽�뜲�씠�듃

  }

  public static void main(String[] args) {

  }

}
