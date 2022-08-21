package fingerspelling_recognition.utils;

import java.util.ArrayList;

import com.leapmotion.leap.Vector;

public class Movement {	//静止判定//指文字の区切りの検出
	Vector thumb =  Vector.zero(), index = Vector.zero(), middle = Vector.zero(), ring = Vector.zero(), pinky = Vector.zero();
	private int Stationry_MOVEMENT = 20; //静止しているかどうか見る時の，全体の動き量の閾値 単位はmm
	private int Side_MOVEMENT = 50 + Stationry_MOVEMENT; //濁点の時の横の動き量の閾値
	private int Verticality_MOVEMENT = 50 + Stationry_MOVEMENT; //半濁点の時の縦の動き量の閾値
	private double TIME = 0.4 * 1000 * 1000;	//静止観測に対する観測秒数
	private double TIME2 = 0.6 * 1000 * 1000 + TIME;	//動き観測に対する観測秒数

	double time = 0;
	private GetVector getVector = new GetVector();

	public void setParameter(int move, double d, int side, int height) {
		Stationry_MOVEMENT = move;
		Side_MOVEMENT = Stationry_MOVEMENT + side;
		Verticality_MOVEMENT = Stationry_MOVEMENT + height;
		TIME = d * 1000;
	}

	//手の動きが少ないかどうか監視 return >> 手の情報が足りない,手が動いている>>false 手が動いていない>>true
	public boolean isHandStationary(final ArrayList<HandJoint> jointList) {
		if(jointList.get(jointList.size()-1).getFrame().timestamp() - jointList.get(0).getFrame().timestamp() < TIME) {
			return false;
		}
		thumb = Vector.zero();
		index = Vector.zero();
		middle = Vector.zero();
		ring = Vector.zero();
		pinky = Vector.zero();

    	for(int i = jointList.size()-1; i > 0; i--) {
    		time = jointList.get(jointList.size()-1).getFrame().timestamp() - jointList.get(i).getFrame().timestamp();
    		if(time < TIME) {
    			thumb = thumb.plus(getVector.absV(jointList.get(i).getTHUMB_DISTAL(),jointList.get(i - 1).getTHUMB_DISTAL()));
    			index = index.plus(getVector.absV(jointList.get(i).getINDEX_DISTAL(),jointList.get(i - 1).getINDEX_DISTAL()));
    			middle = middle.plus(getVector.absV(jointList.get(i).getMIDDLE_DISTAL(),jointList.get(i - 1).getMIDDLE_DISTAL()));
    			ring = ring.plus(getVector.absV(jointList.get(i).getRING_DISTAL(),jointList.get(i - 1).getRING_DISTAL()));
    			pinky = pinky.plus(getVector.absV(jointList.get(i).getPINKY_DISTAL(),jointList.get(i - 1).getPINKY_DISTAL()));
    			continue;
    		}else {
    			break;
    		}
    	}

		if(isFingerStationry(thumb) && isFingerStationry(index) && isFingerStationry(middle) && isFingerStationry(ring) && isFingerStationry(pinky)) {
			return true;
		}else {
			return false;
		}
	}

	private boolean isFingerStationry(Vector vec) {
		if(vec.getX() < Stationry_MOVEMENT && vec.getY() < Stationry_MOVEMENT && vec.getZ() < Stationry_MOVEMENT) {
			return true;
		}else {
			return false;
		}
	}

	//横の移動量
	public boolean isHandSide(final ArrayList<HandJoint> jointList) {
		if(jointList.get(jointList.size()-1).getFrame().timestamp() - jointList.get(0).getFrame().timestamp() < TIME2) {
			return false;
		}
		thumb = Vector.zero();
		index = Vector.zero();
		middle = Vector.zero();
		ring = Vector.zero();
		pinky = Vector.zero();

		for(int i = jointList.size()-1; i > 0; i--) {
			time = jointList.get(jointList.size()-1).getFrame().timestamp() - jointList.get(i).getFrame().timestamp();
			if(time < TIME2) {
				thumb = thumb.plus(getVector.absV(jointList.get(i).getTHUMB_DISTAL(),jointList.get(i - 1).getTHUMB_DISTAL()));
				index = index.plus(getVector.absV(jointList.get(i).getINDEX_DISTAL(),jointList.get(i - 1).getINDEX_DISTAL()));
				middle = middle.plus(getVector.absV(jointList.get(i).getMIDDLE_DISTAL(),jointList.get(i - 1).getMIDDLE_DISTAL()));
				ring = ring.plus(getVector.absV(jointList.get(i).getRING_DISTAL(),jointList.get(i - 1).getRING_DISTAL()));
				pinky = pinky.plus(getVector.absV(jointList.get(i).getPINKY_DISTAL(),jointList.get(i - 1).getPINKY_DISTAL()));
				continue;
			}else {
				break;
			}
		}

		if(isFingerSide(thumb) && isFingerSide(index) && isFingerSide(middle) && isFingerSide(ring) && isFingerSide(pinky)) {
			return true;
		}else {
			return false;
		}
	}

	//縦の移動量
	public boolean isHandVerticality(final ArrayList<HandJoint> jointList) {
		if(jointList.get(jointList.size()-1).getFrame().timestamp() - jointList.get(0).getFrame().timestamp() < TIME2) {
			return false;
		}
		thumb = Vector.zero();
		index = Vector.zero();
		middle = Vector.zero();
		ring = Vector.zero();
		pinky = Vector.zero();

		for(int i = jointList.size()-1; i > 0; i--) {
			time = jointList.get(jointList.size()-1).getFrame().timestamp() - jointList.get(i).getFrame().timestamp();
			if(time < TIME2) {
				thumb = thumb.plus(getVector.absV(jointList.get(i).getTHUMB_DISTAL(),jointList.get(i - 1).getTHUMB_DISTAL()));
				index = index.plus(getVector.absV(jointList.get(i).getINDEX_DISTAL(),jointList.get(i - 1).getINDEX_DISTAL()));
				middle = middle.plus(getVector.absV(jointList.get(i).getMIDDLE_DISTAL(),jointList.get(i - 1).getMIDDLE_DISTAL()));
				ring = ring.plus(getVector.absV(jointList.get(i).getRING_DISTAL(),jointList.get(i - 1).getRING_DISTAL()));
				pinky = pinky.plus(getVector.absV(jointList.get(i).getPINKY_DISTAL(),jointList.get(i - 1).getPINKY_DISTAL()));
				continue;
			}else {
				break;
			}
		}

		if(isFingerVerticality(thumb) && isFingerVerticality(index) && isFingerVerticality(middle) && isFingerVerticality(ring) && isFingerVerticality(pinky)) {
			return true;
		}else {
			return false;
		}
	}

	private boolean isFingerSide(Vector vec) {
		System.out.println(vec.getX());
		if(vec.getX() > Side_MOVEMENT) {
			return true;
		}else {
			return false;
		}
	}

	private boolean isFingerVerticality(Vector vec) {
		System.out.println(vec.getZ());
		if(vec.getZ() > Verticality_MOVEMENT) {
			return true;
		}else {
			return false;
		}
	}
}
