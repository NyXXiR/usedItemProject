package usedItemProject;

import java.sql.SQLException;

public class SelectData extends ItemDB{
	 // ��ü select, �򰹼���(like), ��Ͻð���(date), �������ݼ�(price) ����
	  // select���� order by ��Ʈ�� �߰��ϴ� ������� ����

	//����Ʈ�����ʹ� ��ȣ �ȿ� �ִ� ���� ���� ���� order by�� �߰���. �ִ� 3������ ���� ����

	SelectData() throws ClassNotFoundException, SQLException {
		  // executeQuery�� �������� ResultSet ��ü�� ���� ��ȯ�Ѵ�.
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery("select * from itemDB");
	    String str = null;

	    while (rs.next()) {
	      String id = rs.getString("id");
	      String name = rs.getString("name");
	      int price = rs.getInt("price");
	      String address = rs.getString("address");

	      str += String.format("[%s] %s %d %s \n", id, name, price, address);
	    }

	 
	    
		// TODO Auto-generated constructor stub
	}

}
