package fingerspelling_recognition.utils;

import com.leapmotion.leap.Vector;

public class Angle {
	private double angle;
	private Vector next;
	private Vector prev;

	/*
	float A.angleTo(B) (Vector A,B) ベクトルA,B間の角度をfloatで返す
	このベクトルと指定されたベクトル間の角度（ラジアン単位）。
	角度は、2つのベクトルによって形成される平面で測定されます。返される角度は、常に2つの共役角のうち小さい方です。
	したがって、常にπラジアン（180度）以下の正の値です。A.angleTo(B) == B.angleTo(A)
	いずれかのベクトルの長さがゼロの場合、この関数はゼロを返します。
	 */

	public double calculateAngle(Vector joint, Vector next, Vector prev){
		this.next = next.minus(joint);
		this.prev = prev.minus(joint);
		angle = Math.toDegrees(this.next.angleTo(this.prev));
		return this.angle;
	}

	public void printAngle() {
		System.out.println("angle = " + String.format("%.2f", angle) + "degrees");
	}

}
