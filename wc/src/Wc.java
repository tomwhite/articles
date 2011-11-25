import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Wc {
	public static void main(String[] args) throws IOException {
//		FileOutputStream stream = new FileOutputStream("data/utf-8b.txt");
//		String text = "\u0041\u00DF\u6771\uD801\uDC00";
//		stream.write(text.getBytes("UTF-8"));
//		stream.flush();
		
		String filename = args[0];
		String charsetName = args.length == 1 ? "UTF-8" : args[1];
		
		ByteCounter bc = new ByteCounter();
		InputStream in = new BufferedInputStream(new FileInputStream(filename));
		System.out.format("Bytes: %d\n",  bc.count(in));
		
		CharCounter cc = new CharCounter();
		Reader r = new BufferedReader(new InputStreamReader(new FileInputStream(filename), charsetName));
		System.out.format("Chars: %d\n", cc.count(r));
		
		UnicodeCharacterCounter ucc = new UnicodeCharacterCounter();
		r = new BufferedReader(new InputStreamReader(new FileInputStream(filename), charsetName));
		System.out.format("Unicode characters: %d\n", ucc.count(r));
	}
}
