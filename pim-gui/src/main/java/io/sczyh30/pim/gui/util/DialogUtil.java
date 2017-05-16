package io.sczyh30.pim.gui.util;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Util class for showing dialog.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public final class DialogUtil {

  private DialogUtil() {
  }

  public static void showAddAppointment() {
    try {
      Stage stage = new Stage();
      Parent root = FXMLLoader.load(DialogUtil.class.getResource("/layout/add_note.fxml"));
      stage.setTitle("Add a appointment");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception ex) {
      showErrorAlert(ex);
    }
  }

  public static void showAddContact() {
    try {
      Stage stage = new Stage();
      Parent root = FXMLLoader.load(DialogUtil.class.getResource("/layout/add_contact.fxml"));
      stage.setTitle("Add a contact");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception ex) {
      showErrorAlert(ex);
    }
  }

  public static void showAddTodo() {
    try {
      Stage stage = new Stage();
      Parent root = FXMLLoader.load(DialogUtil.class.getResource("/layout/add_todo.fxml"));
      stage.setTitle("Add a todo item");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception ex) {
      showErrorAlert(ex);
    }
  }

  public static void showAddNote() {
    try {
      Stage stage = new Stage();
      Parent root = FXMLLoader.load(DialogUtil.class.getResource("/layout/add_note.fxml"));
      stage.setTitle("Add a note");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception ex) {
      showErrorAlert(ex);
    }
  }

  public static void showAllNoteDialog() {
    try {
      Stage stage = new Stage();
      Parent root = FXMLLoader.load(DialogUtil.class.getResource("/layout/show_note.fxml"));
      stage.setTitle("View notes");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception ex) {
      showErrorAlert(ex);
    }
  }

  public static void showAllContactDialog() {
    try {
      Stage stage = new Stage();
      Parent root = FXMLLoader.load(DialogUtil.class.getResource("/layout/show_contact.fxml"));
      stage.setTitle("View contacts");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception ex) {
      showErrorAlert(ex);
    }
  }

  public static void showErrorAlert(Throwable ex) {
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

  public static void showSuccessAlert(String message) {
    Platform.runLater(() -> {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Operation successful");
      alert.setContentText(message);
      alert.show();
    });
  }

  public static void showAbout() {
    Platform.runLater(() -> {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("About PIM");
      alert.setHeaderText("Personal Information Manager 1.0 (Java Version)");
      alert.setContentText("Author: 赵奕豪 (14130140389).\nAll rights reserved.");
      alert.show();
    });
  }

  public static void showSuccessAlert() {
    showSuccessAlert("Add Successful.");
  }
}
