package sistem;

import java.util.List;

public class Institutie {

	private List<Birou> _birouri;
	
	//cauta in birouri biroul la care se emite documentul si are cel mai bun ghiseu
	public synchronized Ghiseu GetGhiseuPentruDocument(Document document){
		Ghiseu result=null,tmp;
		for(Birou birou :_birouri){
			if(birou.PoateEmiteDocument(document)){
				tmp = birou.GetGhiseuOptim();
				if(tmp !=null ){
					if(result==null || ((tmp.GetNumarClienti()>result.GetNumarClienti()))){
						result = tmp;
					}
				}			
			}
		}
		return result;
	}
}
