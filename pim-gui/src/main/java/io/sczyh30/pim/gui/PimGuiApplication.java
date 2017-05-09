package io.sczyh30.pim.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * GUI application of personal information manager.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class PimGuiApplication extends Application {

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
}
