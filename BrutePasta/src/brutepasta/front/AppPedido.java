package brutepasta.front;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import brutepasta.entidades.*;
import brutepasta.negocio.ClienteNegocio;
import brutepasta.persistencia.ClientePersistencia;
import brutepasta.persistencia.ProdutoPersistencia;

public class AppPedido {
    public AppPedido() {
        int opc;

        do {
            System.out.println("\n\n");
            System.out.println("====== PEDIDOS ======");
            System.out.println("1 - Realizar pedido");
            System.out.println("2 - Exibir cardápio");
            System.out.println("3 - Voltar");
            opc = Console.readInt("Opção: ");
            switch (opc) {
                case 1:
                    realizarPedido();
                    break;
                case 2:
                    new AppCardapio();
            }
        } while (opc != 3);
    }

    public static void realizarPedido() {
        int opc;
        Pedido objPedido = new Pedido();
        Cliente objCliente = new Cliente();
        Carrinho objCarrinho = new Carrinho();
        Produto objProduto = new Produto();
        Item objItem = new Item();
        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
        String dataString;
        Date dataFormatada = null;

        objCliente.setCpf(Console.readString("Informe o CPF do cliente: "));
        if (ClienteNegocio.isCPF(objCliente.getCpf())) {
            objCliente = ClientePersistencia.procurarPorCPF(objCliente);
            if (objCliente != null) {
                objPedido.setCliente(objCliente);
                boolean dataValida = false;
                do {
                    dataString = Console.readString("Informe a data da venda: ");
                    try {
                        dataFormatada = (Date) formato.parse(dataString);
                        dataValida = true;
                        objPedido.setDataPedido(dataFormatada);
                    } catch (ParseException e) {
                        System.out.println("Data inválida. Informe uma data válida.");
                    }
                } while (dataValida == false);

                do {
                    opc = 1;
                } while (opc != 0);
            }
        }
    }
}