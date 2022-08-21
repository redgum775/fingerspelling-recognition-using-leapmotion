package fingerspelling_recognition;

import fingerspelling_recognition.javafx.FXMLController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LeapApp extends Application {

	//private FXMLController controller;

	@Override
    public void start(Stage primaryStage) throws InterruptedException, Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load());
        primaryStage.setScene(scene);

        // ロードしたFXMLファイルに関連づくControllerを取得
        final FXMLController controller = (FXMLController) fxmlLoader.getController();
        controller.handSign();

        primaryStage.setTitle("HandSign-master");
        primaryStage.show();

        primaryStage.setOnCloseRequest((WindowEvent event) -> {
        	System.out.println("Exit System");
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}