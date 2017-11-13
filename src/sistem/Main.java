package sistem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    private static Institutie institutie;
    private static Document[] documents;

    public static void main(String[] args){
        int number = 0;
        documents = new Document[9];
        institutie = new Institutie();
        initDocumente();
        initBirouri();
        institutie.startController();
        ArrayList<Thread> clients = new ArrayList<>();
        while (number<100) {
            try {
                int random = new Random().nextInt(6);
                Thread thread = new Thread(new Client(number, new Dosar(documents[random]), institutie));

                System.out.println("Clientul " + number + " a fost creat. Are nevoie de documentul " +
                        documents[random].getType());
                thread.start();
                number++;

                Thread.sleep(new Random().nextInt(3000));
                clients.add(thread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        for(Thread client : clients){
        	try {
				client.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        institutie.turnOffController();

    }
/*
a -> g
b -> d(-> e(->g j)) h
c -> d(-> e(->g j))

d -> e(-> g j) h
e -> g j
f -> d(-> e(->g j)) h
 */
    private static void initDocumente() {

        documents[6] = new Document("G", new ArrayList<>());
        documents[7] = new Document("H", new ArrayList<>());
        documents[8] = new Document("J", new ArrayList<>());

        ArrayList<Document> tmp = new ArrayList<>();
        tmp.add(documents[6]);
        documents[0] = new Document("A", tmp);
        tmp = new ArrayList<>();
        tmp.add(documents[6]);
        tmp.add(documents[8]);
        documents[4] = new Document("E", tmp);
        tmp = new ArrayList<>();
        tmp.add(documents[4]);
        tmp.add(documents[7]);
        documents[3] = new Document("D", tmp);
        tmp = new ArrayList<>();
        tmp.add(documents[3]);
        tmp.add(documents[7]);
        documents[1] = new Document("B", tmp);
        tmp = new ArrayList<>();
        tmp.add(documents[3]);
        documents[2] = new Document("C", tmp);
        tmp = new ArrayList<>();
        tmp.add(documents[3]);
        tmp.add(documents[7]);
        documents[5] = new Document("F", tmp);

        for (int i = 0; i < documents.length; i++) {
            institutie.addDocument(documents[i]);
        }
    }

    private static void initBirouri() {
        institutie.addBirou(new Birou(2,
                Arrays.asList(documents[0], documents[1], documents[2]),"birou1"));
        institutie.addBirou(new Birou(1,
                Arrays.asList(documents[3], documents[4], documents[5]),"birou2"));
        institutie.addBirou(new Birou(3,
                Arrays.asList(documents[6], documents[7], documents[8]),"birou3"));
    }
}
