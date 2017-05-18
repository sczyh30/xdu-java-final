package io.sczyh30.pim.gui.view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Calendar view component.
 */
public class CalendarView extends BorderPane {

  private final ObjectProperty<YearMonth> month = new SimpleObjectProperty<>();
  private final Locale locale = Locale.getDefault();
  private final GridPane calendar;

  public CalendarView(YearMonth month) {
    super();
    getStyleClass().add("calendar");
    calendar = new GridPane();
    calendar.setGridLinesVisible(true);
    calendar.getStyleClass().add("calendar-grid");

    Label header = new Label();
    header.setMaxWidth(Double.MAX_VALUE);
    header.getStyleClass().add("calendar-header");

    this.month.addListener((obs, oldMonth, newMonth) -> rebuildCalendar());

    setTop(header);
    setCenter(calendar);

    getStylesheets().add(getClass().getResource("/assets/calendar.css").toExternalForm());

    setMonth(month);

    header.textProperty().bind(Bindings.createStringBinding(() ->
        this.month.get().format(DateTimeFormatter.ofPattern("MMMM yyyy", locale)),
      this.month));
  }

  public CalendarView() {
    this(YearMonth.now());
  }

  public void nextMonth() {
    month.set(month.get().plusMonths(1));
  }

  public void previousMonth() {
    month.set(month.get().minusMonths(1));
  }

  private void rebuildCalendar() {
    calendar.getChildren().clear();

    WeekFields weekFields = WeekFields.of(locale);

    LocalDate first = month.get().atDay(1);

    int dayOfWeekOfFirst = first.get(weekFields.dayOfWeek());

    // column headers:
    for (int dayOfWeek = 1; dayOfWeek <= 7; dayOfWeek++) {
      LocalDate date = first.minusDays(dayOfWeekOfFirst - dayOfWeek);
      DayOfWeek day = date.getDayOfWeek();
      Label label = new Label(day.getDisplayName(TextStyle.SHORT_STANDALONE, locale));
      label.getStyleClass().add("calendar-day-header");
      // label.setPrefWidth(100);
      GridPane.setHalignment(label, HPos.CENTER);
      calendar.add(label, dayOfWeek - 1, 0);
    }

    LocalDate firstDisplayedDate = first.minusDays(dayOfWeekOfFirst - 1);
    LocalDate last = month.get().atEndOfMonth();
    int dayOfWeekOfLast = last.get(weekFields.dayOfWeek());
    LocalDate lastDisplayedDate = last.plusDays(7 - dayOfWeekOfLast);

    PseudoClass beforeMonth = PseudoClass.getPseudoClass("before-display-month");
    PseudoClass afterMonth = PseudoClass.getPseudoClass("after-display-month");

    for (LocalDate date = firstDisplayedDate; !date.isAfter(lastDisplayedDate); date = date.plusDays(1)) {
      CalendarCell cell = new CalendarCell(date);
      cell.getStyleClass().add("calendar-cell");
      cell.pseudoClassStateChanged(beforeMonth, date.isBefore(first));
      cell.pseudoClassStateChanged(afterMonth, date.isAfter(last));

      GridPane.setHalignment(cell, HPos.CENTER);

      int dayOfWeek = date.get(weekFields.dayOfWeek());
      int daysSinceFirstDisplayed = (int) firstDisplayedDate.until(date, ChronoUnit.DAYS);
      int weeksSinceFirstDisplayed = daysSinceFirstDisplayed / 7;

      calendar.add(cell, dayOfWeek - 1, weeksSinceFirstDisplayed + 1);
    }
  }

  public final ObjectProperty<YearMonth> monthProperty() {
    return this.month;
  }

  public final YearMonth getMonth() {
    return this.monthProperty().get();
  }

  public final void setMonth(final YearMonth month) {
    this.monthProperty().set(month);
  }
}
