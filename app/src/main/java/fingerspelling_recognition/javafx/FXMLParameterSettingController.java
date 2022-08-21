package fingerspelling_recognition.javafx;
import fingerspelling_recognition.db_utils.Parameter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
public class FXMLParameterSettingController {

	@FXML private TextField move;
	@FXML private TextField time;
	@FXML private TextField side;
	@FXML private TextField height;
	@FXML private TextField angle;

    @FXML
    protected void onEndInputClicked(ActionEvent event) {
    	try {
            Parameter data = new Parameter();
            data.setMove(Integer.parseInt(move.getText()));
            data.setTime(Integer.parseInt(time.getText()));
            data.setSide(Integer.parseInt(side.getText()));
            data.setHeight(Integer.parseInt(height.getText()));
            data.setAngle(Integer.parseInt(angle.getText()));
            parameterSet.set(data);
            ((Node)event.getSource()).getScene().getWindow().hide(); //ウィンドウを消す
    	}catch(Exception e) {
    		System.out.println("EXCEPION: " + e);
    	}
    }

    @FXML
    protected void onCancelInputClicked(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
    }

	private final ObjectProperty<Parameter> parameterSet = new SimpleObjectProperty<>();
	public ObservableValue<Parameter> userProperty(){
		return parameterSet;
	}
}
