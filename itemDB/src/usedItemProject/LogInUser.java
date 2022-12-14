package usedItemProject;

public class LogInUser { // �떛湲��넠 媛앹껜 243p.
  private static LogInUser logInUser = new LogInUser();
  private String id;
  private String pwd;
  private String name;
  private String address;
  private String nick;
  private int phone;

  private LogInUser() {}

  public static LogInUser getInstance() {
    return logInUser;
  }


  // 媛� �젙蹂� getter,setter 硫붿냼�뱶
  public String getId() {
    return id;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public int getPhone() {
    return phone;
  }

  public void setPhone(int phone) {
    this.phone = phone;
  }

  public void setId(String id) {
    this.id = id;
  }



}
