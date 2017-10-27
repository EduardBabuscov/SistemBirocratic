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
	
	// TODO: De ce avem nevoie de _documenteNecesare in afara clasei? De ce nu putem s-o facem in Document?
	// Marked for deletion
	public List<Document> getDocumenteNecesare(){
		
		return new ArrayList<Document>(_documenteNecesare);
	}
	
	public String getType(){
		
		return _type;
	}
	
	public Document getDocumentNecesar() {
		// TODO
		return null;
	}

}
