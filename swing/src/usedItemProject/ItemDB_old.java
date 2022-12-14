package usedItemProject;

import java.sql.*;
import java.util.Scanner;

public class ItemDB_old {
  // columns: num, id, name, price, address, date
  Connection conn;
  Statement stmt;
  ResultSet rs;
  Scanner sc = new Scanner(System.in);


  ItemDB_old() throws ClassNotFoundException, SQLException {
    // connection part
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thisisjava", "java", "mysql");
    System.out.println("연결 성공");
  }

  String selectAll() throws SQLException {
    // executeQuery는 수행결과로 ResultSet 객체의 값을 반환한다.
    stmt = conn.createStatement();
    rs = stmt.executeQuery("select * from item");
    String str = null;
    while (rs.next()) {
      String mb_id = rs.getString("mb_id");
      String name = rs.getString("name");
      int price = rs.getInt("price");
      String address = rs.getString("address");

      str += String.format("[%s] %s %d %s \n", mb_id, name, price, address);
    }
    return str;

  }

  // insertData만 만들어둠
  void insertData() throws SQLException {
    // executeUpdate는 반영된 레코드의 건수를 반환한다.(바로 insert, update, delete하면 되니까 rs를 리턴받을 필요가 없다)
    // 날짜 칸은 입력 안하면 오늘자가 자동으로 입력된다는데 확인해보자.

    System.out.println("ID를 입력하세요.");
    String inputId = sc.next();
    System.out.println("제품명을 입력하세요.");

    String inputName = sc.next();
    System.out.println("희망가격을 입력하세요.");

    int inputPrice = sc.nextInt();
    System.out.println("주소를 입력하세요.");
    String inputAddress = sc.next();
    stmt = conn.createStatement();
    String sql = String.format("insert into item values(0,'%s','%s',%d,'%s',now())", inputId,
        inputName, inputPrice, inputAddress);
    int result = stmt.executeUpdate(sql);

    System.out.println(result + " 건의 데이터를 처리했습니다.");
  }

  // 뭘 매개변수로 넣어서 delete를 실행하는 게 좋을까?
  void deleteData(String name) throws SQLException {
    stmt = conn.createStatement();
    String sql = String.format("delete from student where name=%s", name);
    int result = stmt.executeUpdate(sql);
    System.out.println(result + " 건의 데이터가 삭제되었습니다.");
  }

  // 판매된 itemDB의 행 soldItemDB에 추가 > itemDB 행 삭제
  void moveData() {
    // addActionListener로 클릭 인식. this


  }



  // update 필요하면 구현
  // void updateData() {}



}

