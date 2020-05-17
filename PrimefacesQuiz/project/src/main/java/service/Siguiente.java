package service;

//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;

//@ManagedBean(name = "sig")
//@SessionScoped

public class Siguiente {
  private int num;
  public Siguiente() {
    num=0;
  }
  public void incrNum() {
    num++;
  }
  public void setNum(int p) {
    num=p;
  }
  public int getNum() {
    return num;
  }
  public void initNum() {
    num=1;
  }
}
