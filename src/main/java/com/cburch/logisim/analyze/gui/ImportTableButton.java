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

package com.cburch.logisim.analyze.gui;

import static com.cburch.logisim.analyze.Strings.S;

import com.cburch.logisim.analyze.file.TruthtableCsvFile;
import com.cburch.logisim.analyze.file.TruthtableTextFile;
import com.cburch.logisim.analyze.model.AnalyzerModel;
import com.cburch.logisim.gui.generic.OptionPane;
import com.cburch.logisim.util.JFileChoosers;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ImportTableButton extends JButton {

  private static final long serialVersionUID = 1L;

  private final JFrame parent;
  private final AnalyzerModel model;

  ImportTableButton(JFrame parent, AnalyzerModel model) {
    this.parent = parent;
    this.model = model;
    addActionListener(
        event -> doLoad());
  }

  void localeChanged() {
    setText(S.get("importTableButton"));
  }

  private File lastFile = null;

  void doLoad() {
    if (lastFile == null) {
      final var c = model.getCurrentCircuit();
      if (c != null) lastFile = new File(c.getName() + ".txt");
      else lastFile = new File("truthtable.txt");
    }
    final var chooser = JFileChoosers.createSelected(lastFile);
    chooser.setDialogTitle(S.get("openButton"));
    chooser.addChoosableFileFilter(TruthtableTextFile.FILE_FILTER);
    chooser.addChoosableFileFilter(TruthtableCsvFile.FILE_FILTER);
    chooser.setFileFilter(TruthtableTextFile.FILE_FILTER);
    final var choice = chooser.showOpenDialog(parent);
    if (choice == JFileChooser.APPROVE_OPTION) {
      final var file = chooser.getSelectedFile();
      if (file.isDirectory()) {
        OptionPane.showMessageDialog(
            parent,
            S.get("notFileMessage", file.getName()),
            S.get("openErrorTitle"),
            OptionPane.OK_OPTION);
        return;
      }
      if (!file.exists() || !file.canRead()) {
        OptionPane.showMessageDialog(
            parent,
            S.get("cantReadMessage", file.getName()),
            S.get("openErrorTitle"),
            OptionPane.OK_OPTION);
        return;
      }
      try {
        final var fileName = file.getName();
        final var idx = fileName.lastIndexOf(".");
        final var ext = fileName.substring(idx + 1);
        if (ext.equals("txt")) {
          TruthtableTextFile.doLoad(file, model, parent);
        } else if (ext.equals("csv")) {
          TruthtableCsvFile.doLoad(file, model, parent);
        } else {
          OptionPane.showMessageDialog(
              parent,
              S.get("DoNotKnowHowto", fileName),
              S.get("openErrorTitle"),
              OptionPane.ERROR_MESSAGE);
          return;
        }
        lastFile = file;
      } catch (IOException e) {
        OptionPane.showMessageDialog(parent, e.getMessage(), S.get("openErrorTitle"), OptionPane.ERROR_MESSAGE);
      }
    }
  }
}
