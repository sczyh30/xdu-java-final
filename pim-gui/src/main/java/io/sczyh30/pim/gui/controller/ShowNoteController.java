package io.sczyh30.pim.gui.controller;

import io.sczyh30.pim.entity.PIMNote;
import io.sczyh30.pim.gui.util.DialogUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The UI controller for showing PIM notes.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class ShowNoteController extends BaseController implements Initializable {

  @FXML
  private TableView<PIMNote> tableView;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    service.getNotes().subscribe(r -> {
      Platform.runLater(() -> {
        tableView.getItems().addAll(r);
      });
    }, DialogUtil::showErrorAlert);
  }
}
