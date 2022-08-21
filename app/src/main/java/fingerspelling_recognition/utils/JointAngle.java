package fingerspelling_recognition.utils;

import com.leapmotion.leap.Vector;

public class JointAngle{
	Angle angle = new Angle();

	private double THUMP_MP = 0;
	private double THUMP_PIP = 0;
	private double THUMP_DIO = 0;

	private double INDEX_MP = 0;
	private double INDEX_PIP = 0;
	private double INDEX_DIO = 0;

	private double MIDDLE_MP = 0;
	private double MIDDLE_PIP = 0;
	private double MIDDLE_DIO = 0;

	private double RING_MP = 0;
	private double RING_PIP = 0;
	private double RING_DIO = 0;

	private double PINKY_MP = 0;
	private double PINKY_PIP = 0;
	private double PINKY_DIO = 0;

	public void jointAngle(HandJoint hand){
		setTHUMP_MP(hand.getTHUMB_METACARPAL(), hand.getTHUMB_PREV(), hand.getTHUMB_PROXIMAL());
		setTHUMP_PIP(hand.getTHUMB_PROXIMAL(), hand.getTHUMB_METACARPAL(), hand.getTHUMB_INTERMEDIATE());
		setTHUMP_DIO(hand.getTHUMB_INTERMEDIATE(), hand.getTHUMB_PROXIMAL(), hand.getTHUMB_DISTAL());

		setINDEX_MP(hand.getINDEX_METACARPAL(), hand.getINDEX_PREV(), hand.getINDEX_PROXIMAL());
		setINDEX_PIP(hand.getINDEX_PROXIMAL(), hand.getINDEX_METACARPAL(), hand.getINDEX_INTERMEDIATE());
		setINDEX_DIO(hand.getINDEX_INTERMEDIATE(), hand.getINDEX_PROXIMAL(), hand.getINDEX_DISTAL());

		setMIDDLE_MP(hand.getMIDDLE_METACARPAL(), hand.getMIDDLE_PREV(), hand.getMIDDLE_PROXIMAL());
		setMIDDLE_PIP(hand.getMIDDLE_PROXIMAL(), hand.getMIDDLE_PREV(), hand.getMIDDLE_INTERMEDIATE());
		setMIDDLE_DIO(hand.getMIDDLE_INTERMEDIATE(), hand.getMIDDLE_PROXIMAL(), hand.getMIDDLE_DISTAL());

		setRING_MP(hand.getRING_METACARPAL(), hand.getRING_PREV(), hand.getRING_PROXIMAL());
		setRING_PIP(hand.getRING_PROXIMAL(), hand.getRING_METACARPAL(), hand.getRING_INTERMEDIATE());
		setRING_DIO(hand.getRING_INTERMEDIATE(), hand.getRING_PROXIMAL(), hand.getRING_DISTAL());

		setPINKY_MP(hand.getPINKY_METACARPAL(), hand.getPINKY_PREV(), hand.getPINKY_PROXIMAL());
		setPINKY_PIP(hand.getPINKY_PROXIMAL(), hand.getPINKY_METACARPAL(), hand.getPINKY_INTERMEDIATE());
		setPINKY_DIO(hand.getPINKY_INTERMEDIATE(), hand.getPINKY_PROXIMAL(), hand.getPINKY_DISTAL());
	}

	public String log(){
		String log = "|THUMP  : MP(" + THUMP_MP + "), PIP(" + THUMP_PIP + "), DIO(" + THUMP_DIO + ")" + "\n"
				+ "|INDEX  : MP(" + INDEX_MP + "), PIP(" + INDEX_PIP + "), DIO(" + INDEX_DIO + ")" + "\n"
				+ "|MIDDLE : MP(" + MIDDLE_MP + "), PIP(" + MIDDLE_PIP + "), DIO(" + MIDDLE_DIO + ")" + "\n"
				+ "|RING   : MP(" + RING_MP + "), PIP(" + RING_PIP + "), DIO(" + RING_DIO + ")" + "\n"
				+ "|PINKY  : MP(" + PINKY_MP + "), PIP(" + PINKY_PIP + "), DIO(" + PINKY_DIO + ")" + "\n";
		return log;
	}

	public double getTHUMP_MP() {
		return THUMP_MP;
	}
	public void setTHUMP_MP(Vector THUMB_METACARPAL, Vector THUMB_PREV, Vector THUMB_PROXIMAL) {
		THUMP_MP = angle.calculateAngle(THUMB_METACARPAL, THUMB_PREV, THUMB_PROXIMAL);
	}

	public double getTHUMP_PIP() {
		return THUMP_PIP;
	}
	public void setTHUMP_PIP(Vector THUMB_PROXIMAL, Vector THUMB_METACARPAL, Vector THUMP_INTERMEDIATE) {
		THUMP_PIP =angle.calculateAngle(THUMB_PROXIMAL, THUMB_METACARPAL, THUMP_INTERMEDIATE);
	}

	public double getTHUMP_DIO() {
		return THUMP_DIO;
	}
	public void setTHUMP_DIO(Vector THUMP_INTERMEDIATE, Vector THUMB_PROXIMAL, Vector THUMP_DISTAL) {
		THUMP_DIO = angle.calculateAngle(THUMP_INTERMEDIATE, THUMB_PROXIMAL, THUMP_DISTAL);
	}


	public double getINDEX_MP() {
		return INDEX_MP;
	}
	public void setINDEX_MP(Vector INDEX_METACARPAL, Vector INDEX_PREV, Vector INDEX_PROXIMAL) {
		INDEX_MP = angle.calculateAngle(INDEX_METACARPAL, INDEX_PREV, INDEX_PROXIMAL);
	}

	public double getINDEX_PIP() {
		return INDEX_PIP;
	}
	public void setINDEX_PIP(Vector INDEX_PROXIMAL, Vector INDEX_METACARPAL, Vector INDEX_INTERMEDIATE) {
		INDEX_PIP =angle.calculateAngle(INDEX_PROXIMAL, INDEX_METACARPAL, INDEX_INTERMEDIATE);
	}

	public double getINDEX_DIO() {
		return INDEX_DIO;
	}
	public void setINDEX_DIO(Vector INDEX_INTERMEDIATE, Vector INDEX_PROXIMAL, Vector INDEX_DISTAL) {
		INDEX_DIO = angle.calculateAngle(INDEX_INTERMEDIATE, INDEX_PROXIMAL, INDEX_DISTAL);
	}


	public double getMIDDLE_MP() {
		return MIDDLE_MP;
	}
	public void setMIDDLE_MP(Vector MIDDLE_METACARPAL, Vector MIDDLE_PREV, Vector MIDDLE_PROXIMAL) {
		MIDDLE_MP = angle.calculateAngle(MIDDLE_METACARPAL, MIDDLE_PREV, MIDDLE_PROXIMAL);
	}

	public double getMIDDLE_PIP() {
		return MIDDLE_PIP;
	}
	public void setMIDDLE_PIP(Vector MIDDLE_PROXIMAL, Vector MIDDLE_METACARPAL, Vector MIDDLE_INTERMEDIATE) {
		MIDDLE_PIP =angle.calculateAngle(MIDDLE_PROXIMAL, MIDDLE_METACARPAL, MIDDLE_INTERMEDIATE);
	}

	public double getMIDDLE_DIO() {
		return MIDDLE_DIO;
	}
	public void setMIDDLE_DIO(Vector MIDDLE_INTERMEDIATE, Vector MIDDLE_PROXIMAL, Vector MIDDLE_DISTAL) {
		MIDDLE_DIO = angle.calculateAngle(MIDDLE_INTERMEDIATE, MIDDLE_PROXIMAL, MIDDLE_DISTAL);
	}


	public double getPINKY_MP() {
		return PINKY_MP;
	}
	public void setPINKY_MP(Vector PINKY_METACARPAL, Vector PINKY_PREV, Vector PINKY_PROXIMAL) {
		PINKY_MP = angle.calculateAngle(PINKY_METACARPAL, PINKY_PREV, PINKY_PROXIMAL);
	}

	public double getPINKY_PIP() {
		return PINKY_PIP;
	}
	public void setPINKY_PIP(Vector PINKY_PROXIMAL, Vector PINKY_METACARPAL, Vector PINKY_INTERMEDIATE) {
		PINKY_PIP =angle.calculateAngle(PINKY_PROXIMAL, PINKY_METACARPAL, PINKY_INTERMEDIATE);
	}

	public double getPINKY_DIO() {
		return PINKY_DIO;
	}
	public void setPINKY_DIO(Vector PINKY_INTERMEDIATE, Vector PINKY_PROXIMAL, Vector PINKY_DISTAL) {
		PINKY_DIO = angle.calculateAngle(PINKY_INTERMEDIATE, PINKY_PROXIMAL, PINKY_DISTAL);
	}


	public double getRING_MP() {
		return RING_MP;
	}
	public void setRING_MP(Vector RING_METACARPAL, Vector RING_PREV, Vector RING_PROXIMAL) {
		RING_MP = angle.calculateAngle(RING_METACARPAL, RING_PREV, RING_PROXIMAL);
	}

	public double getRING_PIP() {
		return RING_PIP;
	}
	public void setRING_PIP(Vector RING_PROXIMAL, Vector RING_METACARPAL, Vector RING_INTERMEDIATE) {
		RING_PIP =angle.calculateAngle(RING_PROXIMAL, RING_METACARPAL, RING_INTERMEDIATE);
	}

	public double getRING_DIO() {
		return RING_DIO;
	}
	public void setRING_DIO(Vector RING_INTERMEDIATE, Vector RING_PROXIMAL, Vector RING_DISTAL) {
		RING_DIO = angle.calculateAngle(RING_INTERMEDIATE, RING_PROXIMAL, RING_DISTAL);
	}

}
