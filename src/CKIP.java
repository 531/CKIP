import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

public class CKIP {
	
	// change to your username and password
	final private static String username = "username";
	final private static String password = "password";

	public static void main(String[] args) throws IOException {

		String text = "反核團體表示，蔣經國過去也曾極力發展核電、認為核電廠絕對安全、甚至研發核武，但接連發生三哩島核災、中美斷交導致核廢料存放出現問題以及1985年的核四事故，讓蔣經國徹底否定專家學者所謂的「核電廠絕對安全論」。";
		
		CKIPChineseAnalyzer analyzer = new CKIPChineseAnalyzer("140.109.19.104", username, password);
		TokenStream stream = analyzer.tokenStream("text", new StringReader(text));
		TermAttribute term = stream.addAttribute(TermAttribute.class);
		
		System.out.println("CKIP Analyzer:" + getSegTerm(stream, term));

	}

	public static String getSegTerm(TokenStream stream, TermAttribute term) {

		StringBuffer buffer = new StringBuffer();

		try {

			while (stream.incrementToken()) {

				buffer.append("[");

				buffer.append(term.term());

				buffer.append("]");

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

		return buffer.toString();

	}
	
}