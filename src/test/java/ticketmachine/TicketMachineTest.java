package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}

	@Test
	//S3 : on n’imprime pas le ticket si le montant inséré est insuffisant
	void noPrintTicketArgentInsuffisant() {
		machine.insertMoney(PRICE -1);
		assertFalse(machine.printTicket(), "Le ticket ne doit pas être imprimé");
	}

	@Test
	// S4 :  on imprime le ticket si le montant inséré est suffisant
	void noPrintTicketArgentSuffisant() {
		machine.insertMoney(PRICE +1 );
		assertTrue(machine.printTicket(), "Le ticket ne doit pas être imprimé");
	}

	@Test
	//S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
	void balanceDecrementee() {
		machine.insertMoney(PRICE + 10);
		machine.printTicket();
		assertEquals(10, machine.getBalance(), "La balance a été décrémentée du prix du ticket");
	}

	@Test
	// S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	void majAfterPrintTicket() {
		machine.insertMoney(PRICE + 10);
		machine.printTicket();
		assertEquals(PRICE,machine.getTotal(), "Le montant est bien collecté apres le print");
	}
	@Test
	//S6 *
	void pasmajBeforePrint() {
		machine.insertMoney(PRICE + 10);
		assertEquals(0, machine.getTotal(), "Le total n'est pas MAJ");
	}

	@Test
	// S7 : refund()rendcorrectement la monnaie
	void rendcorrectementMonnaie() {
		machine.insertMoney(PRICE + 10);
		machine.printTicket();
		assertTrue(machine.refund()==10, "La machine rend bien la monnaie en trop.");
	}

	@Test
	// S8
	void banlanceAzero() {
		machine.insertMoney(PRICE + 10);
		machine.printTicket();
		machine.refund();
		assertTrue(machine.getBalance()==0, "La balance est à 0");
	}

	@Test
	//S9 : on ne peut pas insérer un montant négatif
	void insererMontantNegatif() {
		try {
			machine.insertMoney(-PRICE);
			fail("Argument must not be null");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	// S10 : on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	void noPrintNegativePrice() {
	try {
		machine = new TicketMachine(-50);
		fail("Argument must not be null");
	} catch (IllegalArgumentException e) {
		}
	}




}
