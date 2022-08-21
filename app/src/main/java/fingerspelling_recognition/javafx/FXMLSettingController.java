package fingerspelling_recognition.javafx;
import fingerspelling_recognition.db_utils.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FXMLSettingController {
    @FXML private ToggleGroup gender;
    @FXML private RadioButton male;
    @FXML private RadioButton female;
    @FXML private ToggleGroup hand;
    @FXML private RadioButton right;
    @FXML private RadioButton left;
    @FXML private TextField year;

	@FXML
	protected void initialize(){
		male.setUserData(1);
		female.setUserData(2);
		right.setUserData(1);
		left.setUserData(2);
	}

    @FXML
    protected void onEndInputClicked(ActionEvent event) {
    	try {
        	System.out.println(gender.getSelectedToggle().getUserData().toString());
        	System.out.println(hand.getSelectedToggle().getUserData().toString());
        	String s = year.getText();
        	int i = Integer.parseInt(s);
        	System.out.println(i);
            User data = new User((int)gender.getSelectedToggle().getUserData(), (int)hand.getSelectedToggle().getUserData(), i);
            userSet.set(data);
            ((Node)event.getSource()).getScene().getWindow().hide(); //ウィンドウを消す
    	}catch(Exception e) {
    		System.out.println("EXCEPION: " + e);
    	}
    }

    @FXML
    protected void onCancelInputClicked(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
    }

	private final ObjectProperty<User> userSet = new SimpleObjectProperty<>();
	public ObservableValue<User> userProperty(){
		return userSet;
	}
}
