package sistem;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Ghiseu {

    private List<Client> _clienti;
    private boolean _isOpen;
    private Semaphore lock = new Semaphore(1,true);
    private int _readersOfClients=0;

    public Ghiseu(){
        _clienti = new LinkedList<>();
        _isOpen = true;
    }

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


    public boolean doWork(Client client) {//poate nu trebuie synchro, depinde...
        //eu zic ca nu trebuie sa fie synchronized ca altfel stiva aia va fi inutila
        addClient(client);
        try {
            Thread.sleep(1000);//sleep pentru a simula munca biroului
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        removeClient(_clienti.get(0));
        return true;
    }

    public int getNumarClienti(){

        acquireLockBeforeRead();
        int tmp = _clienti.size();
        releaseLockAfterRead();
        return tmp;
    }

    private synchronized void releaseLock(){
        lock.release();
    }

    private synchronized void acquireLock(){
        try {
            lock.acquire();
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