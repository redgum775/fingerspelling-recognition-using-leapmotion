package fingerspelling_recognition.db_utils;

public class User {
	private int id;
	private String name;
	private int gender;
	private int hand;
	private int year;

    public User(int id, int hand, int year)  {
        this.gender = id;
        this.hand = hand;
        this.year = year;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getHand() {
        return hand;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void show() {
    	System.out.println("gender: " + gender + "hand: " + hand + "year: " + year);
    }
}
