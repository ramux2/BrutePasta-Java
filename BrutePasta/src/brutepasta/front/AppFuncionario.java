package brutepasta.front;

import brutepasta.entidades.TipoProduto;
import brutepasta.entidades.Produto;
import brutepasta.persistencia.ProdutoPersistencia;
import brutepasta.persistencia.TipoProdutoPersistencia;

public class AppFuncionario {
	public AppFuncionario() {
		int opc;
		do {
			System.out.println("\n\n====== FUNCIONÁRIO ======");
			System.out.println("1 - Produto");
			System.out.println("2 - Tipo produto");
			System.out.println("3 - Voltar");
			opc = Console.readInt("Opção: ");
			switch (opc) {
				case 1:
					new AppProduto();
					break;
				case 2:
					new AppTipoProduto();
					break;
			}
		} while (opc != 3);
	}
}
