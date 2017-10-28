package sistem;

// Ok, deci la faza cu birourile ma gandeam sa existe birouri pentru actele necesare ale clientului,
// si birouri pentru actele aditionale.
// Clientul cauta prima data un birou care da acte de care are el nevoie,
// afla apoi ca trebuie sa ia alte chestii de la alte birouri, merge la alea and so on.
// Dar hai sa nu ne complicam; sa nu avem prea multe layere de acte aditionale, ca nu vad cum are rost.

import java.util.List;

public class Birou {

	private List<Ghiseu> _ghiseuri;
	private List<Document> _documente;

	public Birou(List<Ghiseu> ghiseuri, List<Document> documente) {

		_ghiseuri = ghiseuri;
		_documente = documente;
	}

	public boolean poateEmiteDocument(Document document) {

		// daca contine un doc de acelasi tip
		return true;
	}

	public Ghiseu getGhiseuOptim() {

		return new Ghiseu();
	}
}
