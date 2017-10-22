package sistem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dosar {

	//Ar fi nevoie de clasa asta peste Lista de documente (e nevoie de cineva care sa scoata
	//documentele necesare din fiecare document si sa se asigure ca sunt adaugate o singura
	//data in lista de acte.. (Nu e job-ul clasei client, poate al institutiei, dar tot zic
	// ca e mai ok clasa noua)
	
	private List<String> _acteObtinute;
	private Map<String,Document> _documenteNecesare;
	
	public Dosar(List<Document> acteNecesare){
		
		_acteObtinute = new ArrayList<String>();
		_documenteNecesare = new HashMap<String,Document>();
		for(Document document:acteNecesare){
			_documenteNecesare.putAll(getAllDocuments(document));
		}
	}
	
		
		return new ArrayList<Document>(_documenteNecesare.values());
	}

	private Map<String,Document> getAllDocuments(Document act){
		
		if(act==null){
			
			return new HashMap<String,Document>();
		}else{
			
			Map<String,Document> result = new HashMap<String,Document>();
					result.putAll(getAllDocuments(document));
			}
			
			return result;
		}
		
	}
	
	/*	private boolean ContainsType(String type,List<Document> acte){
	
	for(Document document:acte){
		if(document.GetType().equals(type)){
			return true;
		}
	}
	
	return false;
}*/

}
