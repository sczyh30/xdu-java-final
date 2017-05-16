package io.sczyh30.pim.gui.controller;

import io.sczyh30.pim.client.PimService;
import io.sczyh30.pim.entity.PIMAppointment;
import io.sczyh30.pim.entity.PIMTodo;
import io.sczyh30.pim.gui.PimServiceContext;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

/**
 * UI controller for adding todo.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class AddTodoController extends BaseController {

  @FXML
  private TextField todoTextField;
  @FXML
  private DatePicker datePicker;

  @FXML
  public void addTodo(MouseEvent event) {
    String owner = getOwner();
    LocalDate date = datePicker.getValue();
    String todoText = todoTextField.getText();
    service.add(new PIMTodo(todoText, date).setOwner(owner))
      .subscribe(this::showSuccessAlert, this::showErrorAlert);
  }

  @FXML
  public void clearText(MouseEvent event) {
    todoTextField.setText("");
  }
}
