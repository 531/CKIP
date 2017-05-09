import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

import tw.cheyingwu.ckip.CKIP;
import tw.cheyingwu.ckip.Term;
import tw.cheyingwu.ckip.WordSegmentationService;

/*
 * CKIP斷詞使用的套件
 */
public class CKIPChineseTokenizer extends Tokenizer {

	private int tokenStart = 0, tokenEnd = 0;

	private TermAttribute termAtt;

	private String host, id, pw;

	private WordSegmentationService c;

	ArrayList<String> token = new ArrayList<String>();

	public CKIPChineseTokenizer(Reader reader, String host, String id, String pw) {
		super(reader);
		init(host, id, pw);
	}

	public void init(String host, String id, String pw) {
		this.host = host;
		this.id = id;
		this.pw = pw;
		termAtt = addAttribute(TermAttribute.class);
		c = new CKIP(host, 1501, id, pw);
		BufferedReader br = new BufferedReader(input);
		String temp = "", rawText = "";
		try {
			while ((temp = br.readLine()) != null) {
				rawText += temp;
			}
		} catch (IOException e) {
			System.err.print(0);
		}
		c.setRawText(rawText);
		c.send();
		for (Term t : c.getTerm()) {
			token.add(t.getTerm());
		}
	}

	@Override
	public boolean incrementToken() throws IOException {
		clearAttributes();
		String strToken;
		if (tokenStart < token.size()) {
			strToken = token.get(tokenStart);
			termAtt.setTermBuffer(strToken);
			tokenStart++;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void reset() throws IOException {
		super.reset();
		tokenStart = 0;
	}

	@Override
	public void reset(Reader input) throws IOException {
		super.reset(input);
		reset();
	}

	@Override
	public void end() throws IOException {
		// set final offset
	}

}
