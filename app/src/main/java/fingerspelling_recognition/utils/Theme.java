package fingerspelling_recognition.utils;

import java.util.ArrayList;
import java.util.ListIterator;

public class Theme {
	final String theme = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをんがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ";
	final String theme2 = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん";
	final String theme3 = "あいうえおかきくけこさしすせそたちつてとなにぬねはひふへほまみむめもやゆよらるれろわ";
	ArrayList<String> themeArray = new ArrayList<String>();
	ArrayList<String> randomArray = new ArrayList<String>();
	ListIterator<String> iterator;
	int mode = 1;

	public Theme() {
		creatThemeList();
		creatSimpleTheme();
	}

	private void creatThemeList() {
		for (int i = 0; i < theme3.length(); i++) {
		    themeArray.add(String.valueOf(theme3.charAt(i)));
		}
	}

	//お題リストの作成 simple / random
	public void creatSimpleTheme() {
		iterator = themeArray.listIterator(themeArray.size());
	}

	public void createRandomTheme() {
		randomArray.clear();
		ArrayList<String> clone = (ArrayList<String>)themeArray.clone();
		while(!clone.isEmpty()) {
			int rand = (int)(Math.random() * (clone.size()));
			randomArray.add(clone.get(rand));
			clone.remove(rand);
		}
		iterator = randomArray.listIterator(randomArray.size());
	}

	public void choiceMode(int mode) {
		this.mode = mode;
		switch(this.mode){
		case 1:
			creatSimpleTheme();
			break;

		case 2:
			createRandomTheme();
			break;

		default:
			System.out.println("存在しないモード");
		}
	}

	//お題表示
	public String getNextTheme(){	//次のお題を表示
		return iterator.next();
	}

	public boolean hasNext() {	//次の要素があるかどうか
		return iterator.hasNext();
	}

	public void moveHead() {	//リストの頭に戻る
		if(mode == 2) {
			createRandomTheme();
		}
		while(iterator.hasPrevious()) {
			iterator.previous();
		}
	}
}
