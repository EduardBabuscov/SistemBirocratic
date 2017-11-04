package sistem;

import java.util.ArrayList;
import java.util.List;


public class Document {

    private List<Document> _documenteNecesare;
    private String _type;

    public Document(String type,ArrayList<Document> documenteNecesare){

        _type = type;
        _documenteNecesare = documenteNecesare;
    }

    public List<Document> getDocumenteNecesare(){

        return new ArrayList<>(_documenteNecesare);
    }

    public String getType(){

        return _type;
    }
    
    public boolean containsType(String type){
    	
    	for(Document doc:_documenteNecesare){
    		if(doc.getType().equals(type)){
    			return true;
    		}
    	}
    	
    	return false;
    }

    //??
    public String toString() {
        String s = "";
        for(Document doc: _documenteNecesare) {
            s += doc.getType() + " ";
        }
        return s;
    }

}
