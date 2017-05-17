package io.sczyh30.pim.gui.view;

import io.sczyh30.pim.common.util.Utils;
import io.sczyh30.pim.entity.EntityWithDate;
import io.sczyh30.pim.entity.PIMAppointment;
import io.sczyh30.pim.entity.PIMTodo;
import io.sczyh30.pim.gui.PimServiceContext;
import io.sczyh30.pim.gui.util.DialogUtil;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import java.time.LocalDate;
import java.util.List;

/**
 * Calendar cell view.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class CalendarCell extends GridPane {

  private final PimServiceContext context = PimServiceContext.getContext();

  private LocalDate localDate;
  private final Label dayLabel;
  private final Label itemLabel;

  public CalendarCell(LocalDate date) {
    this.localDate = date;
    this.dayLabel = new Label(String.valueOf(localDate.getDayOfMonth()));
    dayLabel.setAlignment(Pos.CENTER);
    dayLabel.setTextAlignment(TextAlignment.CENTER);
    this.itemLabel = new Label();
    itemLabel.getStyleClass().add("calendar-cell-entity-item");
    this.setVgap(3);
    this.add(dayLabel, 0, 0);
    this.add(itemLabel, 0, 2);
    List<EntityWithDate> r = Utils.getItemsForDate(context.getEntityList(), localDate);
    if (!r.isEmpty()) {
      int size = r.size();
      if (size == 1) {
        Platform.runLater(() -> itemLabel.setText(getDisplayFromEntity(r.get(0))));
      } else {
        Platform.runLater(() -> itemLabel.setText(String.format("Total %d items", r.size())));
      }
      itemLabel.setOnMouseClicked(this::handleItemDbClick);
    }
  }

  private void handleItemDbClick(MouseEvent event) {
    if (event.getClickCount() == 2) { // Double click
      DialogUtil.showEntityWithCertainDate(localDate);
    }
  }

  private <E> String getDisplayFromEntity(E entity) {
    if (entity instanceof PIMTodo) {
      return ((PIMTodo) entity).getText();
    } else if (entity instanceof PIMAppointment) {
      return ((PIMAppointment) entity).getDescription();
    } else {
      return "";
    }
  }
}
