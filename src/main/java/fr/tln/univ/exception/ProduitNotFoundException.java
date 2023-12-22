package fr.tln.univ.exception;

public class ProduitNotFoundException extends RuntimeException{

    public ProduitNotFoundException() {
        // TODO Auto-generated constructor stub
    }

    public ProduitNotFoundException(String message){
        super(message);
    }
}
