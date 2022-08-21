package fingerspelling_recognition.javafx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;

import fingerspelling_recognition.LeapMotionListener;
import fingerspelling_recognition.db_utils.Detabase;
import fingerspelling_recognition.db_utils.Parameter;
import fingerspelling_recognition.db_utils.User;
import fingerspelling_recognition.model.Recognition;
import fingerspelling_recognition.utils.HandJoint;
import fingerspelling_recognition.utils.JointAngle;
import fingerspelling_recognition.utils.Movement;
import fingerspelling_recognition.utils.Theme;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FXMLController {

    @FXML private Label result;

    @FXML private Label consoleLabel;

    @FXML private Group draw;

    @FXML private TabPane maintab;

    @FXML private AnchorPane drawhand;

    @FXML private Tab handsign;

    @FXML private TabPane sidTabPane;

    @FXML private Tab resultTab;

    @FXML private TabPane borderTabPane;

    @FXML private Tab consoleTab;

    @FXML private Label now;

    @FXML private Label theme;

    @FXML private Label input;

	//ConnectionSQLite sql = new ConnectionSQLite();

	private Controller controller;
	private LeapMotionListener listener;

	private Frame frame;
	private HandJoint hand ;//= new HandJoint();
	private ArrayList<HandJoint> jointList = new ArrayList<HandJoint>();
	private Movement movement = new Movement();
	private DrawHand drawHand  = new DrawHand();

	private Recognition rec = new Recognition();
	private JointAngle angle = new JointAngle();

	private Detabase db = new Detabase();
	private int userId = 0;
	private int experimentId = 0;

    private boolean flag = false;

    private Theme themes = new Theme();
    private boolean experimentFlg = false;

    private int count = 0;
    private final int MAX_COUNT = 5;

    int angleThreshold = 135;

	FXMLSettingController userCont =  new FXMLSettingController();
	FXMLParameterSettingController parameterController = new FXMLParameterSettingController();

	//実験の入力結果表示
    @FXML private Label progress1;
    @FXML private Label progress2;
    @FXML private Label progress3;
    @FXML private Label progress4;
    @FXML private Label progress5;
    @FXML private Label progress6;
    @FXML private Label progress7;
    @FXML private Label progress8;
    @FXML private Label progress9;
    @FXML private Label progress10;
    ArrayList<Label> progress = new ArrayList<Label>(10);

	@FXML
	protected void initialize(){
		//System.out.println("initialize");
		//初期化時にリストに入れたらエラーが起きたため，コントローラーの初期化時にリストに入れる
		progress.add(progress1);
		progress.add(progress2);
		progress.add(progress3);
		progress.add(progress4);
		progress.add(progress5);
		progress.add(progress6);
		progress.add(progress7);
		progress.add(progress8);
		progress.add(progress9);
		progress.add(progress10);
		for(int i = 0; i < 10; i++) {
			progress.get(i).setText(" ");
			progress.get(i).setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), new CornerRadii(5) , null)));
		}
		theme.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), new CornerRadii(5) , null)));
		input.setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), new CornerRadii(5) , null)));
		//DBで使うテーブル作成，すでにある場合何もしない
		db.creat();
	}

    public void handSign() {
    	//実験の設定ウィンドウでOKを押して問題なく終了されたら，入力データを受け取る
        userCont.userProperty().addListener((ov, t,t1) ->
    	Platform.runLater(() -> {
    		User user = ov.getValue();
    		user.show();
    		userId = db.updateUser(user.getGender(), user.getHand(), user.getYear());
    		experimentId = db.startExperoments(userId);
        	experimentFlg = true;
        	count = 0;
    		themes.moveHead();
    		theme.setText(themes.getNextTheme());
        }));

    	//パラメータの設定ウィンドウでOKを押して問題なく終了されたら，入力データを受け取る
        parameterController.userProperty().addListener((ov, t,t1) ->
    	Platform.runLater(() -> {
    		Parameter p = ov.getValue();
    		movement.setParameter(p.getMove(),p.getTime(),p.getSide(),p.getHeight());
    		hand.setParameter(p.getAngle());
    		angleThreshold = p.getAngle();
        }));

        //ここからアプリケーションの処理
    	//Leap Motion
    	try {
    		controller = new Controller();
    		listener = new LeapMotionListener();
    	} catch (UnsatisfiedLinkError e) {
    		System.out.println("EXCEPION: " + e);
    	}

		drawHand.create(draw);	//手のオブジェクトを設置
		draw.setVisible(false);	//手の描画の非表示

        listener.frameProperty().addListener((ov, t,t1) ->
        	Platform.runLater(() -> {
        	frame = ov.getValue();	//リープモーションから今のフレームを受け取る

        	if(jointList.size() >= 100) {jointList.remove(0);}
        	jointList.add(hand = new HandJoint());	//手の情報をリストに追加
        	hand.setParameter(angleThreshold);

        	hand.setFrame(frame);	//フレーム情報を渡す
        	if(hand.hasHand()) {	//このフレームに手が存在するか
            	hand.updateHand();	//手の情報をアップデート
            	draw.setVisible(true);

    			//手の動き量を常時観察
    			if(flag == true && movement.isHandStationary(jointList) == true) {
    				//手が静止している
    				rec.setHand(hand);	//識別機に投げる
    				if(movement.isHandSide(jointList)) {
    					rec.dakuten();
    				}else if(movement.isHandVerticality(jointList)) {
    					rec.handakuten();
    				}else {
    					rec.recognize();
    				}
    				now.setText(rec.getString());
    				condTable();

    				//実験
    				if(experimentFlg) {	//実験モードのフラグ
    					if(rec.getString() != null) {
        					input.setText(rec.getString());
        					db.updateResult(experimentId, theme.getText(), input.getText(), Objects.equals(theme.getText(), rec.getString()));	//データベースに結果の挿入
        					if(count == 0) {	//認識結果表示の初期化
        						for(int i = 0; i < MAX_COUNT; i++) {
        							progress.get(i).setText(" ");
        							progress.get(i).setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), new CornerRadii(5) , null)));
        						}
        					}

        					if(Objects.equals(theme.getText(), rec.getString())) {	//お題と認識結果が一致
        						progress.get(count).setText(rec.getString());
        						progress.get(count).setBackground(new Background(new BackgroundFill(Color.web("#99ff99"), new CornerRadii(5) , null)));
        						input.setBackground(new Background(new BackgroundFill(Color.web("#99ff99"), new CornerRadii(5) , null)));
        						count = 0;
        						if(themes.hasNext()) {
        							theme.setText(themes.getNextTheme());
        						}else {
        							alert();
        							themes.moveHead();
        							theme.setText(themes.getNextTheme());
        						}
        					}else if(count == MAX_COUNT - 1){	//MAX_COUNT回続けてお題と認識結果が一致しなかった
        						progress.get(count).setText(rec.getString());
        						progress.get(count).setBackground(new Background(new BackgroundFill(Color.web("#ff9999"), new CornerRadii(5) , null)));
        						input.setBackground(new Background(new BackgroundFill(Color.web("#ff9999"), new CornerRadii(5) , null)));
        						count = 0;
        						if(themes.hasNext()) {
        							theme.setText(themes.getNextTheme());
        						}else {
        							alert();
        							themes.moveHead();
        							theme.setText(themes.getNextTheme());
        						}
        					}else if(count < MAX_COUNT - 1) {	//お題と認識結果が一致していないかつ試行数がMAX_COUNT回未満
        						progress.get(count).setText(rec.getString());
        						progress.get(count).setBackground(new Background(new BackgroundFill(Color.web("#ff9999"), new CornerRadii(5) , null)));
        						input.setBackground(new Background(new BackgroundFill(Color.web("#ff9999"), new CornerRadii(5) , null)));
        						count++;
        					}
    					}
    				}
    				flag = false;
    			}else if(flag == false && movement.isHandStationary(jointList) == false) {	//手が動いている
    				flag = true;
    			}
        	}else {
        		draw.setVisible(false);
        	}
        	angle.jointAngle(hand);



			//手の描画を更新
			drawHand.draw(hand);

			//結果表示
			result.setText(rec.getText());
			consoleLabel.setText(setLog());
			angleTable();

			console();	//コンソール表示用
			System.out.println("ListSize: " + jointList.size());
        }));
    }

    private void alert(){
    	Alert dialog = new Alert(AlertType.INFORMATION);
    	dialog.setHeaderText(null);
    	dialog.setContentText("お題を一周しました．");

    	dialog.showAndWait();
    }

	private String setLog() {
		String log = "----------------------------------------------------------------------------------------" + "\n"
					+ listener.log(frame)
					+ "|---------------------------------------------------------------------------------------"+ "\n"
					+ angle.log()
					+ "|---------------------------------------------------------------------------------------"+ "\n"
					+ "|" + movement.isHandStationary(jointList) + "\n"
					+ "|---------------------------------------------------------------------------------------"+ "\n"
					+ rec.results();
		return log;
	}

	//コンソール確認用
	private void console() {
    	System.out.println("----------------------------------------------------------------------------------------");
    	System.out.println(listener.log(frame));
    	System.out.println("|---------------------------------------------------------------------------------------");
		System.out.println(angle.log());
		System.out.println("|---------------------------------------------------------------------------------------");
    	System.out.println("|" + movement.isHandStationary(jointList));
		System.out.println("|---------------------------------------------------------------------------------------");
		System.out.println(rec.results());
	}

    @FXML private Label cond1;
    @FXML private Label cond2;
    @FXML private Label cond3;
    @FXML private Label cond4;
    @FXML private Label cond5;
    @FXML private Label cond6;
    @FXML private Label cond7;
    @FXML private Label cond8;
    @FXML private Label cond9;
    @FXML private Label cond10;
    @FXML private Label cond11;
    @FXML private Label cond12;
    @FXML private Label cond13;
    @FXML private Label cond14;
    @FXML private Label cond15;
    @FXML private Label cond16;
    @FXML private Label cond17;
    @FXML private Label cond18;
    @FXML private Label cond19;
    @FXML private Label cond20;
    @FXML private Label cond21;
    @FXML private Label cond22;

    private void condCell(Label la, boolean b) {
    	la.setText(String.valueOf(b));
    	if(b) {
    		la.setTextFill(Color.web("#0076a3"));
    	}else {
    		la.setTextFill(Color.web("#ee0000"));
    	}
    }

    private void condTable() {
    	condCell(cond1,rec.a());
    	condCell(cond2,rec.b());
    	condCell(cond3,rec.c());
    	condCell(cond4,rec.d());
    	condCell(cond5,rec.e());
    	condCell(cond6,rec.f());
    	condCell(cond7,rec.g());
    	condCell(cond8,rec.h());
    	condCell(cond9,rec.i());
    	condCell(cond10,rec.j());
    	condCell(cond11,rec.k());
    	condCell(cond12,rec.l());
    	condCell(cond13,rec.m());
    	condCell(cond14,rec.n());
    	condCell(cond15,rec.o());
    	condCell(cond16,rec.p());
    	condCell(cond17,rec.q());
    	condCell(cond18,rec.r());
    	condCell(cond19,rec.s());
    	condCell(cond20,rec.t());
    	condCell(cond21,rec.u());
    }

    @FXML private Label tm;
    @FXML private Label tp;
    @FXML private Label td;
    @FXML private Label im;
    @FXML private Label ip;
    @FXML private Label id;
    @FXML private Label mm;
    @FXML private Label mp;
    @FXML private Label md;
    @FXML private Label rm;
    @FXML private Label rp;
    @FXML private Label rd;
    @FXML private Label pm;
    @FXML private Label pp;
    @FXML private Label pd;

    //GUIに指の関節の角度を表示
    private void angleTable() {
    	tm.setText("NULL");
    	angleCell(tp,angle.getTHUMP_PIP());
    	angleCell(td,angle.getTHUMP_DIO());
    	angleCell(im,angle.getINDEX_MP());
    	angleCell(ip,angle.getINDEX_PIP());
    	angleCell(id,angle.getINDEX_PIP());
    	angleCell(id,angle.getINDEX_DIO());
    	angleCell(mm,angle.getMIDDLE_MP());
    	angleCell(mp,angle.getMIDDLE_PIP());
    	angleCell(md,angle.getMIDDLE_DIO());
    	angleCell(rm,angle.getRING_MP());
    	angleCell(rp,angle.getRING_PIP());
    	angleCell(rd,angle.getRING_DIO());
    	angleCell(pm,angle.getPINKY_MP());
    	angleCell(pp,angle.getPINKY_PIP());
    	angleCell(pd,angle.getPINKY_DIO());
    }

    private void angleCell(Label la, Double r) {
    	la.setText(String.format("%.2f",r));
    	if(r > angleThreshold) {
    		la.setTextFill(Color.web("#0076a3"));
    	}else {
    		la.setTextFill(Color.web("#000000"));
    	}
    }

    //指文字のタブが閉じられたときに入る
    @FXML
    protected void onClosed(ActionEvent event) {
    	System.out.println("closed tab");
    }

    @FXML
    protected void onStartExperimentsClicked() {
    	experimentFlg = true;
		themes.moveHead();
		theme.setText(themes.getNextTheme());
    }

    @FXML
    protected void onEndExperimentsClicked() {
    	experimentFlg = false;
    	themes.moveHead();
    }

    @FXML
    protected void onParameterSettingClicked() throws IOException {
        FXMLLoader parameter = new FXMLLoader(getClass().getResource("parameter.fxml"));
        parameter.setController(parameterController);
		Scene scene = new Scene((Parent) parameter.load());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("パラメータ設定");
		stage.showAndWait();
    }

    @FXML
    protected void onOrderSyllabaryClicked() {
    	themes.choiceMode(1);
    }

    @FXML
    protected void onOrderRandomClicked() {
    	themes.choiceMode(2);
    }

    @FXML
    protected void onMenuClicked(ActionEvent event) {
    	maintab.getTabs().add(handsign);
    }

    @FXML
    protected void onConsoleClicked(ActionEvent event) {
    	borderTabPane.getTabs().add(consoleTab);
    }

    @FXML
    protected void onResultClicked(ActionEvent event) {
    	sidTabPane.getTabs().add(resultTab);
    }

    @FXML
    protected void onStartClick(ActionEvent event) {
    	try {
    		controller.addListener(listener);
    	} catch (UnsatisfiedLinkError e) {
    		System.out.println("EXCEPION: " + e);
    	}
    }

    @FXML
    protected void onPouseClick(ActionEvent event) {
    	try {
        	controller.removeListener(listener);
    	} catch (UnsatisfiedLinkError e) {
    		System.out.println("EXCEPION: " + e);
    	}
    }

    @FXML
    protected void onResetClick(ActionEvent event) {
    	rec.delete();
    	result.setText(rec.getText());
    }

    @FXML
    protected void onPussClicked(ActionEvent event) {
		for(int i = 0; i < 10; i++) {
			progress.get(i).setText(" ");
			progress.get(i).setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), new CornerRadii(5) , null)));
		}
		if(themes.hasNext()) {
			theme.setText(themes.getNextTheme());
		}else {
			themes.moveHead();
			theme.setText(themes.getNextTheme());
			alert();
		}
		count = 0;
    }

    @FXML
    protected void onExperimentsClicked(ActionEvent event) {
		try {
			showExperimentsWindow();
		} catch (Exception e) {
			System.out.println("EXCEPION: " + e);
		}
    }

    private void showExperimentsWindow() throws IOException{
        FXMLLoader experiments = new FXMLLoader(getClass().getResource("experiments.fxml"));
        experiments.setController(userCont);
		Scene scene = new Scene((Parent) experiments.load());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("実験");
		stage.showAndWait();
    }

    @FXML
    protected void onHelpClicked(ActionEvent event) {
		try {
			showHelpWindow();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
    }

	private void showHelpWindow() throws IOException {
		FXMLLoader help = new FXMLLoader(getClass().getResource("help.fxml"));
		Scene scene = new Scene((Parent) help.load());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("ヘルプ");
		stage.showAndWait();
	}
}
