package usedItemProject;

import java.sql.*;

public class UserDB {
  Connection conn;
  Statement stmt;
  ResultSet rs;
  PreparedStatement pstmt;

  // mysql�뿉 �젒�냽
  public UserDB() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thisisjava", "java", "mysql");
    } catch (ClassNotFoundException | SQLException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    System.out.println("�뱶�씪�씠踰� �꽕移�");
    System.out.println("�젒�냽 �꽦怨�");
  }


  // SelectAll硫붿냼�뱶 : member�쟾泥� �젙蹂�
  public String selectAll() {
    String str = "";
    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery("select * from user");
      while (rs.next()) {
        String id = rs.getString("id");
        String pwd = rs.getString("pwd");
        String name = rs.getString("name");
        String address = rs.getString("address");
        String nick = rs.getString("nick");
        int phone = rs.getInt("phone");
        str += String.format("id:%s name:%s address:%s nickname:%s phoneNumber:%d \n", id, name,
            address, nick, phone);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return str;
  }

  // insert硫붿냼�뱶 : 硫ㅻ쾭DB�뿉 異붽�, �쉶�썝媛��엯
  public void insert(String id, String pwd, String name, String address, String nick, int phone) {

    String sql =
        "" + "INSERT INTO user(id, pwd, name, address, nick, phone) " + "VALUES (?, ?, ?, ?, ?,?)";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.setString(2, pwd);
      pstmt.setString(3, name);
      pstmt.setString(4, address);
      pstmt.setString(5, nick);
      pstmt.setInt(6, phone);
      int result = pstmt.executeUpdate();
      System.out.println(result + "嫄� �엯�젰 �꽦怨�");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  // �젙蹂댁닔�젙.
  // id媛� �궎 媛�. �굹癒몄��뒗 蹂�寃� 媛�. (id�뒗 蹂�寃쎈텋媛�)
  // https://blog.naver.com/PostView.nhn?blogId=javaking75&logNo=140190267626
  public int userUpdate(String updatePwd, String updateName, String updateAddress,
      String updateNick, int updatePhone, String id) {
    int result = 0;
    String sql = "UPDATE user SET  pwd=?, name=?, address=?, nick=?, phone=? WHERE id=?";
    try {
      pstmt = conn.prepareStatement(sql);
      // ?�쓽 �닚�꽌��濡� 媛� �꽔湲�
      pstmt.setString(1, updatePwd);
      pstmt.setString(2, updateName);
      pstmt.setString(3, updateAddress);
      pstmt.setString(4, updateNick);
      pstmt.setInt(5, updatePhone);
      pstmt.setString(6, id);
      // �떎�뻾�븯湲�
      result = pstmt.executeUpdate();

    } catch (SQLException e) {

      System.out.println(e.getMessage() + "=> userUpdate fail");
    }
    return result;
  }// userUpdate()


  // delete硫붿냼�뱶 : 硫ㅻ쾭DB�뿉�꽌 硫ㅻ쾭 �궘�젣, �쉶�썝�깉�눜
  public void delete(String id) {
    try {
      stmt = conn.createStatement();
      int result = stmt.executeUpdate("delete from user where id = '" + id + "' ");
      System.out.printf("%s�쓽 �옄猷뚮�� %d嫄� �궘�젣", id, result);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  // Id以묐났泥댄겕
  // 留ㅺ컻蹂��닔濡� �뱾�뼱�삩 id�뿉 �빐�떦�븯�뒗 �젅肄붾뱶 寃��깋�븯�뿬 以묐났�뿬遺� 泥댄겕�븯湲� 由ы꽩媛믪씠 true =�궗�슜媛��뒫 , false =
  // 以묐났
  public boolean getIdByCheck(String id) {
    boolean result = true;
    try {
      pstmt = conn.prepareStatement("SELECT * FROM user WHERE id=?");
      pstmt.setString(1, id.trim());
      // 臾몄옄�뿴�쓣 �엯�젰諛쏄굅�굹 臾몄옄�뿴�쓣 �빀移좉꼍�슦 媛��걫 �벝�뜲�뾾�뒗 怨듬갚�씠 �뵲�씪 �뱾�뼱�삤怨� �빀�땲�떎
      // trim()�씠�씪怨좊쭔 �뜥二쇱떆硫�, �쇊履쎌뿉 �엳�뒗 怨듬갚, �삤瑜몄そ�뿉 �엳�뒗 怨듬갚�쓣 �떎 �젣嫄고빐以띾땲�떎.
      // 媛��슫�뜲 �엳�뒗 怨듬갚�� �젣嫄고빐二쇱��뒗 �븡�뒿�땲�떎. 媛��슫�뜲 �엳�뒗 怨듬갚�� replace�븿�닔瑜� �뜥�꽌 �젣嫄�.
      rs = pstmt.executeQuery();
      if (rs.next())
        result = false; // �젅肄붾뱶媛� 議댁옱�븯硫� false

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      System.out.println(e + "�븣臾몄뿉  getIdByCheck() fail");
    }
    return result;

  } // getIdByCheck()
  // [異쒖쿂] https://blog.naver.com/PostView.nhn?blogId=javaking75&logNo=140190267626

  // 濡쒓렇�씤 (�븘�씠�뵒-�뙣�뒪�썙�뱶 �솗�씤) 湲곕뒫
  public boolean loginCheck(String inputId, String inputPwd) {
    boolean result = false;
    try {
      stmt = conn.createStatement();
      String checkingStr = "SELECT pwd FROM user WHERE id='" + inputId + "'";
      ResultSet rs = stmt.executeQuery(checkingStr);
      int count = 0;
      while (rs.next()) {
        if (inputPwd.equals(rs.getString("pwd"))) {
          result = true;
          System.out.println("濡쒓렇�씤 �꽦怨�");
        } else {
          result = false;
          System.out.println("濡쒓렇�씤 �떎�뙣");
        }
        count++;
      }
    } catch (Exception e) {
      result = false;
      System.out.println("濡쒓렇�씤 �떎�뙣 > " + e.toString()); // �엯�젰 �븘�씠�뵒媛� db�뿉 �뾾�쓣 �븣.
    }
    return result;
  }

  // 濡쒓렇�씤 �꽦怨듯븳 �궗�엺�쓽 �젙蹂대뱾�쓣 由ы꽩
  public LogInUser getLogInUserInfo(String logInUserId) {
    LogInUser logInUser = LogInUser.getInstance();
    try {
      stmt = conn.createStatement();
      String checkingStr = "SELECT * FROM user WHERE id='" + logInUserId + "'";
      rs = stmt.executeQuery(checkingStr);
      while (rs.next()) {
        if (logInUserId.equals(rs.getString("id"))) {
          // (1)濡쒓렇�씤 �꽦怨듯븳 �궗�엺�쓽 �젙蹂대뱾�쓣 DB�뿉�꽌 釉붾윭���꽌
          String id = rs.getString("id");
          String pwd = rs.getString("pwd");
          String name = rs.getString("name");
          String address = rs.getString("address");
          String nick = rs.getString("nick");
          int phone = rs.getInt("phone");

          // (2)LogInUser 媛앹껜�뿉 ���옣�븳 �썑 由ы꽩
          logInUser.setId(id);
          logInUser.setPwd(pwd);
          logInUser.setName(name);
          logInUser.setAddress(address);
          logInUser.setNick(nick);
          logInUser.setPhone(phone);
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    return logInUser;
  }


  public static void main(String[] args) {
    // UserDB userDB = new UserDB();
    // userDB.insert(id, pwd, name, address, nick, phone);
    // userDB.delete(�궘�젣�븯�젮�뒗 硫ㅻ쾭�쓽 id);

  }
}
