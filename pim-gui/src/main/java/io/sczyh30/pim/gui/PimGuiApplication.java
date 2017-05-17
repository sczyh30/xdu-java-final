package io.sczyh30.pim.gui;

import io.sczyh30.pim.client.PimService;
import io.sczyh30.pim.gui.util.DialogUtil;
import io.sczyh30.pim.gui.view.CalendarView;
import io.vertx.core.json.JsonObject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * GUI application of personal information manager.
 * Also represents the main controller.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class PimGuiApplication extends Application {

  private final PimService service = PimServiceContext.getService();
  private final PimServiceContext context = PimServiceContext.getContext();

  @Override
  public void start(Stage primaryStage) {
    // Init the data and prepare UI.
    service.getAll()
      .doAfterTerminate(() -> prepareUI(primaryStage))
      .subscribe(context::setEntityList, DialogUtil::showErrorAlert);
  }

  /**
   * Prepare UI and run the scene.
   *
   * @param primaryStage primary stage
   */
  private void prepareUI(Stage primaryStage) {
    Platform.runLater(() -> {
      primaryStage.setTitle("Personal Information Manager");
      // Prepare root.
      GridPane root = new GridPane();
      initRoot(root);
      // Prepare menu bar.
      MenuBar menuBar = buildMenuBar(primaryStage);
      root.add(menuBar, 0, 0);
      // Prepare calendar view.
      CalendarView calendar = new CalendarView();
      Button next = new Button(">");
      next.setOnAction(e -> calendar.nextMonth());
      Button previous = new Button("<");
      previous.setOnAction(e -> calendar.previousMonth());
      BorderPane calendarPane = new BorderPane(calendar, null, next, null, previous);
      root.add(calendarPane, 0, 2);
      // Init the data cache, build the scene and run.
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.show();
      primaryStage.setOnCloseRequest(e -> service.close()); // Close hook.
    });
  }

  private void initRoot(GridPane root) {
    root.getStyleClass().add("pim-root");
  }

  /**
   * Build the menu bar instance.
   *
   * @param pStage primary stage. Useful for exit hook
   * @return menu bar instance
   */
  private MenuBar buildMenuBar(Stage pStage) {
    MenuBar menuBar = new MenuBar();
    Menu editMenu = new Menu("_New/Edit");
    MenuItem ma1 = new MenuItem("Add Appointment");
    MenuItem ma2 = new MenuItem("Add Contact");
    MenuItem ma3 = new MenuItem("Add Todo");
    MenuItem ma4 = new MenuItem("Add Note");
    MenuItem maSetOwner = new MenuItem("Set Owner");
    MenuItem ma5 = new MenuItem("Exit");
    ma1.setOnAction(e -> DialogUtil.showAddAppointment());
    ma2.setOnAction(e -> DialogUtil.showAddContact());
    ma3.setOnAction(e -> DialogUtil.showAddTodo());
    ma4.setOnAction(e -> DialogUtil.showAddNote());
    maSetOwner.setOnAction(e -> handleChangeOwner());
    ma5.setOnAction(e -> pStage.close());
    editMenu.getItems().addAll(ma1, ma2, ma3, ma4, new SeparatorMenuItem(), maSetOwner, new SeparatorMenuItem(), ma5);
    Menu viewMenu = new Menu("_View");
    MenuItem mb1 = new MenuItem("View All Items");
    MenuItem mb2 = new MenuItem("Refresh");
    mb1.setOnAction(e -> DialogUtil.showItemsDialog());
    mb2.setOnAction(e -> refreshData());
    viewMenu.getItems().addAll(mb1, new SeparatorMenuItem(), mb2);
    Menu helpMenu = new Menu("_Help");
    MenuItem mc1 = new MenuItem("About");
    mc1.setOnAction(e -> DialogUtil.showAbout());
    helpMenu.getItems().add(mc1);
    menuBar.getMenus().addAll(editMenu, viewMenu, helpMenu);
    return menuBar;
  }

  private void refreshData() {
    service.getAll().subscribe(context::setEntityList, DialogUtil::showErrorAlert);
  }

  private void handleChangeOwner() {
    TextInputDialog dialog = new TextInputDialog(context.getOwner());
    dialog.setTitle("Set Owner");
    dialog.setHeaderText("Set Owner");
    dialog.setContentText("Input the new owner name:");
    dialog.showAndWait()
      .ifPresent(context::setOwner);
  }

  /**
   * Read the JSON config from the configuration file.
   *
   * @return configuration in JSON
   */
  public JsonObject readConfig() throws Exception {
    FileChannel channel = new FileInputStream(new File(getClass().getResource("config.json").toURI())).getChannel();
    final int size = (int) channel.size();
    ByteBuffer buffer = ByteBuffer.allocate(size);
    channel.read(buffer);
    channel.close();
    return new JsonObject(new String(buffer.array(), 0, size));
  }

  public static void main(String[] args) {
    launch(args);
  }
}
