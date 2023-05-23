package brutepasta.front;

public class AppConta {
	public AppConta() {
		int opc;
		do {
			System.out.println("\n\n*** CONTA CORRENTE ***");
			System.out.println("1 - Criar conta");
			System.out.println("2 - Depositar");
			System.out.println("3 - Sacar");
			System.out.println("4 - Saldo");
			System.out.println("5 - Listar contas");
			System.out.println("6 - Excluir conta");
			System.out.println("7 - Voltar");
			opc = Console.readInt("Op��o: ");
			switch (opc) {
			case 1:
				// criarConta();
				break;
			case 2:
				// depositar();
				break;
			case 3:
				// sacar();
				break;
			case 4:
				// mostrarSaldo();
				break;
			case 5:
				// listarContas();
				break;
			case 6:
				// excluirConta();
				break;
			}
		} while (opc != 7);
	}
	/*
	 * private static void criarConta(){ Cliente cliente;
	 * System.out.println("\n\n*** CRIAR CONTA ***");
	 * System.out.println("Escolher o cliente por:");
	 * System.out.println("\t(1) Por Nome"); System.out.println("\t(2) Por CPF");
	 * System.out.println("\t(3) Por ID"); int opc = Console.readInt("\tOp��o: ");
	 * if(opc == 1){ String nome = Console.readString("Nome: "); cliente = new
	 * Cliente(); cliente.setNome(nome); cliente =
	 * ClienteDAO.procurarPorNome(cliente); } else{ if(opc == 2){ String cpf =
	 * Console.readString("CPF: "); cliente = new Cliente(); cliente.setCpf(cpf);
	 * cliente = ClienteDAO.procurarPorCPF(cliente); } else{ int id =
	 * Console.readInt("Id: "); cliente = new Cliente(); cliente.setId(id); cliente
	 * = ClienteDAO.procurarPorId(cliente); } } if(cliente != null){
	 * System.out.println("----------------------------"); System.out.println("ID: "
	 * + cliente.getId()); System.out.println("NOme: " + cliente.getNome());
	 * System.out.println("CPF: " + cliente.getCpf());
	 * System.out.println("----------------------------"); char resp =
	 * Console.readChar("Confirma o cliente selecionado? "); if(resp == 's' || resp
	 * == 'S'){ Conta conta = new Conta();
	 * conta.setAgencia(Console.readInt("Informe a ag�ncia: "));
	 * conta.setConta(Console.readInt("Informe a conta: "));
	 * if(ContaDAO.procurarConta(conta) == null){ conta.setCliente(cliente);
	 * conta.setSaldo(0); conta.setLimite(Console.readFloat("Informe o limite: "));
	 * if(ContaDAO.incluir(conta)){ System.out.println("Inclus�o bem sucedida."); }
	 * else{ System.out.println("Conta n�o cadastrada."); } } else
	 * System.out.println("Conta j� cadastrada."); } } else{
	 * System.out.println("Cliente n�o encontrado."); } } private static void
	 * depositar(){ System.out.println("\n\n*** DEPOSITAR ***"); Conta conta = new
	 * Conta(); conta.setAgencia(Console.readInt("Informe a ag�ncia: "));
	 * conta.setConta(Console.readInt("Informe a conta: ")); conta =
	 * ContaDAO.procurarConta(conta); if(conta != null){ float valor =
	 * Console.readFloat("Valor: "); ContaNegocio.depositar(conta, valor);
	 * if(ContaDAO.alterar(conta)){ System.out.println("Dep�sito realizado."); }
	 * else System.out.println("Dep�sito n�o realizado."); } else
	 * System.out.println("Ag�ncia e conta n�o cadastrados."); } private static void
	 * sacar(){ System.out.println("\n\n*** SACAR ***"); Conta conta = new Conta();
	 * conta.setAgencia(Console.readInt("Informe a ag�ncia: "));
	 * conta.setConta(Console.readInt("Informe a conta: ")); conta =
	 * ContaDAO.procurarConta(conta); if(conta != null){ float valor =
	 * Console.readFloat("Valor: "); if(ContaNegocio.sacar(conta, valor)){
	 * if(ContaDAO.alterar(conta)){ System.out.println("Saque realizado."); } else
	 * System.out.println("Saque n�o realizado."); } else
	 * System.out.println("Saldo indispon�vel."); } else
	 * System.out.println("Ag�ncia e conta n�o cadastrados."); } private static void
	 * mostrarSaldo(){ System.out.println("\n\n*** SALDO ***"); Conta conta = new
	 * Conta(); conta.setAgencia(Console.readInt("Informe a ag�ncia: "));
	 * conta.setConta(Console.readInt("Informe a conta: ")); conta =
	 * ContaDAO.procurarConta(conta); if(conta != null){
	 * System.out.println("----------------------------");
	 * System.out.println("Ag�ncia: " + conta.getAgencia());
	 * System.out.println("Conta: " + conta.getConta());
	 * System.out.println("Cliente: " + conta.getCliente().getNome());
	 * System.out.println("Saldo: " + conta.getSaldo());
	 * System.out.println("Limite: " + conta.getLimite()); float disponivel =
	 * conta.getSaldo() + conta.getLimite(); System.out.println("Dispon�vel: " +
	 * disponivel); System.out.println("----------------------------"); } else
	 * System.out.println("Conta n�o cadastrada."); } private static void
	 * listarContas(){ System.out.println("\n\n*** LISTAR CONTAS ***"); List<Conta>
	 * contas = ContaDAO.getContas(); if(!contas.isEmpty()){ for(Conta ct : contas){
	 * System.out.println("----------------------------");
	 * System.out.println("Ag�ncia: " + ct.getAgencia());
	 * System.out.println("Conta: " + ct.getConta()); System.out.println("Cliente: "
	 * + ct.getCliente().getNome()); } } else
	 * System.out.println("Nenhuma conta cadastrada."); } private static void
	 * excluirConta(){ System.out.println("\n\n*** EXCLUIR ***"); Conta conta = new
	 * Conta(); conta.setAgencia(Console.readInt("Informe a ag�ncia: "));
	 * conta.setConta(Console.readInt("Informe a conta: ")); conta =
	 * ContaDAO.procurarConta(conta); if(conta != null){
	 * System.out.println("----------------------------");
	 * System.out.println("Ag�ncia: " + conta.getAgencia());
	 * System.out.println("Conta: " + conta.getConta());
	 * System.out.println("Cliente: " + conta.getCliente().getNome());
	 * System.out.println("Saldo: " + conta.getSaldo());
	 * System.out.println("Limite: " + conta.getLimite()); float disponivel =
	 * conta.getSaldo() + conta.getLimite(); System.out.println("Dispon�vel: " +
	 * disponivel); System.out.println("----------------------------"); char op =
	 * Console.readChar("Deseja excluir a conta? "); if(op == 'S' || op == 's'){
	 * if(ContaDAO.excluir(conta)){ System.out.println("A conta foi exclu�da."); }
	 * else{ System.out.println("A conta n�o foi exclu�da."); } } } else
	 * System.out.println("Conta n�o cadastrada."); }
	 */
}
