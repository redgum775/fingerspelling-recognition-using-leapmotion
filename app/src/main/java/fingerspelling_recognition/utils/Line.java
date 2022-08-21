package fingerspelling_recognition.utils;

public class Line {
	//二次元において線分ABと線分CDが交差しているかどうか
	public boolean isIntersect(double ax, double ay, double bx, double by, double cx, double cy, double dx, double dy) {
	double ta = (cx - dx) * (ay - cy) + (cy - dy) * (cx - ax);
	double tb = (cx - dx) * (by - cy) + (cy - dy) * (cx - bx);
	double tc = (ax - bx) * (cy - ay) + (ay - by) * (ax - cx);
	double td = (ax - bx) * (dy - ay) + (ay - by) * (ax - dx);

	System.out.println("isIntersect: " + (tc * td < 0 && ta * tb < 0));
	return tc * td < 0 && ta * tb < 0;
	// return tc * td <= 0 && ta * tb <= 0; // 端点を含む場合
	}
}
