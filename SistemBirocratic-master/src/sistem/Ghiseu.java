package sistem;

import java.util.Queue;

public class Ghiseu {
	private Queue<Client> _clienti;

	public boolean isOpen(){
		
		return true;
	}
	
	public synchronized void addClient(Client client){
		
		
	}
	
	public synchronized void doWork(){//poate nu trebuie synchro, depinde...
		
		
	}
	
	public int getNumarClienti(){
		
		return 0;
	}

	// TODO: We probably don't need this
	public Queue<Client> getClienti() {
		return _clienti;
	}

	public void setClienti(Queue<Client> _clienti) {
		this._clienti = _clienti;
	}
}
