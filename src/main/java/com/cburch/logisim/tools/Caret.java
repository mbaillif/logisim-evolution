/**
 * ***************************************************************************** This file is part
 * of logisim-evolution.
 *
 * <p>logisim-evolution is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * <p>logisim-evolution is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * <p>You should have received a copy of the GNU General Public License along with
 * logisim-evolution. If not, see <http://www.gnu.org/licenses/>.
 *
 * <p>Original code by Carl Burch (http://www.cburch.com), 2011. Subsequent modifications by: +
 * College of the Holy Cross http://www.holycross.edu + Haute École Spécialisée Bernoise/Berner
 * Fachhochschule http://www.bfh.ch + Haute École du paysage, d'ingénierie et d'architecture de
 * Genève http://hepia.hesge.ch/ + Haute École d'Ingénierie et de Gestion du Canton de Vaud
 * http://www.heig-vd.ch/
 * *****************************************************************************
 */
package com.cburch.logisim.tools;

import com.cburch.logisim.data.Bounds;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Caret {
  // listener methods
  public void addCaretListener(CaretListener e);

  public void cancelEditing();

  // finishing
  public void commitText(String text);

  public void draw(Graphics g);

  public Bounds getBounds(Graphics g);

  // query/Graphics methods
  public String getText();

  public void keyPressed(KeyEvent e);

  public void keyReleased(KeyEvent e);

  public void keyTyped(KeyEvent e);

  public void mouseDragged(MouseEvent e);

  // events to handle
  public void mousePressed(MouseEvent e);

  public void mouseReleased(MouseEvent e);

  public void removeCaretListener(CaretListener e);

  public void stopEditing();
}
