package sistem;

import java.util.List;

public class Client implements Runnable{
    public Dosar dosar;
    private int id;
    private Institutie institutie;

    public  Client(int number,Dosar d, Institutie institutie) {
        dosar = d;
        id = number;
        this.institutie = institutie;
    }
    
    public int getId(){
    	
    	return id;
    }

    @Override
    public void run() {

        List<Document> acte =  dosar.getAllDocuments();
        String message = "";
        for(Document act:acte){
        	message += act.getType() + " ";
        }
        System.out.println("Actele necesare pentru clientul " + id +" sunt:" + message );
      
        Document act = null;
        while((act=dosar.getNextObtainableDocument())!=null){
            boolean notOk = true;
            while (notOk) {
                try {
                    if (institutie.getGhiseuPentruDocument(act).doWork(this, act)) {

                        System.out.println("Clientul " + id + " a obtinut actul " + act.getType());
                        notOk = false;
                    } else {

                        //System.out.println("Clientul " + id + " nu a putut obtine actul " + act.getType());
                    }
                }catch (Exception e) {
                    System.out.println("Clientul" + id + " cauta alt birou, fiindca biroul la care statea s-a inchis.");
                }
            }
        }
        
        if(dosar.noMoreDocumentsToObtain()){
        	
        	System.out.println("Clientul " + id + " a reusit sa obtina toate actele.");
        }
        else{
        	
        	System.out.println("Structura de acte nu este valida pentru clientul " + id);
        }

    }

}