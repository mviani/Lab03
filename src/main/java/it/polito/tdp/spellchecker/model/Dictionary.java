package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Dictionary {
	
	private List<String> parole = new ArrayList<String>();
	public void loadDictionary(String language) {
		
		try {
			FileReader fr = new FileReader(language);
			BufferedReader br = new BufferedReader(fr); 
			String word;
			
			parole.clear();
			
			while ((word = br.readLine()) != null) {
			  parole.add(word);                        
			             }
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
		
	}
	
	public List<String> formatta(String input){
		List<String> formattato =new ArrayList<String>();
		String inputOk=input.replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").toLowerCase();
		String[] t=inputOk.split(" ");
		for(int i=0;i<t.length;i++) {
			formattato.add(t[i]);
		}
		
		return formattato;
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> toCheck = new ArrayList<RichWord>();
		
		for(String s:inputTextList) {
			if(parole.contains(s)) {
				toCheck.add(new RichWord(s,true));
			}
			else toCheck.add(new RichWord(s,false));
		}
		return toCheck;
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
        List<RichWord> toCheck = new ArrayList<RichWord>();
		
		for(String s:inputTextList) {
			toCheck.add(new RichWord(s,false));
			if(s.compareTo(parole.get(parole.size()/2).toLowerCase())==0) {
				toCheck.get(toCheck.size()-1).setCorretta(true);
			}
			
			if(s.compareTo(parole.get(parole.size()/2).toLowerCase())<0) {
				for(int i=parole.size()/2;i>=0;i--) {
					if(s.compareTo(parole.get(i))==0) {
						toCheck.get(toCheck.size()-1).setCorretta(true);
					}
				}
			}
			
			if(s.compareTo(parole.get(parole.size()/2).toLowerCase())>0) {
				for(int i=parole.size()/2;i<parole.size();i++) {
					if(s.compareTo(parole.get(i))==0) {
						toCheck.get(toCheck.size()-1).setCorretta(true);
					}
				}
			}
		}
        
        
        
        
		return toCheck;
	}
	
    public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
    	List<RichWord> toCheck = new ArrayList<RichWord>();
    	
    	for(String s:inputTextList) {
    		toCheck.add(new RichWord(s,false));
    		for(int i=0;i<parole.size();i++) {
    			if(s.compareTo(parole.get(i))==0) {
    				toCheck.get(toCheck.size()-1).setCorretta(true);
    			}
    		}	
    	}
    	return toCheck;
	}
	
}
