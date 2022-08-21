package fingerspelling_recognition.utils;

import com.leapmotion.leap.Vector;

public class GetVector {
	float x,y,z;
	//二つのベクトルのそれぞれ座標情報の絶対値をベクトルで返す．
	public Vector absV(Vector a, Vector b) {
		x = Math.abs(a.getX() - b.getX());
		y = Math.abs(a.getY() - b.getY());
		z = Math.abs(a.getZ() - b.getZ());
		Vector v = new Vector(x,y,z);
		return v;
	}
}
