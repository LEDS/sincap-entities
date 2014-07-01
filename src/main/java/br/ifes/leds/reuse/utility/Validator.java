package br.ifes.leds.reuse.utility;

public enum Validator {
    INSTANCE;

    private final String REGEX_CPF = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$"; // 000.000.000-00
    private final String REGEX_CNPJ = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$"; // 00.000.000/0001-00
    private final String REGEX_TELEFONE_RES = "^\\(\\d{2}\\)\\d{4}-\\d{4}$"; // (dd)1234-1234
    private final String REGEX_TELEFONE_CEL = "^\\(\\d{2}\\)\\d{5}-\\d{4}$"; // (dd)12345-1234
    private final String REGEX_NOME = "^.+$";

    public static Validator getInstance() {
        return INSTANCE;
    }

    public boolean validarCpf(String cpf) {
        return cpf.matches(REGEX_CPF) && validarAlgoritmoCpf(cpfStringToIntVet(cpf));
    }

    private int[] cpfStringToIntVet(String cpf) {
        String numerosCpf = cpf.replaceAll("\\.|-", "");
        int[] cpfVet = new int[11];

        for (int posicao = 0; posicao < numerosCpf.toCharArray().length; posicao++) {
            cpfVet[posicao] = numerosCpf.toCharArray()[posicao];
        }

        return cpfVet;
    }

    private boolean validarAlgoritmoCpf(int[] cpf) {
        int[] temp = new int[2];

        temp[0] = 1 * cpf[0] + 2 * cpf[1] + 3 * cpf[2];
        temp[0] += 4 * cpf[3] + 5 * cpf[4] + 6 * cpf[5];
        temp[0] += 7 * cpf[6] + 8 * cpf[7] + 9 * cpf[8];
        temp[0] = temp[0] % 11;
        temp[0] = temp[0] % 10;

        temp[1] = 1 * cpf[1] + 2 * cpf[2] + 3 * cpf[3];
        temp[1] += 4 * cpf[4] + 5 * cpf[5] + 6 * cpf[6];
        temp[1] += 7 * cpf[7] + 8 * cpf[8] + 9 * temp[0];
        temp[1] = temp[1] % 11;
        temp[1] = temp[1] % 10;

        return temp[0] == cpf[9] && temp[1] == cpf[10];
    }

    public boolean validarTelefone(String telefone) {
        return telefone.matches(REGEX_TELEFONE_CEL) || telefone.matches(REGEX_TELEFONE_RES);
    }

    public boolean validarCnpj(String cnpj) {
        return cnpj.matches(REGEX_CNPJ);
    }

    public boolean validarNome(String nome) {
        return nome.matches(REGEX_NOME);
    }

}
