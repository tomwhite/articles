import java.text.BreakIterator;

import org.apache.commons.codec.binary.Hex;

public class UserCharacterCounter {
	public int count(String text) {
		int count = 0;
		BreakIterator iter = BreakIterator.getCharacterInstance();
		iter.setText(text);
		while (iter.next() != BreakIterator.DONE) {
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) throws Exception {
		String text = "\u0041\u00DF\u6771\uD801\uDC00";
		System.out.println(new String(Hex.encodeHex(text.getBytes("UTF-8"))));
		UserCharacterCounter ucc = new UserCharacterCounter();
		System.out.println(ucc.count(text));
	} 	
}
