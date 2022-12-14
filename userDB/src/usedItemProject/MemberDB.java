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
    // executeQuery�� �������� ResultSet ��ü�� ���� ��ȯ�Ѵ�.
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
    // executeUpdate�� �ݿ��� ���ڵ��� �Ǽ��� ��ȯ�Ѵ�.(�ٷ� insert, update, delete�ϸ� �Ǵϱ� rs�� ���Ϲ��� �ʿ䰡 ����)
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
