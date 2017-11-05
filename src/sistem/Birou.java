package sistem;

import java.util.ArrayList;
import java.util.List;

public class Birou {

    private List<Ghiseu> _ghiseuri;
    private List<Document> _documente;
    private int _numarGhisee;

    public Birou(int numarGhisee,List<Document> documente){

    	_ghiseuri = new ArrayList<>();
        _documente = documente;
        _numarGhisee = numarGhisee;
        createGhisee();
    }
    
    private void createGhisee(){
    	
    	List<String> types = new ArrayList<String>();
    	for(Document doc:_documente){
    		types.add(doc.getType());
    	}
    	
    	for(int i=0;i<_numarGhisee;i++){
    		_ghiseuri.add(new Ghiseu(types));
    	}
    }

    public boolean poateEmiteDocument(Document document){

        for(Document doc : _documente){
            if(doc.getType().equals(document.getType())){
                return true;
            }
        }
        return false;
    }

    public Ghiseu getGhiseuOptim(Document document){

        if(!poateEmiteDocument(document)){
            return null;
        }

        Ghiseu tmp=null;
        for(Ghiseu ghiseu : _ghiseuri){
            if(tmp!=null){
                if(ghiseu.getNumarClienti()<tmp.getNumarClienti()){
                    tmp = ghiseu;
                }
            }
            else{
                tmp=ghiseu;
            }

        }
        return tmp;
    }
}