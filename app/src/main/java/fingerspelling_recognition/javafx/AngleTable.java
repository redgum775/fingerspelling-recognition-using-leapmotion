package fingerspelling_recognition.javafx;

import fingerspelling_recognition.utils.JointAngle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

//L26 error
public class AngleTable {
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
    public void angleTable(JointAngle angle) {
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
    	if(r > 135) {
    		la.setTextFill(Color.web("#0076a3"));
    	}else {
    		la.setTextFill(Color.web("#000000"));
    	}
    }
}
