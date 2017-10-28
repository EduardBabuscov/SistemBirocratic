package sistem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Dosar {

	// Ar fi nevoie de clasa asta peste Lista de documente (e nevoie de cineva care
	// sa scoata documentele necesare din fiecare document si sa se asigure ca sunt
	// adaugate o singura data in lista de acte.. (Nu e job-ul clasei client, poate
	// al institutiei, dar tot zic ca e mai ok clasa noua)

	private Map<Document, Boolean> _documenteNecesare; /*
														 * Sunt documentele necesare clientului care detine dosarul. Un
														 * tip de Document este mapat unui flag care sa indice daca
														 * clientul il are sau nu. Clientul va pleca dupa ce are toate
														 * actele de care are nevoie.
														 */
	private List<Document> _acteAditionale; /*
											 * Sunt acte dobandite pe parcurs, de care clientul are nevoie pentru a
											 * obtine documentele necesare.
											 */

	public Dosar(List<Document> acteNecesare) {
		_acteAditionale = new ArrayList<Document>();
		_documenteNecesare = new HashMap<Document, Boolean>();
		for (Document document : acteNecesare) {
			_documenteNecesare.put(document, false);
		}
	}

	public List<Document> getDocumenteNecesare() {
		ArrayList<Document> documente = new ArrayList<Document>();

		for (Entry<Document, Boolean> entry : _documenteNecesare.entrySet()) {
			documente.add(entry.getKey());
		}
		return documente;
	}
	
	/*
	 * Functia asta determina de care acte aditionale mai este nevoie pentru a elibera un document de care clientul are nevoie.
	 * Daca toate actele aditionale pentru documentul necesar (primit ca parametru), functia va returna null.
	 */
	public ArrayList<Document> acteAditionaleLipsaPentru(Document doc) {
		ArrayList<Document> ret = new ArrayList<Document>();
		ArrayList<Document> necesare = doc.getDocumenteNecesare();
		
		// Verificam daca gasim in "_acteAditionale" toate elementele din "necesare"
		for (Document itr : _acteAditionale) {
			if (necesare.contains(itr) == false) {
				ret.add(itr);
			}
		}
		if (ret.isEmpty()) {
			return null;
		}
		return ret;
	}
}
