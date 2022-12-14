package usedItemProject;


// �ϴ� �� String���� �ް� �ʿ��ϸ� int�� ��ȯ����
public class ItemList {
  String id;
  String name;
  String price;
  String address;
  String content;
  String transaction;
  String like;
  String date;

  @Override
  public String toString() {
    return "ItemList [id=" + id + ", name=" + name + ", price=" + price + ", address=" + address
        + ", content=" + content + ", transaction=" + transaction + ", like=" + like + ", date="
        + date + "]";
  }

  ItemList(String id, String name, String price, String address, String content, String transaction,
      String like, String date) {
    super();
    this.id = id;
    this.name = name;
    this.price = price;
    this.address = address;
    this.content = content;
    this.transaction = transaction;
    this.like = like;

  }
}
