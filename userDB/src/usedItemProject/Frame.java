package usedItemProject;

import java.awt.CardLayout;
import javax.swing.JFrame;

public class Frame extends JFrame {
  private CardLayout cards = new CardLayout();

  public Frame() {
    setSize(330, 100);
    getContentPane().setLayout(cards);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);

    getContentPane().add("One", new P_One(this));
    getContentPane().add("Two", new P_Two(this));

    setVisible(true);
  }

  public void changePanel() {
    cards.next(this.getContentPane());
  }
}


// �� �̻��� �г��� �� Card�� ����� �� ���ϴ� �г��� �θ��� ���
// cards.show(Container parent, String name);
// - parent : CardLayout�� �ִ� �����̳�. ���⼱ ������������ Pane -> F.getContentPane()
// - name : CardLayout�� �г� ��Ͻ� ������ String ��. ���⼱ One, Two
//
// �г�1�� �г�2�� Button Action��
// - F.getCardLayout().show(F.getContentPane(), "One");
// - F.getCardLayout().show(F.getContentPane(), "Two");
//
// getCardLayout()�� ������ ���� �ʴ�.
//
// ������ ���� �����ӿ�
// public CardLayout getCardLayout() {
// return cards;
// }
