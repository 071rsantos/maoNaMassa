import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import entities.Funcionario;

public class Principal {
    public static void main(String[] args) {
        //inicialzando arraylist de funcionarios
        List<Funcionario> funcionarios = new ArrayList<>();

        //adicionando os funcionaros na lsta
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        //removendo o funcionaro "Joao", utlizando lambda
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        //Formatando Data e valor dos salarios
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat formatoValor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        //imprimindo todos os funcionarios com os formatos alterados.
        for (Funcionario funcionario : funcionarios) {
            String dataNascimento = funcionario.getDataNascimento().format(formatoData);
            String salario = formatoValor.format(funcionario.getSalario());
            System.out.println("Nome: " + funcionario.getNome() + ", Data de Nascimento: " + dataNascimento + ", Salário: " + salario + ", Função: " + funcionario.getFuncao());
        }

        //Separando para melhor organizaçao da impressao
        System.out.println("------------------------------------------------------------------------------");

        //aumentando em 10% o salario dos funconarios
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(novoSalario);
        }
    
        //mapeando funcionarios por funcao
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função: " + funcao);
            listaFuncionarios.forEach((s)-> System.out.println(s));
        });

        //Separando para melhor organizaçao da impressao
        System.out.println("------------------------------------------------------------------------------");

        //Fazendo lista de funcionarios que fazem aniversario em outubro ou dezembro
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 || funcionario.getDataNascimento().getMonthValue() == 12)
                .toList();
        System.out.println("Aniversariantes nos meses 10 e 12:");
        aniversariantes.forEach((s)-> System.out.println(s));

        //Separando para melhor organizaçao da impressao
        System.out.println("------------------------------------------------------------------------------");

        //Pegando o funcionario mais velho da empresa
        Funcionario maisAntigo = Collections.max(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        int idade = LocalDate.now().getYear() - maisAntigo.getDataNascimento().getYear();
        System.out.println("Funcionário com a maior idade: Nome: " + maisAntigo.getNome() + ", Idade: " + idade);

        //Separando para melhor organizaçao da impressao
        System.out.println("------------------------------------------------------------------------------");

        //Organzando lista de funcionarios em ordfem alfaberica
        List<Funcionario> funcionariosAlfabetica = funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());
        System.out.println("Ordem alfabética:");
        funcionariosAlfabetica.forEach((s)-> System.out.println(s));

        //Separando para melhor organizaçao da impressao
        System.out.println("------------------------------------------------------------------------------");

        //Calculando o valor total de salario que a empresa paga
        BigDecimal salaroTotal= funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: " + formatoValor.format(salaroTotal));

        System.out.println("------------------------------------------------------------------------------");

    }
}
