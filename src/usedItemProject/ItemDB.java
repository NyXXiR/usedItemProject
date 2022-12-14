package usedItemProject;

import java.sql.*;
import java.util.*;

public class ItemDB {
  // columns: num, id, name, price, address, date
  Connection conn;
  Statement stmt;
  PreparedStatement pstm;
  ResultSet rs;
  Scanner sc = new Scanner(System.in);
  String sql;
  SoldItemDB soldItemDB = new SoldItemDB();
  public ArrayList<ItemList> itemList = new ArrayList<ItemList>();

  ItemDB() throws ClassNotFoundException, SQLException {
    // connection part
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/usedItemProject", "root2",
        "mysql");
    System.out.println("itemDB ���� ����");
  }

  // ��ü select, �򰹼���, ��Ͻð���, �����ð��� ����
  // select���� order by ��Ʈ�� �߰��ϴ� ������� ����



  // ����Ʈ�����ʹ� ��ȣ �ȿ� �ִ� ���� ���� ���� order by�� �߰���. �ִ� 3������ ���� ����


  // �޼ҵ� �����ε�
  ArrayList<ItemList> searchData(String column) throws SQLException {
    stmt = conn.createStatement();
    String sql = "select * from itemDB where name like '%" + column + "%'";
    rs = stmt.executeQuery(sql);

    while (rs.next()) {
      String id = rs.getString("id");
      String name = rs.getString("name");
      String price = rs.getString("price");
      String address = rs.getString("address");
      String content = rs.getString("content");
      String transaction = rs.getString("transaction");
      String like = rs.getString("like");
      String date = rs.getString("date");

      itemList.add(new ItemList(id, name, price, address, content, transaction, like, date));
    }
    return itemList;
  }

  ArrayList<ItemList> selectData() throws SQLException {
    // executeQuery�� �������� ResultSet ��ü�� ���� ��ȯ�Ѵ�.
    stmt = conn.createStatement();
    sql = "select * from itemDB";
    rs = stmt.executeQuery(sql);

    // for������ �ٲ㼭 �� �پ� ��µǰ� ������
    while (rs.next()) {
      String id = rs.getString("id");
      String name = rs.getString("name");
      String price = rs.getString("price");
      String address = rs.getString("address");
      String content = rs.getString("content");
      String transaction = rs.getString("transaction");
      String like = rs.getString("like");
      String date = rs.getString("date");

      itemList.add(new ItemList(id, name, price, address, content, transaction, like, date));
    }
    return itemList;
  }

  ArrayList<ItemList> selectData(String column) throws SQLException {
    stmt = conn.createStatement();
    sql = String.format("select * from itemDB order by %s;", column);
    rs = stmt.executeQuery(sql);

    while (rs.next()) {
      String id = rs.getString("id");
      String name = rs.getString("name");
      String price = rs.getString("price");
      String address = rs.getString("address");
      String content = rs.getString("content");
      String transaction = rs.getString("transaction");
      String like = rs.getString("like");
      String date = rs.getString("date");

      itemList.add(new ItemList(id, name, price, address, content, transaction, like, date));
    }
    return itemList;
  }

  ArrayList<ItemList> selectData(String column, String column2) throws SQLException {
    stmt = conn.createStatement();
    sql = String.format("select * from itemDB order by %s, %s;", column, column2);
    rs = stmt.executeQuery(sql);

    while (rs.next()) {
      String id = rs.getString("id");
      String name = rs.getString("name");
      String price = rs.getString("price");
      String address = rs.getString("address");
      String content = rs.getString("content");
      String transaction = rs.getString("transaction");
      String like = rs.getString("like");
      String date = rs.getString("date");

      itemList.add(new ItemList(id, name, price, address, content, transaction, like, date));
    }
    return itemList;
  }

  ArrayList<ItemList> selectData(String column, String column2, String column3)
      throws SQLException {
    stmt = conn.createStatement();
    sql = String.format("select * from itemDB order by %s, %s, %s;", column, column2, column3);
    rs = stmt.executeQuery(sql);

    while (rs.next()) {
      String id = rs.getString("id");
      String name = rs.getString("name");
      String price = rs.getString("price");
      String address = rs.getString("address");
      String content = rs.getString("content");
      String transaction = rs.getString("transaction");
      String like = rs.getString("like");
      String date = rs.getString("date");

      itemList.add(new ItemList(id, name, price, address, content, transaction, like, date));
    }
    return itemList;
  }


  // insertData�� ������
  void insertData() throws SQLException {
    // executeUpdate�� �ݿ��� ���ڵ��� �Ǽ��� ��ȯ�Ѵ�.(�ٷ� insert, update, delete�ϸ� �Ǵϱ� rs�� ���Ϲ��� �ʿ䰡 ����)
    // ��¥ ĭ�� �Է� ���ϸ� �����ڰ� �ڵ����� �Էµȴٴµ� Ȯ���غ���.

    System.out.println("ID�� �Է��ϼ���.");
    String inputId = sc.nextLine();
    System.out.println("��ǰ���� �Է��ϼ���.");

    String inputName = sc.nextLine();
    System.out.println("��������� �Է��ϼ���.");
    String inputPrice = sc.nextLine();
    System.out.println("�ּҸ� �Է��ϼ���.");
    String inputAddress = sc.nextLine();
    System.out.println("��ǰ ������ �߰����ּ���.");
    String description = sc.nextLine();
    String inputContent = description;
    System.out.println("������¸� ��� �ּ���.");
    String inputTransaction = sc.nextLine();
    int intPrice = Integer.parseInt(inputPrice);
    stmt = conn.createStatement();

    String sql =
        String.format("insert into itemDB values(0,'%s','%s', %d,'%s', '%s', '%s',0,now())",
            inputId, inputName, intPrice, inputAddress, inputContent, inputTransaction);
    int result = stmt.executeUpdate(sql);

    System.out.println(result + " ���� �����͸� ó���߽��ϴ�.");
  }

  int insertData2(ItemList itemDB) throws SQLException {
    String sql =
        "insert into ItemDB(name, price, address, content, transaction) values (?,?,?,?,?)";
    pstm = conn.prepareStatement(sql);
    pstm.setString(1, itemDB.name);
    pstm.setString(2, itemDB.price);
    pstm.setString(3, itemDB.address);
    pstm.setString(4, itemDB.content);
    pstm.setString(5, itemDB.transaction);

    return 0;
  }



  // �� �Ű������� �־ delete�� �����ϴ� �� ������? Ŭ���� this�� ����� �ȴ�.
  // �ű�鼭 buyerID�� �߰���. ���� date�� �˾Ƽ� �Էµ�.

  // Ŭ���ϸ�

  // �� ���� ������ ���콺�� �Է��� �� ������ ���� ��. address�� transaction

  void deleteData(String name) throws SQLException {
    stmt = conn.createStatement();
    String sql = String.format("delete from student where name=%s", name);
    int result = stmt.executeUpdate(sql);
    System.out.println(result + " ���� �����Ͱ� �����Ǿ����ϴ�.");



  }



  // update �ʿ��ϸ� ����


  void updateData() {}


  // �Ǹŵ� itemDB�� �� soldItemDB�� �߰� > itemDB �� ����
  void moveData() {
    // String column = null;
    // soldItemDB.insertData(column);

    // Ư���� ���� �����͸� �����ͼ� �����ϰ� ���� ���� ����.



  }

}

