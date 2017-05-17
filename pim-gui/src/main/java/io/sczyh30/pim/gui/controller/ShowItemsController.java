package io.sczyh30.pim.gui.controller;

import io.sczyh30.pim.entity.PIMAppointment;
import io.sczyh30.pim.entity.PIMContact;
import io.sczyh30.pim.entity.PIMEntity;
import io.sczyh30.pim.entity.PIMNote;
import io.sczyh30.pim.entity.PIMTodo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * The UI controller for showing PIMEntity items.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class ShowItemsController extends BaseController implements Initializable {

  @FXML
  private TableView<PIMContact> contactTableView;
  @FXML
  private TableView<PIMTodo> todoTableView;
  @FXML
  private TableView<PIMAppointment> appointmentTableView;
  @FXML
  private TableView<PIMNote> noteTableView;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    List<PIMEntity> list = context.getEntityList();
    contactTableView.getItems().addAll(
      list.stream()
        .filter(e -> e instanceof PIMContact)
        .map(e -> (PIMContact) e)
        .collect(Collectors.toList())
    );
    todoTableView.getItems().addAll(
      list.stream()
        .filter(e -> e instanceof PIMTodo)
        .map(e -> (PIMTodo) e)
        .collect(Collectors.toList())
    );
    appointmentTableView.getItems().addAll(
      list.stream()
        .filter(e -> e instanceof PIMAppointment)
        .map(e -> (PIMAppointment) e)
        .collect(Collectors.toList())
    );
    noteTableView.getItems().addAll(
      list.stream()
        .filter(e -> e instanceof PIMNote)
        .map(e -> (PIMNote) e)
        .collect(Collectors.toList())
    );
  }
}
