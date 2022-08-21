package fingerspelling_recognition.utils;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Vector;

import fingerspelling_recognition.LeapMotionListener;

public class HandJoint extends LeapMotionListener{

	private Frame frame;
	private Angle angle = new Angle();
	private Line line = new Line();

	private Vector THUMP_PREV = Vector.zero();
	private Vector THUMP_METACARPAL = Vector.zero();
	private Vector THUMP_PROXIMAL = Vector.zero();
	private Vector THUMP_INTERMEDIATE = Vector.zero();
	private Vector THUMP_DISTAL = Vector.zero();

	private Vector INDEX_PREV = Vector.zero();
	private Vector INDEX_METACARPAL = Vector.zero();
	private Vector INDEX_PROXIMAL = Vector.zero();
	private Vector INDEX_INTERMEDIATE = Vector.zero();
	private Vector INDEX_DISTAL = Vector.zero();

	private Vector MIDDLE_PREV = Vector.zero();
	private Vector MIDDLE_METACARPAL = Vector.zero();
	private Vector MIDDLE_PROXIMAL = Vector.zero();
	private Vector MIDDLE_INTERMEDIATE = Vector.zero();
	private Vector MIDDLE_DISTAL = Vector.zero();

	private Vector PINKY_PREV = Vector.zero();
	private Vector PINKY_METACARPAL = Vector.zero();
	private Vector PINKY_PROXIMAL = Vector.zero();
	private Vector PINKY_INTERMEDIATE = Vector.zero();
	private Vector PINKY_DISTAL = Vector.zero();

	private Vector RING_PREV = Vector.zero();
	private Vector RING_METACARPAL = Vector.zero();
	private Vector RING_PROXIMAL = Vector.zero();
	private Vector RING_INTERMEDIATE = Vector.zero();
	private Vector RING_DISTAL = Vector.zero();

	//誤:exists...存在する　正:is...～か？　isに置き換える
	private boolean isThumbStraight, isIndexStraight, isMiddleStraight, isRingStraight, isPinkyStraight;	//指を伸ばしているか，曲げているか
	private boolean isFront, isBack, isUp, isDown; //手のひらが見えるか，手が上を向いているか，手が下を向いているか，手が左を向いているか
	private boolean isNearK, isNearR;

	public boolean isIntersect() {
		return line.isIntersect(INDEX_DISTAL.getX(), INDEX_DISTAL.getZ(), INDEX_METACARPAL.getX(), INDEX_METACARPAL.getZ(), MIDDLE_DISTAL.getX(), MIDDLE_DISTAL.getZ(), MIDDLE_METACARPAL.getX(), MIDDLE_METACARPAL.getZ());
	}

	private int ANGLE = 150;	//手が真っすぐか判断する閾値

	public void setParameter(int angle) {
		ANGLE = angle;
	}

	//関節が真っすぐかどうか，真っすぐ：true / 曲がっている：false
	public boolean isThumpMpStraight() {
		return ANGLE < angle.calculateAngle(THUMP_METACARPAL, THUMP_PREV, THUMP_PROXIMAL);
	}
	public boolean isThumpPipStraight() {
		return ANGLE < angle.calculateAngle(THUMP_PROXIMAL, THUMP_METACARPAL, THUMP_INTERMEDIATE);
	}
	public boolean isThumpDioStraight() {
		return ANGLE < angle.calculateAngle(THUMP_INTERMEDIATE, THUMP_PROXIMAL, THUMP_DISTAL);
	}

	public boolean isIndexMpStraight() {
		return ANGLE < angle.calculateAngle(INDEX_METACARPAL, INDEX_PREV, INDEX_PROXIMAL);
	}
	public boolean isIndexPipStraight() {
		return ANGLE < angle.calculateAngle(INDEX_PROXIMAL, INDEX_METACARPAL, INDEX_INTERMEDIATE);
	}
	public boolean isIndexDioStraight() {
		return ANGLE < angle.calculateAngle(INDEX_INTERMEDIATE, INDEX_PROXIMAL, INDEX_DISTAL);
	}

	public boolean isMiddleMpStraight() {
		return ANGLE < angle.calculateAngle(MIDDLE_METACARPAL, MIDDLE_PREV, MIDDLE_PROXIMAL);
	}
	public boolean isMiddlePipStraight(){
		return ANGLE < angle.calculateAngle(MIDDLE_PROXIMAL, MIDDLE_METACARPAL, MIDDLE_INTERMEDIATE);
	}
	public boolean isMiddleDioStraight(){
		return ANGLE < angle.calculateAngle(MIDDLE_INTERMEDIATE, MIDDLE_PROXIMAL, MIDDLE_DISTAL);
	}

	public boolean isRingMpStraight() {
		return ANGLE < angle.calculateAngle(RING_METACARPAL, RING_PREV, RING_PROXIMAL);
	}
	public boolean isRingPipStraight(){
		return ANGLE < angle.calculateAngle(RING_PROXIMAL, RING_METACARPAL, RING_INTERMEDIATE);
	}
	public boolean isRingDioStraight(){
		return ANGLE < angle.calculateAngle(RING_INTERMEDIATE, RING_PROXIMAL, RING_DISTAL);
	}

	public boolean isPinkyMpStraight() {
		return ANGLE < angle.calculateAngle(PINKY_METACARPAL, PINKY_PREV, PINKY_PROXIMAL);
	}
	public boolean isPinkyPipStraight(){
		return ANGLE < angle.calculateAngle(PINKY_PROXIMAL, PINKY_METACARPAL, PINKY_INTERMEDIATE);
	}
	public boolean isPinkyDioStraight(){
		return ANGLE < angle.calculateAngle(PINKY_INTERMEDIATE, PINKY_PROXIMAL, PINKY_DISTAL);
	}

	public boolean isNearK() {
		return isNearK;
	}

	public void nearK() {
		double m = -1;
		try {
			m = Math.sqrt(Math.pow(THUMP_DISTAL.getX() - MIDDLE_PROXIMAL.getX(), 2) + Math.pow(THUMP_DISTAL.getY() - MIDDLE_PROXIMAL.getY(), 2) + Math.pow(THUMP_DISTAL.getZ() - MIDDLE_PROXIMAL.getZ(), 2));
		}catch(ArithmeticException e) {
			System.out.println("|sqrit error");
		}
		System.out.println("|nearK:" + m);
		if(m < 40 && m != -1) {
			isNearK = true;
		}else {
			isNearK =  false;
		}
	}
	public boolean isNearR() {
		return isNearR;
	}

	public void nearR() {
		double m = -1;
		try {
			m = Math.sqrt(Math.pow(INDEX_DISTAL.getX() - MIDDLE_PROXIMAL.getX(), 2) + Math.pow(INDEX_DISTAL.getY() - MIDDLE_PROXIMAL.getY(), 2) + Math.pow(INDEX_DISTAL.getZ() - MIDDLE_PROXIMAL.getZ(), 2));
		}catch(ArithmeticException e) {
			System.out.println("|sqrit error");
		}
		System.out.println("|nearR:" + m);
		if(m < 40 && m != -1) {
			isNearR = true;
		}else {
			isNearR =  false;
		}
	}

	public boolean isFront() {
		return isFront;
	}

	private void setIsFront(Hand hand) {
		double roll = Math.toDegrees(hand.palmNormal().roll());
		//System.out.println("|roll: " + roll);
		if(-60< roll && roll < 60) {
			isFront = true;
		}else {
			isFront = false;
		}
	}

	public boolean isBack() {
		return isBack;
	}

	private void setIsBack(Hand hand) {
		double roll = Math.toDegrees(hand.palmNormal().roll());
		//System.out.println("|roll: " + roll);
		System.out.println("**roll: " + roll);
		if(120 < roll || roll < -120) {
			isBack = true;
		}else {
			isBack = false;
		}
	}

	public boolean isUp() {
		return isUp;
	}

	private void setIsUp(Hand hand) {
		double yaw = Math.toDegrees(hand.direction().yaw());
		//System.out.println("|yaw: " + yaw);
		if(-60 < yaw && yaw < 60) {
			isUp = true;
		}else {
			isUp = false;
		}
	}

	public boolean isDown() {
		return isDown;
	}

	private void setIsDown(Hand hand) {
		double yaw = Math.toDegrees(hand.direction().yaw());
		//System.out.println("|yaw: " + yaw);
		if(120 < yaw || yaw < -120) {
			isDown = true;
		}else {
			isDown = false;
		}
	}

	boolean isPich;
	public boolean isPich() {
		return isPich;
	}

	private void setIsPich(Hand hand) {
		if(hand.pinchStrength() * 100 > 90) {isPich = true;}else {isPich = false;}
	}


	//受け取ったフレームの情報を各setter
	public void updateHand(){
		if (!frame.hands().isEmpty()) {
			HandList hands =frame.hands();
			for(Hand hand : hands) {
				setIsFront(hand);
				setIsBack(hand);
				setIsUp(hand);
				setIsDown(hand);
				setIsPich(hand);

				FingerList fingers = hand.fingers();
				for (Finger finger : fingers) {
					Finger.Type fingerType = finger.type();
					setIsFingerStraight(finger, fingerType);
					for(Bone.Type boneType : Bone.Type.values()) {
						Bone bone = finger.bone(boneType);
						if(boneType == Bone.Type.TYPE_METACARPAL) {
							setPrevVector(bone.prevJoint(), fingerType);
						}
						setNextVector(bone.nextJoint(), fingerType, boneType);
					}
				}
			}
			nearK();
			nearR();
		}
	}

	public boolean hasHand() {
		return !frame.hands().isEmpty();
	}

	//指の曲げ伸び
	public void setIsFingerStraight(Finger finger, Finger.Type fingerType) {
		switch(fingerType) {
		case TYPE_THUMB:
			setIsThumbStraight(finger.isExtended());
			break;
		case TYPE_INDEX:
			setIsIndexStraight(finger.isExtended());
			break;
		case TYPE_MIDDLE:
			setIsMiddleStraight(finger.isExtended());
			break;
		case TYPE_PINKY:
			setIsPinkyStraight(finger.isExtended());
			break;
		case TYPE_RING:
			setIsRingStraight(finger.isExtended());
			break;
		default:
			break;
		}
	}

	//指の一番根元の関節のベクトル(座標)
	public void setPrevVector(Vector vec, Finger.Type fingerType) {
		switch(fingerType){
		case TYPE_THUMB:
			//setTHUMB_PREV(vec);
			break;
		case TYPE_INDEX:
			setINDEX_PREV(vec);
			break;
		case TYPE_MIDDLE:
			setMIDDLE_PREV(vec);
			break;
		case TYPE_PINKY:
			setPINKY_PREV(vec);
			break;
		case TYPE_RING:
			setRING_PREV(vec);
			break;
		default:
			break;
		}
	}

	//指の一番根元以外の関節のベクトル(座標)
	public void setNextVector(Vector vec, Finger.Type fingerType, Bone.Type boneType){
		switch(fingerType){
		case TYPE_THUMB:{
			switch(boneType) {
			case TYPE_METACARPAL:
				setTHUMB_METACARPAL(vec);
				break;
			case TYPE_PROXIMAL:
				setTHUMB_PROXIMAL(vec);
				break;
			case TYPE_INTERMEDIATE:
				setTHUMP_INTERMEDIATE(vec);
				break;
			case TYPE_DISTAL:
				setTHUMP_DISTAL(vec);
				break;
			default:
				break;
			}
		}
			break;

		case TYPE_INDEX:{
			switch(boneType) {
			case TYPE_METACARPAL:
				setINDEX_METACARPAL(vec);
				break;
			case TYPE_PROXIMAL:
				setINDEX_PROXIMAL(vec);
				break;
			case TYPE_INTERMEDIATE:
				setINDEX_INTERMEDIATE(vec);
				break;
			case TYPE_DISTAL:
				setINDEX_DISTAL(vec);
				break;
			default:
				break;
			}
		}
			break;

		case TYPE_MIDDLE:{
			switch(boneType) {
			case TYPE_METACARPAL:
				setMIDDLE_METACARPAL(vec);
				break;
			case TYPE_PROXIMAL:
				setMIDDLE_PROXIMAL(vec);
				break;
			case TYPE_INTERMEDIATE:
				setMIDDLE_INTERMEDIATE(vec);
				break;
			case TYPE_DISTAL:
				setMIDDLE_DISTAL(vec);
				break;
			default:
				break;
			}
		}
			break;

		case TYPE_RING:{
			switch(boneType) {
			case TYPE_METACARPAL:
				setRING_METACARPAL(vec);
				break;
			case TYPE_PROXIMAL:
				setRING_PROXIMAL(vec);
				break;
			case TYPE_INTERMEDIATE:
				setRING_INTERMEDIATE(vec);
				break;
			case TYPE_DISTAL:
				setRING_DISTAL(vec);
				break;
			default:
				break;
			}
		}
			break;

		case TYPE_PINKY:{
			switch(boneType) {
			case TYPE_METACARPAL:
				setPINKY_METACARPAL(vec);
				break;
			case TYPE_PROXIMAL:
				setPINKY_PROXIMAL(vec);
				break;
			case TYPE_INTERMEDIATE:
				setPINKY_INTERMEDIATE(vec);
				break;
			case TYPE_DISTAL:
				setPINKY_DISTAL(vec);
				break;
			default:
				break;
			}
		}
			break;
		default:
			break;

		}
	}


//これより以下getter及びsetter
	public Vector getTHUMB_METACARPAL() {
		return THUMP_METACARPAL;
	}

	public void setTHUMB_METACARPAL(Vector tHUMB_METACARPAL) {
		THUMP_METACARPAL = tHUMB_METACARPAL;
	}

	public Vector getTHUMB_PROXIMAL() {
		return THUMP_PROXIMAL;
	}

	public void setTHUMB_PROXIMAL(Vector tHUMB_PROXIMAL) {
		THUMP_PROXIMAL = tHUMB_PROXIMAL;
	}

	public Vector getTHUMB_INTERMEDIATE() {
		return THUMP_INTERMEDIATE;
	}

	public void setTHUMP_INTERMEDIATE(Vector tHUMB_INTERMEDIATE) {
		THUMP_INTERMEDIATE = tHUMB_INTERMEDIATE;
	}

	public Vector getTHUMB_DISTAL() {
		return THUMP_DISTAL;
	}

	public void setTHUMP_DISTAL(Vector tHUMB_DISTAL) {
		THUMP_DISTAL = tHUMB_DISTAL;
	}

	public Vector getINDEX_METACARPAL() {
		return INDEX_METACARPAL;
	}

	public void setINDEX_METACARPAL(Vector iNDEX_METACARPAL) {
		INDEX_METACARPAL = iNDEX_METACARPAL;
	}

	public Vector getINDEX_PROXIMAL() {
		return INDEX_PROXIMAL;
	}

	public void setINDEX_PROXIMAL(Vector iNDEX_PROXIMAL) {
		INDEX_PROXIMAL = iNDEX_PROXIMAL;
	}

	public Vector getINDEX_INTERMEDIATE() {
		return INDEX_INTERMEDIATE;
	}

	public void setINDEX_INTERMEDIATE(Vector iNDEX_INTERMEDIATE) {
		INDEX_INTERMEDIATE = iNDEX_INTERMEDIATE;
	}

	public Vector getINDEX_DISTAL() {
		return INDEX_DISTAL;
	}

	public void setINDEX_DISTAL(Vector iNDEX_DISTAL) {
		INDEX_DISTAL = iNDEX_DISTAL;
	}

	public Vector getMIDDLE_METACARPAL() {
		return MIDDLE_METACARPAL;
	}

	public void setMIDDLE_METACARPAL(Vector mIDDLE_METACARPAL) {
		MIDDLE_METACARPAL = mIDDLE_METACARPAL;
	}

	public Vector getMIDDLE_PROXIMAL() {
		return MIDDLE_PROXIMAL;
	}

	public void setMIDDLE_PROXIMAL(Vector mIDDLE_PROXIMAL) {
		MIDDLE_PROXIMAL = mIDDLE_PROXIMAL;
	}

	public Vector getMIDDLE_INTERMEDIATE() {
		return MIDDLE_INTERMEDIATE;
	}

	public void setMIDDLE_INTERMEDIATE(Vector mIDDLE_INTERMEDIATE) {
		MIDDLE_INTERMEDIATE = mIDDLE_INTERMEDIATE;
	}

	public Vector getMIDDLE_DISTAL() {
		return MIDDLE_DISTAL;
	}

	public void setMIDDLE_DISTAL(Vector mIDDLE_DISTAL) {
		MIDDLE_DISTAL = mIDDLE_DISTAL;
	}

	public Vector getPINKY_METACARPAL() {
		return PINKY_METACARPAL;
	}

	public void setPINKY_METACARPAL(Vector pINKY_METACARPAL) {
		PINKY_METACARPAL = pINKY_METACARPAL;
	}

	public Vector getPINKY_PROXIMAL() {
		return PINKY_PROXIMAL;
	}

	public void setPINKY_PROXIMAL(Vector pINKY_PROXIMAL) {
		PINKY_PROXIMAL = pINKY_PROXIMAL;
	}

	public Vector getPINKY_INTERMEDIATE() {
		return PINKY_INTERMEDIATE;
	}

	public void setPINKY_INTERMEDIATE(Vector pINKY_INTERMEDIATE) {
		PINKY_INTERMEDIATE = pINKY_INTERMEDIATE;
	}

	public Vector getPINKY_DISTAL() {
		return PINKY_DISTAL;
	}

	public void setPINKY_DISTAL(Vector pINKY_DISTAL) {
		PINKY_DISTAL = pINKY_DISTAL;
	}

	public Vector getRING_METACARPAL() {
		return RING_METACARPAL;
	}

	public void setRING_METACARPAL(Vector rING_METACARPAL) {
		RING_METACARPAL = rING_METACARPAL;
	}

	public Vector getRING_PROXIMAL() {
		return RING_PROXIMAL;
	}

	public void setRING_PROXIMAL(Vector rING_PROXIMAL) {
		RING_PROXIMAL = rING_PROXIMAL;
	}

	public Vector getRING_INTERMEDIATE() {
		return RING_INTERMEDIATE;
	}

	public void setRING_INTERMEDIATE(Vector rING_INTERMEDIATE) {
		RING_INTERMEDIATE = rING_INTERMEDIATE;
	}

	public Vector getRING_DISTAL() {
		return RING_DISTAL;
	}

	public void setRING_DISTAL(Vector rING_DISTAL) {
		RING_DISTAL = rING_DISTAL;
	}

	public Vector getTHUMB_PREV() {
		return THUMP_PREV;
	}

	public void setTHUMB_PREV(Vector tHUMB_PREV) {
		THUMP_PREV = tHUMB_PREV;
	}

	public Vector getINDEX_PREV() {
		return INDEX_PREV;
	}

	public void setINDEX_PREV(Vector iNDEX_PREV) {
		INDEX_PREV = iNDEX_PREV;
	}

	public Vector getMIDDLE_PREV() {
		return MIDDLE_PREV;
	}

	public void setMIDDLE_PREV(Vector mIDDLE_PREV) {
		MIDDLE_PREV = mIDDLE_PREV;
	}

	public Vector getPINKY_PREV() {
		return PINKY_PREV;
	}

	public void setPINKY_PREV(Vector pINKY_PREV) {
		PINKY_PREV = pINKY_PREV;
	}

	public Vector getRING_PREV() {
		return RING_PREV;
	}

	public void setRING_PREV(Vector rING_PREV) {
		RING_PREV = rING_PREV;
	}

	public boolean isThumbStraight() {
		return isThumbStraight;
	}

	public void setIsThumbStraight(boolean isThumbStraight) {
		this.isThumbStraight = isThumbStraight;
	}

	public boolean isIndexStraight() {
		return isIndexStraight;
	}

	public void setIsIndexStraight(boolean isIndexStraight) {
		this.isIndexStraight = isIndexStraight;
	}

	public boolean isMiddleStraight() {
		return isMiddleStraight;
	}

	public void setIsMiddleStraight(boolean isMiddleStraight) {
		this.isMiddleStraight = isMiddleStraight;
	}

	public boolean isRingStraight() {
		return isRingStraight;
	}

	public void setIsRingStraight(boolean isRingStraight) {
		this.isRingStraight = isRingStraight;
	}

	public boolean isPinkyStraight() {
		return isPinkyStraight;
	}

	public void setIsPinkyStraight(boolean isPinkyStraight) {
		this.isPinkyStraight = isPinkyStraight;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}
}
