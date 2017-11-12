package sistem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Birou {

    private List<Ghiseu> _ghiseuri;
    private List<Document> _documente;
    private int _numarGhisee;
    private String _numeBirou;

    public Birou(int numarGhisee,List<Document> documente,String numeBirou){

    	_ghiseuri = new ArrayList<>();
        _documente = documente;
        _numarGhisee = numarGhisee;
        _numeBirou = numeBirou;
        createGhisee();
    }
    
    public synchronized void acceptRandomSchedule(){
    	
    	_ghiseuri.get(new Random().nextInt(_ghiseuri.size())).changeState();

    }
    
    private void createGhisee(){
    	
    	List<String> types = new ArrayList<String>();
    	for(Document doc:_documente){
    		types.add(doc.getType());
    	}
    	
    	for(int i=0;i<_numarGhisee;i++){
    		_ghiseuri.add(new Ghiseu(types,i+_numeBirou));
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
                if((ghiseu.getNumarClienti()<tmp.getNumarClienti()) && ghiseu.isOpen()){
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