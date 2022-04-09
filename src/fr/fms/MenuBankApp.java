/**
 * Version 1.0 d'une appli bancaire simplifiÃ©e offrant la possibilitÃ©e de crÃ©er des clients, des comptes bancaires associÃ©s et des opÃ©rations ou
 * transactions bancaires sur ceux-ci telles que : versement, retrait ou virement 
 * + permet d'afficher l'historique des transactions sur un compte
 * + la gestion des cas particuliers est rudimentaire ici puisque la notion d'exception n'a pas encore Ã©tÃ© abordÃ©e
 * 
 * @author El babili - 2022
 * 
 */

package fr.fms;

import java.util.Date;
import java.util.Scanner;

import fr.fms.business.IBankBusinessImpl;
import fr.fms.entities.Account;
import fr.fms.entities.Current;
import fr.fms.entities.Customer;
import fr.fms.entities.Saving;
import fr.fms.entities.Transaction;


public class MenuBankApp {	
	
	/** Flux d'E/S*/
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Initialisation des données pour présentation 
		IBankBusinessImpl bankJob = new IBankBusinessImpl();
		Customer robert = new Customer(1, "dupont", "robert", "robert.dupont@xmail.com");
		Customer julie = new Customer(2, "jolie", "julie", "julie.jolie@xmail.com");		
		Current firstAccount = new Current(100200300, new Date(), 1500, 200 , robert);
		Saving secondAccount = new Saving(200300400, new Date(), 2000, 5.5, julie);		
		bankJob.addAccount(firstAccount);
		bankJob.addAccount(secondAccount);
		int choice = 0;
		long accountId = 0L;
		long targetAccountId = 0L;
		double amount = 0.0;
		
		//Ivite à saisir un numéro de compte
		System.out.println("Veuillez saisir un numéro de compte :");
		while(!scan.hasNextLong())	scan.next();
		accountId = scan.nextLong();
		
		Account customerAccount = bankJob.consultAccount(accountId);
		
		System.out.println("Bienvenu " + customerAccount.getCustomer().getFirstName() +", saisissez le numéro correspondant :");
		
		//Menu principal
		while(choice != 6) {
			System.out.println("1 : Versement - 2 : Retrait - 3 : virement - 4 : information sur ce compte - 5 : liste des opérations - 6 : sortir");		
			while(!scan.hasNextInt())	scan.next();
			choice = scan.nextInt();
			
			switch(choice) {
				case 1 : //Versement
					System.out.println("saisissez le montant à verser sur ce compte :");
					while(!scan.hasNextDouble())	scan.next();
					amount = scan.nextDouble();
					bankJob.pay(accountId, amount);
					
					break;
				
				case 2 : //retrait
					System.out.println("saisissez le montant à retirer sur ce compte :");
					while(!scan.hasNextDouble())	scan.next();
					amount = scan.nextDouble();
					bankJob.withdraw(accountId, amount);
					break;
				
				case 3 ://virement
					System.out.println("saisissez le numéro du compte destinataire :");
					while(!scan.hasNextLong())	scan.next();
					targetAccountId = scan.nextLong();	
					System.out.println("saisissez le montant à verser sur ce compte :");
					while(!scan.hasNextDouble())	scan.next();
					amount = scan.nextDouble();
					bankJob.transfert(accountId, targetAccountId, amount);
					break;
				
				case 4 ://information sur ce compte
					System.out.println(bankJob.consultAccount(accountId));						 
					break;
				
				case 5 : //liste des opérations
					for(Transaction trans : bankJob.listTransactions(accountId))
						System.out.println(trans);								
				break;
				
				case 6 : System.out.println("Au revoir");
					break;
				
				default : System.out.println("mauvaise saisie");
			}			
		}			
	}
}
