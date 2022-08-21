package fingerspelling_recognition.javafx;

import fingerspelling_recognition.utils.HandJoint;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

//JavaFXの描画
public class DrawHand {
	private final double radius = 15d;	//オブジェクトのサイズ(半径)
	private Sphere sphereTHUMB_METACARPAL	= new Sphere(radius);
	private Sphere sphereTHUMB_PROXIMAL	= new Sphere(radius);
	private Sphere sphereTHUMB_INTERMEDIATE	= new Sphere(radius);
	private Sphere sphereTHUMB_DISTAL	= new Sphere(radius);

	private Sphere sphereINDEX_PREV	= new Sphere(radius);
	private Sphere sphereINDEX_METACARPAL	= new Sphere(radius);
	private Sphere sphereINDEX_PROXIMAL	= new Sphere(radius);
	private Sphere sphereINDEX_INTERMEDIATE	= new Sphere(radius);
	private Sphere sphereINDEX_DISTAL	= new Sphere(radius);

	private Sphere sphereMIDDLE_PREV	= new Sphere(radius);
	private Sphere sphereMIDDLE_METACARPAL	= new Sphere(radius);
	private Sphere sphereMIDDLE_PROXIMAL	= new Sphere(radius);
	private Sphere sphereMIDDLE_INTERMEDIATE	= new Sphere(radius);
	private Sphere sphereMIDDLE_DISTAL	= new Sphere(radius);

	private Sphere sphereRING_PREV	= new Sphere(radius);
	private Sphere sphereRING_METACARPAL	= new Sphere(radius);
	private Sphere sphereRING_PROXIMAL	= new Sphere(radius);
	private Sphere sphereRING_INTERMEDIATE	= new Sphere(radius);
	private Sphere sphereRING_DISTAL	= new Sphere(radius);

	private Sphere spherePINKY_PREV	= new Sphere(radius);
	private Sphere spherePINKY_METACARPAL	= new Sphere(radius);
	private Sphere spherePINKY_PROXIMAL	= new Sphere(radius);
	private Sphere spherePINKY_INTERMEDIATE	= new Sphere(radius);
	private Sphere spherePINKY_DISTAL	= new Sphere(radius);

	private PhongMaterial material = new PhongMaterial();

	public void color(boolean isFront, boolean isBack) {
		if(isFront) {
			material.setDiffuseColor(Color.WHITE);
		}else if(isBack) {
			material.setDiffuseColor(Color.TURQUOISE);
		}else {
			material.setDiffuseColor(Color.WHITE);
		}
		setMaterial();
	}

	private void setMaterial() {
		sphereTHUMB_METACARPAL.setMaterial(material);
		sphereTHUMB_PROXIMAL.setMaterial(material);
		sphereTHUMB_INTERMEDIATE.setMaterial(material);
		sphereTHUMB_DISTAL.setMaterial(material);
		sphereINDEX_PREV.setMaterial(material);
		sphereINDEX_METACARPAL.setMaterial(material);
		sphereINDEX_PROXIMAL.setMaterial(material);
		sphereINDEX_INTERMEDIATE.setMaterial(material);
		sphereINDEX_DISTAL.setMaterial(material);
		sphereMIDDLE_PREV.setMaterial(material);
		sphereMIDDLE_METACARPAL.setMaterial(material);
		sphereMIDDLE_PROXIMAL.setMaterial(material);
		sphereMIDDLE_INTERMEDIATE.setMaterial(material);
		sphereMIDDLE_DISTAL.setMaterial(material);
		sphereRING_PREV.setMaterial(material);
		sphereRING_METACARPAL.setMaterial(material);
		sphereRING_PROXIMAL.setMaterial(material);
		sphereRING_INTERMEDIATE.setMaterial(material);
		sphereRING_DISTAL.setMaterial(material);
		spherePINKY_PREV.setMaterial(material);
		spherePINKY_METACARPAL.setMaterial(material);
		spherePINKY_PROXIMAL.setMaterial(material);
		spherePINKY_INTERMEDIATE.setMaterial(material);
		spherePINKY_DISTAL.setMaterial(material);
	}

	//手のオブジェクトのグループ
	public void create(Group draw){
		material.setDiffuseColor(Color.WHITE);
		setMaterial();
		draw.getChildren().addAll(sphereTHUMB_METACARPAL, sphereTHUMB_PROXIMAL, sphereTHUMB_INTERMEDIATE, sphereTHUMB_DISTAL,
				sphereINDEX_PREV, sphereINDEX_METACARPAL, sphereINDEX_PROXIMAL, sphereINDEX_INTERMEDIATE, sphereINDEX_DISTAL,
				sphereMIDDLE_PREV, sphereMIDDLE_METACARPAL, sphereMIDDLE_PROXIMAL, sphereMIDDLE_INTERMEDIATE, sphereMIDDLE_DISTAL,
				sphereRING_PREV, sphereRING_METACARPAL, sphereRING_PROXIMAL, sphereRING_INTERMEDIATE, sphereRING_DISTAL,
				spherePINKY_PREV, spherePINKY_METACARPAL, spherePINKY_PROXIMAL, spherePINKY_INTERMEDIATE, spherePINKY_DISTAL);
	}

	//作成したオブジェクトの削除
	public void clear(Group root){
		root.getChildren().clear();
	}

	//中央位置調整やサイズ調整
	private final double X = 400.0d, Y = 300.0d , Z = 0.0d;
	private final double size = 2.0;

	//描画，更新
	public void draw(HandJoint hand) {
		color(hand.isFront(), hand.isBack());
		sphereTHUMB_METACARPAL.setTranslateX(X + hand.getTHUMB_METACARPAL().getX() * size);
		sphereTHUMB_METACARPAL.setTranslateY(Y + hand.getTHUMB_METACARPAL().getZ() * size);
		sphereTHUMB_METACARPAL.setTranslateZ(Z + hand.getTHUMB_METACARPAL().getY() * size);

		sphereTHUMB_PROXIMAL.setTranslateX(X + hand.getTHUMB_PROXIMAL().getX() * size);
		sphereTHUMB_PROXIMAL.setTranslateY(Y + hand.getTHUMB_PROXIMAL().getZ() * size);
		sphereTHUMB_PROXIMAL.setTranslateZ(Z + hand.getTHUMB_PROXIMAL().getY() * size);

		sphereTHUMB_INTERMEDIATE.setTranslateX(X + hand.getTHUMB_INTERMEDIATE().getX() * size);
		sphereTHUMB_INTERMEDIATE.setTranslateY(Y + hand.getTHUMB_INTERMEDIATE().getZ() * size);
		sphereTHUMB_INTERMEDIATE.setTranslateZ(Z + hand.getTHUMB_INTERMEDIATE().getY() * size);

		sphereTHUMB_DISTAL.setTranslateX(X + hand.getTHUMB_DISTAL().getX() * size);
		sphereTHUMB_DISTAL.setTranslateY(Y + hand.getTHUMB_DISTAL().getZ() * size);
		sphereTHUMB_DISTAL.setTranslateZ(Z + hand.getTHUMB_DISTAL().getY() * size);

		sphereINDEX_PREV.setTranslateX(X + hand.getINDEX_PREV().getX() * size);
		sphereINDEX_PREV.setTranslateY(Y + hand.getINDEX_PREV().getZ() * size);
		sphereINDEX_PREV.setTranslateZ(Z + hand.getINDEX_PREV().getY() * size);

		sphereINDEX_METACARPAL.setTranslateX(X + hand.getINDEX_METACARPAL().getX() * size);
		sphereINDEX_METACARPAL.setTranslateY(Y + hand.getINDEX_METACARPAL().getZ() * size);
		sphereINDEX_METACARPAL.setTranslateZ(Z + hand.getINDEX_METACARPAL().getY() * size);

		sphereINDEX_PROXIMAL.setTranslateX(X + hand.getINDEX_PROXIMAL().getX() * size);
		sphereINDEX_PROXIMAL.setTranslateY(Y + hand.getINDEX_PROXIMAL().getZ() * size);
		sphereINDEX_PROXIMAL.setTranslateZ(Z + hand.getINDEX_PROXIMAL().getY() * size);

		sphereINDEX_INTERMEDIATE.setTranslateX(X + hand.getINDEX_INTERMEDIATE().getX() * size);
		sphereINDEX_INTERMEDIATE.setTranslateY(Y + hand.getINDEX_INTERMEDIATE().getZ() * size);
		sphereINDEX_INTERMEDIATE.setTranslateZ(Z + hand.getINDEX_INTERMEDIATE().getY() * size);

		sphereINDEX_DISTAL.setTranslateX(X + hand.getINDEX_DISTAL().getX() * size);
		sphereINDEX_DISTAL.setTranslateY(Y + hand.getINDEX_DISTAL().getZ() * size);
		sphereINDEX_DISTAL.setTranslateZ(Z + hand.getINDEX_DISTAL().getY() * size);

		sphereMIDDLE_PREV.setTranslateX(X + hand.getMIDDLE_PREV().getX() * size);
		sphereMIDDLE_PREV.setTranslateY(Y + hand.getMIDDLE_PREV().getZ() * size);
		sphereMIDDLE_PREV.setTranslateZ(Z + hand.getMIDDLE_PREV().getY() * size);

		sphereMIDDLE_METACARPAL.setTranslateX(X + hand.getMIDDLE_METACARPAL().getX() * size);
		sphereMIDDLE_METACARPAL.setTranslateY(Y + hand.getMIDDLE_METACARPAL().getZ() * size);
		sphereMIDDLE_METACARPAL.setTranslateZ(Z + hand.getMIDDLE_METACARPAL().getY() * size);

		sphereMIDDLE_PROXIMAL.setTranslateX(X + hand.getMIDDLE_PROXIMAL().getX() * size);
		sphereMIDDLE_PROXIMAL.setTranslateY(Y + hand.getMIDDLE_PROXIMAL().getZ() * size);
		sphereMIDDLE_PROXIMAL.setTranslateZ(Z + hand.getMIDDLE_PROXIMAL().getY() * size);

		sphereMIDDLE_INTERMEDIATE.setTranslateX(X + hand.getMIDDLE_INTERMEDIATE().getX() * size);
		sphereMIDDLE_INTERMEDIATE.setTranslateY(Y + hand.getMIDDLE_INTERMEDIATE().getZ() * size);
		sphereMIDDLE_INTERMEDIATE.setTranslateZ(Z + hand.getMIDDLE_INTERMEDIATE().getY() * size);

		sphereMIDDLE_DISTAL.setTranslateX(X + hand.getMIDDLE_DISTAL().getX() * size);
		sphereMIDDLE_DISTAL.setTranslateY(Y + hand.getMIDDLE_DISTAL().getZ() * size);
		sphereMIDDLE_DISTAL.setTranslateZ(Z + hand.getMIDDLE_DISTAL().getY() * size);

		sphereRING_PREV.setTranslateX(X + hand.getRING_PREV().getX() * size);
		sphereRING_PREV.setTranslateY(Y + hand.getRING_PREV().getZ() * size);
		sphereRING_PREV.setTranslateZ(Z + hand.getRING_PREV().getY() * size);

		sphereRING_METACARPAL.setTranslateX(X + hand.getRING_METACARPAL().getX() * size);
		sphereRING_METACARPAL.setTranslateY(Y + hand.getRING_METACARPAL().getZ() * size);
		sphereRING_METACARPAL.setTranslateZ(Z + hand.getRING_METACARPAL().getY() * size);

		sphereRING_PROXIMAL.setTranslateX(X + hand.getRING_PROXIMAL().getX() * size);
		sphereRING_PROXIMAL.setTranslateY(Y + hand.getRING_PROXIMAL().getZ() * size);
		sphereRING_PROXIMAL.setTranslateZ(Z + hand.getRING_PROXIMAL().getY() * size);

		sphereRING_INTERMEDIATE.setTranslateX(X + hand.getRING_INTERMEDIATE().getX() * size);
		sphereRING_INTERMEDIATE.setTranslateY(Y + hand.getRING_INTERMEDIATE().getZ() * size);
		sphereRING_INTERMEDIATE.setTranslateZ(Z + hand.getRING_INTERMEDIATE().getY() * size);

		sphereRING_DISTAL.setTranslateX(X + hand.getRING_DISTAL().getX() * size);
		sphereRING_DISTAL.setTranslateY(Y + hand.getRING_DISTAL().getZ() * size);
		sphereRING_DISTAL.setTranslateZ(Z + hand.getRING_DISTAL().getY() * size);

		spherePINKY_PREV.setTranslateX(X + hand.getPINKY_PREV().getX() * size);
		spherePINKY_PREV.setTranslateY(Y + hand.getPINKY_PREV().getZ() * size);
		spherePINKY_PREV.setTranslateZ(Z + hand.getPINKY_PREV().getY() * size);

		spherePINKY_METACARPAL.setTranslateX(X + hand.getPINKY_METACARPAL().getX() * size);
		spherePINKY_METACARPAL.setTranslateY(Y + hand.getPINKY_METACARPAL().getZ() * size);
		spherePINKY_METACARPAL.setTranslateZ(Z + hand.getPINKY_METACARPAL().getY() * size);

		spherePINKY_PROXIMAL.setTranslateX(X + hand.getPINKY_PROXIMAL().getX() * size);
		spherePINKY_PROXIMAL.setTranslateY(Y + hand.getPINKY_PROXIMAL().getZ() * size);
		spherePINKY_PROXIMAL.setTranslateZ(Z + hand.getPINKY_PROXIMAL().getY() * size);

		spherePINKY_INTERMEDIATE.setTranslateX(X + hand.getPINKY_INTERMEDIATE().getX() * size);
		spherePINKY_INTERMEDIATE.setTranslateY(Y + hand.getPINKY_INTERMEDIATE().getZ() * size);
		spherePINKY_INTERMEDIATE.setTranslateZ(Z + hand.getPINKY_INTERMEDIATE().getY() * size);

		spherePINKY_DISTAL.setTranslateX(X + hand.getPINKY_DISTAL().getX() * size);
		spherePINKY_DISTAL.setTranslateY(Y + hand.getPINKY_DISTAL().getZ() * size);
		spherePINKY_DISTAL.setTranslateZ(Z + hand.getPINKY_DISTAL().getY() * size);

	}
}
