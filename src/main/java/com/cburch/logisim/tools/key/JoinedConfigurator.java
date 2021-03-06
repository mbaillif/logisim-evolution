/*
 * This file is part of logisim-evolution.
 *
 * Logisim-evolution is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Logisim-evolution is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with logisim-evolution. If not, see <http://www.gnu.org/licenses/>.
 *
 * Original code by Carl Burch (http://www.cburch.com), 2011.
 * Subsequent modifications by:
 *   + College of the Holy Cross
 *     http://www.holycross.edu
 *   + Haute École Spécialisée Bernoise/Berner Fachhochschule
 *     http://www.bfh.ch
 *   + Haute École du paysage, d'ingénierie et d'architecture de Genève
 *     http://hepia.hesge.ch/
 *   + Haute École d'Ingénierie et de Gestion du Canton de Vaud
 *     http://www.heig-vd.ch/
 */

package com.cburch.logisim.tools.key;

public class JoinedConfigurator implements KeyConfigurator, Cloneable {
  public static JoinedConfigurator create(KeyConfigurator a, KeyConfigurator b) {
    return new JoinedConfigurator(new KeyConfigurator[] {a, b});
  }

  public static JoinedConfigurator create(KeyConfigurator[] configs) {
    return new JoinedConfigurator(configs);
  }

  private KeyConfigurator[] handlers;

  private JoinedConfigurator(KeyConfigurator[] handlers) {
    this.handlers = handlers;
  }

  @Override
  public JoinedConfigurator clone() {
    JoinedConfigurator ret;
    try {
      ret = (JoinedConfigurator) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      return null;
    }
    int len = this.handlers.length;
    ret.handlers = new KeyConfigurator[len];
    for (var i = 0; i < len; i++) {
      ret.handlers[i] = this.handlers[i].clone();
    }
    return ret;
  }

  public KeyConfigurationResult keyEventReceived(KeyConfigurationEvent event) {
    final var hs = handlers;
    if (event.isConsumed()) {
      return null;
    }
    for (KeyConfigurator h : hs) {
      final var result = h.keyEventReceived(event);
      if (result != null || event.isConsumed()) {
        return result;
      }
    }
    return null;
  }
}
