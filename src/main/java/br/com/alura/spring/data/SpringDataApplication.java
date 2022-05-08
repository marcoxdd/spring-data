package br.com.alura.spring.data;

import br.com.alura.spring.data.services.CrudCargoService;
import br.com.alura.spring.data.services.CrudFuncionarioService;
import br.com.alura.spring.data.services.CrudRelatorioService;
import br.com.alura.spring.data.services.CrudUnidadeDeTrabalhoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService crudCargoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final CrudUnidadeDeTrabalhoService crudUnidadeDeTrabalhoService;
	private final CrudRelatorioService crudRelatorioService;

	public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService crudFuncionarioService, CrudUnidadeDeTrabalhoService crudUnidadeDeTrabalhoService, CrudRelatorioService crudRelatorioService) {
		this.crudCargoService = cargoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.crudUnidadeDeTrabalhoService = crudUnidadeDeTrabalhoService;
		this.crudRelatorioService = crudRelatorioService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);

		while (true){
			System.out.println("Qual opção deseja executar? ");
			System.out.println("0 - Sair");
			System.out.println("1 - Ir para Cargos");
			System.out.println("2 - Ir para Unidades de trabalho");
			System.out.println("3 - Ir para Funcionarios");
			System.out.println("4 - Relatorios");

			int acao = sc.nextInt();

			if (acao == 0) break;

			switch (acao){
				case 1:
					crudCargoService.inicial(sc);
					break;
				case 2:
					crudUnidadeDeTrabalhoService.inicial(sc);
					break;
				case 3:
					crudFuncionarioService.inicial(sc);
					break;
				case 4:
					crudRelatorioService.inicial(sc);
					break;

			}


		}
	}
}


