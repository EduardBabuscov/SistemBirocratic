package sistem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dosar {

    private List<Document> _acteObtinute;
    private Map<String,Document> _documenteNecesare;

    public Dosar(Document actNecesar) {
        _acteObtinute = new ArrayList<Document>();
        _documenteNecesare = new HashMap<>();
        flattenAndAddDocuments(actNecesar);
    }

    public Dosar(List<Document> acteNecesare){

        _acteObtinute = new ArrayList<Document>();
        _documenteNecesare = new HashMap<String,Document>();
        for(Document document:acteNecesare){
            if(!_documenteNecesare.containsKey(document.getType())){
            	flattenAndAddDocuments(document);
            }

        }
    }
    
    public Document getNextObtainableDocument(){
    	
    	List<Document> tmpList = new ArrayList<>(_documenteNecesare.values());
    	for(Document candidate:tmpList){
    		boolean flag=true;
    		for(Document docNecesar: candidate.getDocumenteNecesare()){
    			flag = checkIfDocumentIsOwned(docNecesar);
    			if(flag==false){
    				break;
    			}
    		}
    		
    		if(flag==true){
				return candidate;
			}  		
    	}
    	
    	return null;
    }
    
    public boolean checkIfDocumentHasAllRequired(Document document){
    	
    	for(Document doc:document.getDocumenteNecesare()){
    		if(!checkIfDocumentIsOwned(doc)){
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    private boolean checkIfDocumentIsOwned(Document document){
    	
    	for(Document doc:_acteObtinute){    		
    		if(doc.getType().equals(document.getType())){
    			return true;
    		}
    	}
    	return false;
    }
    
    public void markDocumentAsObtained(Document doc){
    	
    	_acteObtinute.add(doc);
    	_documenteNecesare.remove(doc.getType());
    }
    
    private void flattenAndAddDocuments(Document actNecesar){
    	
    	ArrayList<Document> documents = getAllDocuments(actNecesar);
    	for(Document doc:documents){		
    		_documenteNecesare.put(doc.getType(), doc);
    	}
    }

    public List<Document> getAllDocuments(){
        return new ArrayList<Document>(_documenteNecesare.values());
    }
    
    public boolean noMoreDocumentsToObtain(){
    	
    	return _documenteNecesare.size()==0;
    }


    private ArrayList<Document> getAllDocuments(Document act){

        if(act==null){

            return null;
        }else{
        	ArrayList<Document> result = new ArrayList<Document>();
            result.add(act); 
            for(Document document:act.getDocumenteNecesare()){
                if(!_documenteNecesare.containsKey(document.getType())){
                    result.addAll(getAllDocuments(document));
                }
            }
            return result;
        }

    }

}
