package it.polito.tdp.spellchecker.model;

public class RichWord {
 private String parola;
 private boolean isCorretta;
 
 
 
public RichWord(String parola, boolean isCorretta) {
	super();
	this.parola = parola;
	this.isCorretta = isCorretta;
}

public String getParola() {
	return parola;
}

public boolean isCorretta() {
	return isCorretta;
}

public void setParola(String parola) {
	this.parola = parola;
}

public void setCorretta(boolean isCorretta) {
	this.isCorretta = isCorretta;
}

 
}
