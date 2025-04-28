package projeto.arenaFuria;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArenaFuriaApplication {
	public static void main(String[] args) {
		// Carrega o .env
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing() // Não dá erro se .env não existir
				.load();

		// Coloca cada variável do .env como propriedade do sistema
		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
		});

		// Agora inicia a aplicação Spring Boot
		SpringApplication.run(ArenaFuriaApplication.class, args);
	}
}
