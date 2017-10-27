package sistem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Ghiseu {

	private List<Client> _clienti;
	private boolean _isOpen;
	private Semaphore lock = new Semaphore(1,true);
	private int _readersOfClients=0;
	
	public Ghiseu(){
		_clienti = new ArrayList<Client>();
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
	
	
	public synchronized void doWork(){//poate nu trebuie synchro, depinde...
		
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
