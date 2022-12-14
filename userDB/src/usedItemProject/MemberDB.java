package usedItemProject;

import java.sql.*;

public class MemberDB {

  Connection conn;
  Statement stmt;
  ResultSet rs;

  MemberDB() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thisisjava", "java", "mysql");
  }

  String selectAll() throws SQLException {
    // executeQuery는 수행결과로 ResultSet 객체의 값을 반환한다.
    stmt = conn.createStatement();
    rs = stmt.executeQuery("select * from student");
    String str = null;
    while (rs.next()) {
      int id = rs.getInt("id");
      String name = rs.getString("name");
      String hakbun = rs.getString("hakbun");
      String address = rs.getString("address");

      str += String.format("[%d] %s %s %s \n", id, name, hakbun, address);
    }
    return str;

  }

  void insert() {
    // executeUpdate는 반영된 레코드의 건수를 반환한다.(바로 insert, update, delete하면 되니까 rs를 리턴받을 필요가 없다)
    stmt = conn.createStatement();
    int result = stmt.executeUpdate(
        "insert into student values(0,'" + name + "','" + hakbun + "','" + address + "')");

  }

  void delete(String name) throws SQLException {
    stmt = conn.createStatement();
    String result = stmt.executeUpdate("delete from student where name=%", name);

  }

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    // TODO Auto-generated method stub
    MemberDB sd = new MemberDB();



  }

}
