package fingerspelling_recognition;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;

import fingerspelling_recognition.utils.ColorCode;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class LeapMotionListener extends Listener {

	@Override
	public void onInit(Controller controller) {	//Controllerに新たなListenerオブジェクトが追加されたときに呼び出される
		System.out.println("Initialized");
	}

	@Override
	public void onConnect(Controller controller) {	//Leap Motionが接続されたときに実行
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}

	@Override
	public void onDisconnect(Controller controller) {	//Leap Motionが節出されたときに実行
		System.out.println("Disconnected");
	}

	@Override
	public void onExit(Controller controller) {	//Controllerインスタンスが破棄されたときに呼び出される
		System.out.println("Exited");
	}

	public String log(Frame frame) {
		String log =
		"|" + ColorCode.CYAN
		+ "Frame id: " + frame.id()						//現在フレームのID
		+ ", timestamp: " + frame.timestamp()			//PC内の時間
		+ ", framerate: " + frame.currentFramesPerSecond() //フレームレート
		+ ", hands: " + frame.hands().count()			//検出された手の数
		+ ColorCode.END + "\n";
		return log;
	}

	@Override
	public void onFrame(Controller controller) {
		Frame frame = controller.frame();
		frameSet.set(frame);	//JavaFX側のイベント呼出し
	}

	//JavaFX側にFrameを渡す
	private final ObjectProperty<Frame> frameSet = new SimpleObjectProperty<>();
	public ObservableValue<Frame> frameProperty(){
		return frameSet;
	}
}
