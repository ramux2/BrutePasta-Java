package brutepasta.front;

public class Principal {
	public static void main(String[] args) {
		int opc;
		do {
			System.out.println("\n\n");
			System.out.println("====== BRUTE PASTA ====== ");
			System.out.println("1 - Clientes");
			System.out.println("2 - Funcionário");
			System.out.println("3 - Entregador");
			System.out.println("4 - Pedido");
			System.out.println("5 - Sair");
			opc = Console.readInt("Opção: ");
			switch (opc) {
				case 1:
					new AppClientes();
					break;
				case 2:
					new AppFuncionario();
					break;
				case 3:
					new AppEntregador();
					break;
				case 4:
					new AppPedido();
					break;
			}
		} while (opc != 5);
	}	
}

