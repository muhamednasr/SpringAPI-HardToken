package com.eta.jsonhandle;

import java.util.Iterator;
import com.fasterxml.jackson.databind.JsonNode;

public class CanonicalJson {
	
	private String fieldName;
	private String valAsStr;
	private String CanonicalDoc = "";
	
	public String canonicalizeJson(JsonNode jsonNode) throws Exception{
			
		try {
			
			Iterator<String> DocFieldNames = jsonNode.fieldNames();
			
 			  while(DocFieldNames.hasNext()) {
				  
				 fieldName = DocFieldNames.next();
				 String arrName;
				 
				 if(fieldName != "signatures") {
					 
					 JsonNode fieldValueAsJson = jsonNode.get(fieldName);
					 
					 if (fieldValueAsJson.isArray()) {
						
						 Iterator<JsonNode> arrNodeList =fieldValueAsJson.iterator() ;
						 						 
						 arrName = "\""+ fieldName.toUpperCase() + "\"";
						 CanonicalDoc += arrName;
						 
						 while(arrNodeList.hasNext()) {
								 CanonicalDoc += arrName;

							 canonicalizeJson(arrNodeList.next()); 
							 
						 }
//						 arrName = "";
					 }
					 else if(fieldValueAsJson.isObject()){
							
							CanonicalDoc += "\""+fieldName.toUpperCase() + "\"";
							canonicalizeJson(fieldValueAsJson);
						 
					 }
					 else {
						 CanonicalElement(fieldValueAsJson); 
					 }
				 } 				  
			  }  
		}
		catch(Exception ex) {
			
		}	
		
		return CanonicalDoc;
	}
	  
	
	  private void CanonicalElement(JsonNode element) {
		  
		  valAsStr = element.asText();
		    
			  CanonicalDoc += "\""+ fieldName.toUpperCase() + "\"" + "\"" + valAsStr + "\"";
		  	  
	  }
	  
}
