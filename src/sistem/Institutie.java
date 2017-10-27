package sistem;

import java.util.List;

public class Institutie {

	private List<Birou> _birouri;
	
	//cauta in birouri biroul la care se emite documentul si are cel mai bun ghiseu
	public synchronized Ghiseu getGhiseuPentruDocument(Document document){
		Ghiseu result=null,tmp;
		for(Birou birou :_birouri){
			if(birou.poateEmiteDocument(document)){
				tmp = birou.getGhiseuOptim(document);
				if(tmp !=null ){
					if(result==null || ((tmp.getNumarClienti()>result.getNumarClienti()))){
						result = tmp;
					}
				}			
			}
		}
		return result;

	}
}
