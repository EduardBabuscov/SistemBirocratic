package sistem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Institutie {

    private List<Birou> _birouri;
    private List<Document> _documente;
    private Thread _thread;

    public Institutie() {
        _birouri = new ArrayList<>();
        _documente = new ArrayList<>();
        _thread = new Thread(new GhiseuController(this));
    }
    
    public synchronized void startController(){
    	_thread.start();
    }
    
    public synchronized void acceptRandomSchedule(){
    	
    	_birouri.get(new Random().nextInt(_birouri.size())).acceptRandomSchedule();
    }

    public void addBirou(Birou birou) {
        _birouri.add(birou);
    }

    public void addDocument(Document document) {
    	_documente.add(document);
    }

    //cauta in birouri biroul la care se emite documentul si are cel mai bun ghiseu
    public synchronized Ghiseu getGhiseuPentruDocument(Document document){
        Ghiseu result=null,tmp;
        for(Birou birou :_birouri){
            if(birou.poateEmiteDocument(document)){
                tmp = birou.getGhiseuOptim(document);
                if(tmp !=null ){
                    if(result==null || ((tmp.getNumarClienti()>result.getNumarClienti()))){
                        result = tmp;
                    }
                }
            }
        }
        return result;

    }
}
