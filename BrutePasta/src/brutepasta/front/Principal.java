package brutepasta.front;

public class Principal {
	public static void main(String[] args) {
		int opc;
		do {
			System.out.println("\n\n");
			System.out.println("*** MENU PRINCIPAL ***");
			System.out.println("1 - Clientes");
			System.out.println("2 - Conta corrente");
			System.out.println("3 - Sair");
			opc = Console.readInt("Opção: ");
			switch (opc) {
			case 1:
				new AppClientes();
				break;
			case 2:
				new AppConta();
				break;
			}
		} while (opc != 3);
	}
}
