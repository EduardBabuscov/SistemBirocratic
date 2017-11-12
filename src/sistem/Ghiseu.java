package sistem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Ghiseu {

    private List<Client> _clienti;
    private boolean _isOpen;
    private List<String> _documentTypes;
    private String _numeGhiseu;

    public Ghiseu(List<String> documentTypes,String numeGhiseu){
        _clienti = new LinkedList<>();
        _isOpen = true;
        _documentTypes = new ArrayList<>(documentTypes);
        _numeGhiseu = numeGhiseu;
    }
    

    public synchronized boolean isOpen(){
        return _isOpen;
    }
    
    
    public synchronized void changeState(){
    	System.out.println("Se schimba starea la ghiseul "+ _numeGhiseu);
    	
    	_isOpen =!_isOpen;
    	if(_isOpen){
    		System.out.println("Ghiseul "+ _numeGhiseu+ " este deschis");
    	}else{
    		System.out.println("Ghiseul "+ _numeGhiseu+ " este inchis");
    	}	
		
    }
    

    public synchronized void addClient(Client client){     
        //System.out.println("add" + _clienti.size());
         _clienti.add(client);
    }

    public synchronized void removeClient(Client client){
        _clienti.remove(client);
        //System.out.println("remove" + _clienti.size());
    }


    public boolean doWork(Client client,Document act) throws Exception {

    	if(!_isOpen || !_documentTypes.contains(act.getType()) || !client.dosar.checkIfDocumentHasAllRequired(act)){
    		return false;
    	}
    	
        addClient(client);
    	if(!isOpen())
    	    throw new Exception();

    	if(!_isOpen){
    		removeClient(client);
    		System.out.println("Clientul" + client.getId() + " nu a putut obtine actul " + act.getType() + " pt ca s-a inchis ghiseul "+_numeGhiseu);
    		return false;
    		
    	}
        synchronized (this) {
        	if(_clienti.get(0).getId()!=client.getId()){
        		Thread.currentThread().wait();
        	}
    	System.out.println("Clientului " + client.getId() + " i-a venit randul pt actul " + act.getType() + " la ghiseul "+_numeGhiseu);

            try {
                Thread.sleep(1000);//sleep pentru a simula munca ghiseului
            } catch (InterruptedException e) {
                e.printStackTrace();
                removeClient(client);
                return false;
            }

            
            client.dosar.markDocumentAsObtained(act);  
            removeClient(client);
            if(_clienti.size()>0){
            	_clienti.get(0).notify();
            }
            
        }
        
        return true;
    }

    public synchronized int getNumarClienti(){
        return _clienti.size();
    }
}