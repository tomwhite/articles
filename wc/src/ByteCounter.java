import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Hex;

public class ByteCounter {

	public int count(InputStream in) throws IOException {
		byte[] buffer = new byte[4096];
		int count = 0;
		int len;
		while((len = in.read(buffer)) != -1) {
			count += len;
		}
		return count;
	}
	
	public static void main(String[] args) throws Exception {
		String text = "\u0041\u00DF\u6771\uD801\uDC00";
		System.out.println(new String(Hex.encodeHex(text.getBytes("UTF-8"))));
		ByteCounter bc = new ByteCounter();
		System.out.println(bc.count(new ByteArrayInputStream(text.getBytes("UTF-8"))));
	} 

}
