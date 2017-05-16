package io.sczyh30.pim.gui.controller;

import io.sczyh30.pim.client.PimService;
import io.sczyh30.pim.gui.PimServiceContext;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Base UI controller.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public abstract class BaseController {

  protected final PimService service = PimServiceContext.getService();

  protected void showErrorAlert(Throwable ex) {
    Platform.runLater(() -> {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Oops... Unexpected error occurred...");
      String cause = ex.getMessage() == null ? "unknown" : ex.getMessage();
      alert.setContentText("Detail: " + cause);
      alert.show();
      ex.printStackTrace();
    });
  }

  protected void showSuccessAlert(String message) {
    Platform.runLater(() -> {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Operation successful");
      alert.setContentText(message);
      alert.show();
    });
  }

  protected void showSuccessAlert() {
    showSuccessAlert("Add Successful.");
  }

  protected String getOwner() {
    return PimServiceContext.getContext().getOwner();
  }
}
