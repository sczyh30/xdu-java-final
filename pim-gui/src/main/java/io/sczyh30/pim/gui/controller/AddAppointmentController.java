package io.sczyh30.pim.gui.controller;

import io.sczyh30.pim.client.PimService;
import io.sczyh30.pim.entity.PIMAppointment;
import io.sczyh30.pim.gui.PimServiceContext;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

/**
 * UI controller for adding appointment.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class AddAppointmentController extends BaseController {

  @FXML
  private TextField descField;
  @FXML
  private DatePicker datePicker;

  @FXML
  public void addAppointment(MouseEvent event) {
    String owner = getOwner();
    LocalDate date = datePicker.getValue();
    String description = descField.getText();
    service.add(new PIMAppointment(date, description).setOwner(owner))
      .subscribe(this::showSuccessAlert, this::showErrorAlert);
  }

  @FXML
  public void clearText(MouseEvent event) {
    descField.setText("");
  }
}
