package fingerspelling_recognition.javafx;

import fingerspelling_recognition.model.Recognition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class CondTable {
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

    public void condTable(Recognition rec) {
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
}
