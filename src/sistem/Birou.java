package sistem;

import java.util.List;

public class Birou {

	private List<Ghiseu> _ghiseuri;
	private List<Document> _documente;
	
	public Birou(List<Ghiseu> ghiseuri,List<Document> documente){
		
		_ghiseuri = ghiseuri;
		_documente = documente;
	}
	
		
		//daca contine un doc de acelasi tip
		return true;
	}
	
		
		return new Ghiseu();
	}
}
