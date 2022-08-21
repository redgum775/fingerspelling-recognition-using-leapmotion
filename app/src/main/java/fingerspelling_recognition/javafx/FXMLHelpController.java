package fingerspelling_recognition.javafx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FXMLHelpController {

	@FXML Label title;
	@FXML Label content;

	@FXML
	protected void onCloseAction(ActionEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
	}

	@FXML
	protected void onTitleOnclicked(ActionEvent event) {
		title.setText("当研究とは");
		content.setText("当研究ではLeap Motionを用いて指文字の認識を行い、\n聴覚障がい者のコミュニケーションを支援するツールの開発を行っています。");
	}

	@FXML
	protected void onDrawErrorClicked(ActionEvent event){
		title.setText("手が正常に表示されない");
		content.setText("Leap Motion と手の距離は10～20センチ程でしょうか。"
				+ "近すぎたり、離れすぎたりすると読み取れなくなることがあります。\n\n"
				+ "手の形が崩れた、手が読み込みこまれなくなったなどの場合は、"
				+ "手をパーの形にして再度Leap Motionに手をかざしてください。\n");
	}

	@FXML
	protected void onBackgroundClicked(ActionEvent event) {
		title.setText("背景");
		content.setText("現在、音声言語で話をする健聴者と、手話、指文字で話をする聴覚障がい者との間ではコミュニケーションが難しく、"
				+ "様々な工夫がなされています。\n\n"
				+ "実際に、音声認識を使って文字情報に変換することで、健聴者が話している内容を聴覚障がい者にも分かるようにする技術もあります。\n\n"
				+ "しかし、聴覚障がい者が発話するとき、PCやスマホを使って入力する方法では音声入力のような滑らかさを確保することが難しく、"
				+ "結果私の理想とするスムーズなコミュニケーションにほど遠くなっています。\n\n"
				+ "よって、よりスムーズなコミュニケーションを実現したいと思い、当研究をすることに至りました。");
	}

	@FXML
	protected void onPurposeClicked(ActionEvent event) {
		title.setText("目的");
		content.setText("当研究の目的は、音声入力の様に聴覚障がい者でもスムーズにできる入力方法を提案することで、"
				+ "健聴者と聴覚障がい者間のコミュニケーションの滑らかさを実現できるようにすることです。");
	}

	@FXML
	protected void onMethodClicked(ActionEvent event) {
		title.setText("手法");
		content.setText("Leap Motionを使うことで、手の指の関節の座標を拾得することができ、"
				+ "その座標情報から、指の曲げ伸び、指の向きなどの情報を得ることができます。"
				+ "それらの情報を使って、指文字を認識しています。");
	}

	@FXML
	protected void onHandSignClicked(ActionEvent event) {
		title.setText("指文字の認識精度");
		content.setText("コツが必要、認識精度が悪い指文字もありますが、改善により初版と比べて良くなっています。\n"
				+ "「の」,「り」,「を」,「ん」については現在対応しておりません。\n\n"
				+ "認識成功率に関しては、実験を実施していないので正確ではないのですが、9割近くの指文字もあれば、1割もないだろうという指文字まであります。\n\n"
				+ "連続して指文字をすると、手のモーションキャプチャーが正確じゃなくなり、認識精度が落ちることもあります。\n\n");
	}

	@FXML
	protected void onSupportClicked(ActionEvent event) {
		title.setText("対応している指文字");
		content.setText("「の」,「り」,「を」,「ん」については現在対応しておりません。");
	}

	@FXML
	protected void onQuestion3Clicked(ActionEvent event) {
		title.setText("一音の区切りの方法");
		content.setText("指文字には手の動きが止まるタイミングがあり、そのタイミングの検出をするために手の動き量を常時観察しています。"
				+ "そして、手の動きが止まったのを検出し、そのタイミングで識別をしています。");
	}

	@FXML
	protected void onWeakClicked(ActionEvent event) {
		title.setText("読み取りにくい指文字");
		content.setText("・Leap Motionからみて奥行きがある指文字\n"
				+ "　　　例：「お」,「か」など\n\n"
				+ "・手が下向きになる指文字\n"
				+ "　　　例：「す」,「な」など\n\n"
				+ "・LeapMotionに手の甲を向けている指文字\n"
				+ "　　　例：「し」,「に」など\n\n"
				+ "これらの指文字はLeap Motionから見えない指が出るため、認識精度が落ちます。");
	}

	@FXML
	protected void onLeapClicked(ActionEvent event){
		try {
			showWebWindow();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	protected void showWebWindow() throws IOException {

		WebView web = new WebView();
		final WebEngine webEngine = web.getEngine();
		webEngine.load("https://www.leapmotion.com/ja/");

        // Create the VBox
        VBox root = new VBox();
        // Add the Children to the VBox
        root.getChildren().add(web);

        // Set the Style-properties of the VBox
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
		Scene scene = new Scene(root, 800.0d, 600.0d);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Leap Motion");
		stage.sizeToScene();		//secneのサイズに合わせてウィンドウサイズを指定する
        stage.setResizable(false);	//ウィンドウのリサイズ禁止	trueで許可
		stage.showAndWait();
	}
}
