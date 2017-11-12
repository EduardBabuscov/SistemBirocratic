package sistem;

import java.util.Random;

public class GhiseuController implements Runnable {
	
	private Random random = new Random();
	private Institutie _inst;
	
	public GhiseuController(Institutie inst){
		
		_inst=inst;
	}

	@Override
	public void run() {
		while(true){
			 float chance = random.nextFloat();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (chance <= 0.10f){
					 _inst.acceptRandomSchedule();
				}
		
		}
	}
}
