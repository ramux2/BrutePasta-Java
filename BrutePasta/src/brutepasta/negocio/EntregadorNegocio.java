package brutepasta.negocio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntregadorNegocio {
    public static boolean validaPlaca(String placaVeiculo){
        // Expressão regular para validar a placa
        String padrao = "^[A-Z]{3}[0-9]{4}$";

        // Cria um objeto Pattern com o padrão
        Pattern pattern = Pattern.compile(padrao);

        // Cria um objeto Matcher com a placa e o padrão
        Matcher matcher = pattern.matcher(placaVeiculo);

        // Verifica se a placa corresponde ao padrão
        return matcher.matches();
    }
}
