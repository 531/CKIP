
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
//import org.apache.lucene.analysis.cn.ChineseAnalyzer;
//import org.apache.lucene.analysis.cn.smart.SentenceTokenizer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;

import tw.cheyingwu.ckip.CKIP;
import tw.cheyingwu.ckip.WordSegmentationService;

/*
 * 建立CKIPAnalyzer
 */
public class CKIPChineseAnalyzer extends Analyzer{
	private String id;
	private String pw;
	private String host;
	private WordSegmentationService c;
	
	public CKIPChineseAnalyzer(String host,String id, String pw){
		this.id=id;
		this.pw=pw;
		this.host=host;
	}
	
	@Override
	public TokenStream tokenStream(String arg0, Reader reader) {
		TokenStream result = new CKIPChineseTokenizer(reader,host,id,pw);
		return result;
	}

}
