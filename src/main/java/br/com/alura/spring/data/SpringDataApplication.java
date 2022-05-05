package br.com.alura.spring.data;

import br.com.alura.spring.data.services.CrudCargoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;

	public SpringDataApplication(CrudCargoService cargoService) {
		this.cargoService = cargoService;
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
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Remover");

			Integer acao = sc.nextInt();

			if (acao != 0){
				System.out.println("Informe a descrição do cargo: ");
				cargoService.inicial(sc, acao);
			}else{
				break;
			}


		}
	}
}


