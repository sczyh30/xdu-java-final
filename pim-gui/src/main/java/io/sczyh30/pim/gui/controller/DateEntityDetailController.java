package io.sczyh30.pim.gui.controller;

import io.sczyh30.pim.common.date.DateUtils;
import io.sczyh30.pim.common.util.Utils;
import io.sczyh30.pim.entity.EntityWithDate;
import io.sczyh30.pim.entity.PIMAppointment;
import io.sczyh30.pim.entity.PIMTodo;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * The UI controller for scene showing date entity details.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class DateEntityDetailController extends BaseController implements Initializable {

  private SimpleObjectProperty<LocalDate> date = new SimpleObjectProperty<>();

  @FXML
  private Label dateLabel;
  @FXML
  private TableView<PIMAppointment> appTableView;
  @FXML
  private TableView<PIMTodo> todoTableView;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    date.addListener((obs, oldDate, newDate) -> onDateChange(newDate));
  }

  /**
   * Hook function on date change.
   *
   * @param newDate the new date instance
   */
  private void onDateChange(LocalDate newDate) {
    dateLabel.setText(DateUtils.dateToString(newDate));
    // Use cache here.
    applyData(Utils.getItemsForDate(context.getEntityList(), newDate));
  }

  /**
   * Apply data to the table view.
   *
   * @param list list of entities
   */
  private void applyData(List<EntityWithDate> list) {
    Platform.runLater(() -> {
      appTableView.getItems().addAll(
        list.stream() // NOTE: Bad performance! Use groupingBy operator to optimize this.
          .filter(e -> e instanceof PIMAppointment)
          .map(e -> (PIMAppointment) e)
          .collect(Collectors.toList())
      );
      todoTableView.getItems().addAll(
        list.stream()
          .filter(e -> e instanceof PIMTodo)
          .map(e -> (PIMTodo) e)
          .collect(Collectors.toList())
      );
    });
  }

  public LocalDate getDate() {
    return date.get();
  }

  public SimpleObjectProperty<LocalDate> dateProperty() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date.set(date);
  }
}
