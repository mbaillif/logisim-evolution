package com.cburch.logisim.gui.icons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class PorIcon extends BaseIcon {
  
  private int opp;
  
  public PorIcon(int operation) {
    opp =  operation;
  }
    
  protected void paintIcon(Graphics2D g2) {
    int tmp1 = scale(15);
    int tmp2 = scale(2);
    int tmp3 = scale(13);
    g2.drawRect(0, 0, tmp1, tmp1);
    g2.setStroke(new BasicStroke(scale(1f)));

    tmp1 = scale(12);
    g2.drawLine(tmp2, scale(3), tmp2, tmp3);
    g2.drawLine(tmp2, tmp1, tmp3, tmp1);
    g2.drawLine(tmp1, scale(11), tmp1, tmp3);
    g2.setStroke(new BasicStroke(scale(1.5f)));
    g2.setColor(Color.RED);
   
    tmp2 = scale(8);
    tmp3 = scale(4);
    int tmp4 = scale(11);
    int tmp5 = scale(3);
    if (opp==2) { //LowToHigh
      g2.drawPolyline(new int[] {tmp5, tmp2, tmp2, tmp1}, 
          new int[] {tmp4, tmp4, tmp3 ,tmp3},4);
    } else {      //HighToLow
      g2.drawPolyline(new int[] {tmp5, tmp2, tmp2, tmp1}, 
          new int[] {tmp3, tmp3, tmp4 , tmp4},4);
    }
  }
  
  protected void paintIcon(Graphics2D g2, Object par) {
     opp = (int)par;
     paintIcon(g2);
  }
}
