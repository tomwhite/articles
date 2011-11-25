import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.codec.binary.Hex;

public class CharacterCounter {

	public int count(Reader in) throws IOException {
		char[] buffer = new char[4096];
		int count = 0;
		int len;
		while((len = in.read(buffer)) != -1) {
			count += len;
			for (int i = 0; i < len; i++) {
				if (Character.isHighSurrogate(buffer[i])) {
					count--;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) throws Exception {
		String text = "\u0041\u00DF\u6771\uD801\uDC00";
		System.out.println(new String(Hex.encodeHex(text.getBytes("UTF-8"))));
		CharacterCounter cc = new CharacterCounter();
		System.out.println(cc.count(new StringReader(text)));
	} 

}
