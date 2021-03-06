package services;

import java.io.IOException;

import core.Service;
import core.Store;
import core.Transmission;
import core.UnavailableException;

public class Returning extends Service {

	public Returning(Store store, Transmission transmission) { super(store, transmission); }

	@Override
	public void run() {
		
		try {
			
			while(true) {
				transmission().send("=============================");
				transmission().send("This is the Returning Service");
				transmission().send(store().listBorrowedDocuments());
				transmission().send("Please enter the document number to return (-2 to quit) : ");
				int document = intFrom(transmission().receive(), -1);
				if(document == -2) break;
				try {
					store().returnDocument(document);
					transmission().send("Document" + document + " has been successfully returned.");
				} catch (UnavailableException e) { 
					transmission().send("ERROR >>>>>>>>>> " + e.getMessage());
					transmission().send("Please press enter to continue...");
					transmission().receive();
				}
			}
			
			transmission().close();
			
		} catch(IOException ioe) { ioe.printStackTrace(); }
	}

}
