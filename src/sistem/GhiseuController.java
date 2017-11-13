package sistem;

import java.util.Random;

public class GhiseuController extends Thread{
	
	private Random random = new Random();
	private Institutie _inst;
	private boolean condition=true;
	
	public GhiseuController(Institutie inst){
		
		_inst=inst;
	}
	
	public void turnOff(){
		condition = false;
	}

	@Override
	public void run() {
		while(condition){
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
