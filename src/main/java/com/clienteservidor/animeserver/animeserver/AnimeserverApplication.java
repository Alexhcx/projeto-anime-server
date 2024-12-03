package com.clienteservidor.animeserver.animeserver;

import org.springframework.boot.SpringApplication;

// import java.util.Optional;

// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// import com.clienteservidor.animeserver.animeserver.dao.userdao.UserDAOImpl;
// import com.clienteservidor.animeserver.animeserver.models.UserModel;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableJpaAuditing
public class AnimeserverApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AnimeserverApplication.class, args);

		// UserDAOImpl userDAOImpl = context.getBean(UserDAOImpl.class);

		// UserModel user = new UserModel();
		// user.setNome("Teste");
		// user.setCpf("12345678901");
		// user.setSexo("M");
		// user.setEmail("test@test.com");
		// user.setPassword("123456");
		// user.setDataNascimento("01/01/2000");
		// user.setTelefone("123456789");

		// userDAOImpl.save(user);

		// userDAOImpl.findById(3L).ifPresent(System.out::println);

		// userDAOImpl.findByName("Teste").ifPresent(System.out::println);

		// userDAOImpl.findByEmail("test@test.com").ifPresent(System.out::println);

		// userDAOImpl.findByCPF("12345678901").ifPresent(System.out::println);

		// userDAOImpl.findAll().forEach(System.out::println);

		/*
		 * // 1. Buscar o usuário que deseja atualizar
		 * Optional<UserModel> optionalUser = userDAOImpl.findById(3L); // Supondo que o
		 * ID do usuário seja 1
		 * 
		 * if (optionalUser.isPresent()) {
		 * user = optionalUser.get();
		 * 
		 * // 2. Modificar os campos desejados
		 * user.setNome("Nome Atualizado");
		 * user.setEmail("novoemail@example.com");
		 * user.setTelefone("987654321");
		 * 
		 * // 3. Chamar o método update
		 * UserModel updatedUser = userDAOImpl.update(user);
		 * 
		 * System.out.println("Usuário atualizado: " + updatedUser);
		 * } else {
		 * System.out.println("Usuário não encontrado para o ID fornecido.");
		 * }
		 * 
		 */

		// userDAOImpl.deleteById(3L);
	}
}
