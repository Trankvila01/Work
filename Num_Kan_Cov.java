package conversion;

import java.util.ArrayList;

public class Num_Kan_Cov {
	
	private String[] kanjiNumber;
	private String[] kanjiKeta1;
	private String[] kanjiKeta2;
	
	public Num_Kan_Cov() {
		
		setValue();

	}

	public void setValue() {
		
		kanjiNumber = Num_Kan_Const.kanjiNumber;
		kanjiKeta1 = Num_Kan_Const.kanjiKeta1;
		kanjiKeta2 = Num_Kan_Const.kanjiKeta2;

	}
	
	/**
	 * 位の漢字（十,百,千,万,億など）を用いた漢数字に変換
	 * @param inputStr 算用数字
	 * @return 漢数字
	 */
	public String numKanCov (String inputStr) {
		
		String[] number = inputStr.split(Num_Kan_Const.Blank);
		ArrayList<String> al = new ArrayList<String>();

		// 入力した文字を反転
		for(int cnt = inputStr.length()-1; cnt>=0; cnt--) {
			al.add(number[cnt]);
		}
		
		// 漢数字へ文字変換
		StringBuffer sb = new StringBuffer();
		int keta_cnt = 0;
		
		for(String number_element: al) {
			// 各要素
			int element = Integer.parseInt(number_element);
			// 1桁ごとの位
			int position = keta_cnt%Num_Kan_Const.separateNumber;
			// 4桁ごとの位
			int position2 = keta_cnt/Num_Kan_Const.separateNumber; 
			
			// 十,百,千の漢字（0以外）
			if(element!=0) {
				sb.append(kanjiKeta1[position]);
			}
			// 一～九の漢字（十,百,千の位での"一"は省略）
			if(position>0 && element!=1) {
				sb.append(kanjiNumber[element]);
			}else if(position==0) {
			// 万,億,兆,京の4桁ごとの漢字
				sb.append(kanjiKeta2[position2]);
				sb.append(kanjiNumber[element]);
			}
			keta_cnt++;
		}
		
		// 漢数字変換した文字列を再び反転
		return sb.reverse().toString();
	}
	
	/**
	 * 位の漢字を用いない漢数字に変換
	 * @param inputStr 算用数字
	 * @return 漢数字
	 */
	public String numKanCov_2 (String inputStr) {
		
		String[] number = inputStr.split(Num_Kan_Const.Blank);
		ArrayList<String> al = new ArrayList<String>();

		// 入力した文字を反転
		for(int cnt = inputStr.length()-1; cnt>=0; cnt--) {
			al.add(number[cnt]);
		}
		
		// 漢数字へ文字変換
		StringBuffer sb = new StringBuffer();
		int keta_cnt = 0;
		
		for(int cntCov = 0; cntCov<al.size(); cntCov++) {
			// 各要素
			int element = Integer.parseInt(al.get(cntCov));
			
			if(element!=0) {
				sb.append(kanjiNumber[element]);
			} else {
				sb.append(Num_Kan_Const.kanjiZero);
			}
			// 3桁ずつカンマを入れる 
			if(keta_cnt==Num_Kan_Const.separateNumberNonPosi
			&& cntCov<al.size()-1) {
				sb.append(Num_Kan_Const.comma);
				keta_cnt = -1;
			}
			keta_cnt++;		
		}
		
		// 漢数字変換した文字列を再び反転
		return sb.reverse().toString();
	}

}
