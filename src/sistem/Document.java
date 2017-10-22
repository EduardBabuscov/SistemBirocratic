package sistem;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {

	private List<Document> _documenteNecesare;
	private String _type;
	
	public Document(String type,ArrayList<Document> documenteNecesare){
		
		_type = type;
		_documenteNecesare = documenteNecesare;
	}
	
	public List<Document> getDocumenteNecesare(){
		
		return new ArrayList<Document>(_documenteNecesare);
	}
	
	public String getType(){
		
		return _type;
	}

}
