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
	// On vérifie que le prix affiché correspond au paramètre passé lors de
	// l'initialisation
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
	// S3 : on imprime le ticket si le montant inséré est suffisant
	void nimprimePasSiBalanceInsuffisante() {
		//GIVEN: une machine virtuelle (initialisée dans beforeeach
		//WHEN on ne met pas assez d'argent
		//On met pas assez d'argent
		machine.insertMoney(PRICE-5);
		//THEN
		assertFalse(machine.printTicket(), "pas assez d'argent, on ne doit pas imprimer le ticket");
	}

	@Test
		// S4 : on imprime le ticket si le montant inséré est suffisant
	void imprimeSiBalanceSuffisante() {
		//GIVEN: une machine virtuelle (initialisée dans beforeeach
		//WHEN on met assez d'argent
		//On met  assez d'argent
		machine.insertMoney(PRICE);
		//THEN
		assertTrue(machine.printTicket(), "il y a assez d'argent, on doit imprimer le ticket");
	}

	@Test
		// S5 : on decremente la balance quand on imprime un ticket
	void balanceDecrementee() {
		//GIVEN: une machine virtuelle (initialisée dans beforeeach
		//WHEN on imprime un ticket
		machine.insertMoney(PRICE);
		machine.insertMoney(10);
		machine.printTicket();
		//THEN
		assertEquals(10, machine.getBalance(), "on n'a pas decremente la balance");
	}

	@Test
		// S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	void miseAJourBalance() {
		//GIVEN: une machine virtuelle (initialisée dans beforeeach
		//WHEN on imprime un ticket
		machine.insertMoney(PRICE);
		machine.insertMoney(10);
		machine.insertMoney(PRICE);
		machine.printTicket();
		machine.printTicket();
		//THEN
		assertEquals(100, machine.getTotal(), "on n'a pas tout collecte");
	}

	@Test
		// S7 : refund()rendcorrectement la monnaie
	void rendMonnaie() {
		//GIVEN: une machine virtuelle (initialisée dans beforeeach
		//WHEN on imprime un ticket en inserant plus d'agent
		machine.insertMoney(PRICE);
		machine.insertMoney(10);
		machine.printTicket();
		//THEN
		assertEquals(10, machine.refund(), "on n'a pas tout rendu");
	}

	@Test
		// S9 : on ne peut pas insérer un montant négatif
	void montantNegatif() {
		//GIVEN: une machine virtuelle (initialisée dans beforeeach
		//WHEN on insere un montant negatif

		//THEN
		assertThrows(IllegalArgumentException.class,
				() -> { machine.insertMoney(0); },
				"Cet appel doit lever une exception");
	}

}
