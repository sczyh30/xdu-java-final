package io.sczyh30.pim.gui;

import io.sczyh30.pim.client.PimService;
import io.sczyh30.pim.gui.util.DialogUtil;
import io.sczyh30.pim.gui.view.CalendarView;
import io.vertx.core.json.JsonObject;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * GUI application of personal information manager.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class PimGuiApplication extends Application {

  private final PimService service = PimServiceContext.getService();
  private final PimServiceContext context = PimServiceContext.getContext();

  @Override
  public void start(Stage primaryStage) throws Exception {
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
    // Build the scene and run.
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
    primaryStage.setOnCloseRequest(e -> service.close()); // Close hook.
  }

  private void initRoot(GridPane root) {
    root.setHgap(10);
    root.setVgap(10);
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
    MenuItem ma1 = new MenuItem("Add appointment");
    MenuItem ma2 = new MenuItem("Add contact");
    MenuItem ma3 = new MenuItem("Add todo");
    MenuItem ma4 = new MenuItem("Add note");
    MenuItem maSetOwner = new MenuItem("Set owner");
    MenuItem ma5 = new MenuItem("Exit");
    ma1.setOnAction(e -> DialogUtil.showAddAppointment());
    ma2.setOnAction(e -> DialogUtil.showAddContact());
    ma3.setOnAction(e -> DialogUtil.showAddTodo());
    ma4.setOnAction(e -> DialogUtil.showAddNote());
    ma5.setOnAction(e -> pStage.close());
    editMenu.getItems().addAll(ma1, ma2, ma3, ma4, new SeparatorMenuItem(), maSetOwner, new SeparatorMenuItem(), ma5);
    Menu viewMenu = new Menu("_View");
    MenuItem mb1 = new MenuItem("View notes");
    MenuItem mb2 = new MenuItem("View contacts");
    mb1.setOnAction(e -> DialogUtil.showAllNoteDialog());
    mb2.setOnAction(e -> DialogUtil.showAllContactDialog());
    viewMenu.getItems().addAll(mb1, mb2);
    Menu helpMenu = new Menu("_Help");
    MenuItem mc1 = new MenuItem("About");
    mc1.setOnAction(e -> DialogUtil.showAbout());
    helpMenu.getItems().add(mc1);
    menuBar.getMenus().addAll(editMenu, viewMenu, helpMenu);
    return menuBar;
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
