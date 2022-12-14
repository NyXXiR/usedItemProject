package usedItemProject;

import java.sql.SQLException;

public class SelectData extends ItemDB{
	 // 전체 select, 찜갯수별(like), 등록시간순(date), 낮은가격순(price) 정렬
	  // select문에 order by 멘트를 추가하는 방식으로 구현

	//셀렉트데이터는 괄호 안에 있는 값을 쿼리 끝에 order by로 추가함. 최대 3개까지 적용 가능

	SelectData() throws ClassNotFoundException, SQLException {
		  // executeQuery는 수행결과로 ResultSet 객체의 값을 반환한다.
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
