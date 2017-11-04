package sistem;

import java.util.List;

public class Birou {

    private List<Ghiseu> _ghiseuri;
    private List<Document> _documente;

    public Birou(List<Ghiseu> ghiseuri,List<Document> documente){

        _ghiseuri = ghiseuri;
        _documente = documente;
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