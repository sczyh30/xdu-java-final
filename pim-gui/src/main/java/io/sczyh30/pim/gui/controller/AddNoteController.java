package io.sczyh30.pim.gui.controller;

import io.sczyh30.pim.entity.PIMNote;
import io.sczyh30.pim.gui.util.DialogUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * UI controller for adding note.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class AddNoteController extends BaseController {

  @FXML
  private TextField noteTextField;

  @FXML
  public void addNote(MouseEvent event) {
    String owner = getOwner();
    service.add(new PIMNote(noteTextField.getText())
      .setOwner(owner))
      .subscribe(DialogUtil::showSuccessAlert, DialogUtil::showErrorAlert);
  }

  @FXML
  public void clearText(MouseEvent event) {
    noteTextField.setText("");
  }
}
