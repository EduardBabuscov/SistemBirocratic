package sistem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Ghiseu {

    private List<Client> _clienti;
    private boolean _isOpen;
    private Semaphore _lock = new Semaphore(1,true);
    private int _readersOfClients=0;
    private List<String> _documentTypes;

    public Ghiseu(List<String> documentTypes){
        _clienti = new LinkedList<>();
        _isOpen = true;
        _documentTypes = new ArrayList<>(documentTypes);
    }
    
/*    public void replaceDocumentTypes(List<String> docs){
    	_documentTypes = new ArrayList<>(docs);
    }*/

    public boolean isOpen(){

        return _isOpen;
    }

    public void addClient(Client client){
        acquireLock();
        if(_isOpen)
        {
            _clienti.add(client);
        }
        releaseLock();
    }

    public void removeClient(Client client){
        acquireLock();
        _clienti.remove(client);
        releaseLock();
    }


    public boolean doWork(Client client,Document act) {

    	if(!_documentTypes.contains(act.getType()) || !client.dosar.checkIfDocumentHasAllRequired(act)){
    		return false;
    	}
    	
        addClient(client);
        try {
            Thread.sleep(1000);//sleep pentru a simula munca ghiseului
        } catch (InterruptedException e) {
            e.printStackTrace();
            removeClient(client);
            return false;
        }
        
        client.dosar.markDocumentAsObtained(act);
        removeClient(client);
        return true;
    }

    public int getNumarClienti(){

        acquireLockBeforeRead();
        int tmp = _clienti.size();
        releaseLockAfterRead();
        return tmp;
    }

    private synchronized void releaseLock(){
    	_lock.release();
    }

    private synchronized void acquireLock(){
        try {
        	_lock.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private synchronized void releaseLockAfterRead(){

        _readersOfClients--;
        if(_readersOfClients==0){
            releaseLock();
        }
    }

    private synchronized void acquireLockBeforeRead(){
        _readersOfClients++;
        if(_readersOfClients==1){
            acquireLock();
        }
    }
}