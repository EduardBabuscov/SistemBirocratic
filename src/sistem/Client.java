package sistem;

import java.util.List;

public class Client implements Runnable{
    private Dosar dosar;
    private int id;
    private Institutie institutie;

    public  Client(int number,Dosar d, Institutie institutie) {
        dosar = d;
        id = number;
        this.institutie = institutie;
    }

    @Override
    public void run() {
        //TODO fa rost de toate dependintele
        List<Document> acte =  dosar.getAllDocuments();
        String message = "";
        for(Document act:acte){
        	message += act.getType() + " ";
        }
        System.out.println("Actele necesare pentru clientul " + id +" sunt:" + message );
        //TODO do while stiva de dependinte nu e goala
       /* for (int i = acte.size() - 1; i >= 0; i--) {
            institutie.getGhiseuPentruDocument(acte.get(i)).doWork(this);
            System.out.println("Clientul " + id + " a obtinut actul " + acte.get(i).getType());
        }*/
        //TODO cauta birou pt primul document
        //TODO birou.doWork()
        //TODO Bifeaza act ca si terminat -- not needed anymore
        
        Document act = null;
        while((act=dosar.getNextObtainableDocument())!=null){
         	if(institutie.getGhiseuPentruDocument(act).doWork(this)){
         		dosar.markDocumentAsObtained(act);
         	}
        	
            System.out.println("Clientul " + id + " a obtinut actul " + act.getType());
        }


        //TODO deci fa sa fie bine ca sa nu fie rau
        //TODO in dosar fa o metoda de compute all needed docs
    }

    //cum vor apela clientii institutia? Din main? Un controller?


}