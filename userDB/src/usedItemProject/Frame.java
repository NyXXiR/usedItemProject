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


// 둘 이상의 패널을 각 Card로 등록한 뒤 원하는 패널을 부르는 방법
// cards.show(Container parent, String name);
// - parent : CardLayout이 있는 컨테이너. 여기선 메인프레임의 Pane -> F.getContentPane()
// - name : CardLayout에 패널 등록시 지정한 String 값. 여기선 One, Two
//
// 패널1과 패널2의 Button Action에
// - F.getCardLayout().show(F.getContentPane(), "One");
// - F.getCardLayout().show(F.getContentPane(), "Two");
//
// getCardLayout()는 구현되 있지 않다.
//
// 구현시 메인 프레임에
// public CardLayout getCardLayout() {
// return cards;
// }
