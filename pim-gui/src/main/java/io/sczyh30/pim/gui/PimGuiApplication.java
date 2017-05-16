package io.sczyh30.pim.gui;

import io.sczyh30.pim.client.PimService;
import io.vertx.core.json.JsonObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    Parent root = FXMLLoader.load(getClass().getResource("/layout/main.fxml"));
    primaryStage.setTitle("Personal Information Manager");
    primaryStage.setScene(new Scene(root, 300, 275));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  public JsonObject readConfig() throws Exception {
    FileChannel channel = new FileInputStream(new File(getClass().getResource("config.json").toURI())).getChannel();
    final int size = (int) channel.size();
    ByteBuffer buffer = ByteBuffer.allocate(size);
    channel.read(buffer);
    channel.close();
    return new JsonObject(new String(buffer.array(), 0, size));
  }
}
