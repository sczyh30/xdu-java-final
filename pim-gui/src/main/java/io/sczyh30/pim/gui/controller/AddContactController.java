package io.sczyh30.pim.gui.controller;

import io.sczyh30.pim.entity.PIMContact;
import io.sczyh30.pim.gui.util.DialogUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * UI controller for adding contact.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class AddContactController extends BaseController {

  @FXML
  private TextField firstNameField;
  @FXML
  private TextField lastNameField;
  @FXML
  private TextField emailField;

  @FXML
  public void addContact(MouseEvent event) {
    String owner = getOwner();
    service.add(new PIMContact(firstNameField.getText(), lastNameField.getText(), emailField.getText())
      .setOwner(owner))
      .subscribe(DialogUtil::showSuccessAlert, DialogUtil::showErrorAlert);
  }

  @FXML
  public void clearText(MouseEvent event) {
    firstNameField.setText("");
    lastNameField.setText("");
    emailField.setText("");
  }
}
