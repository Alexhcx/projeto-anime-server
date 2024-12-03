// package com.clienteservidor.animeserver.animeserver.DAO;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.bean.override.mockito.MockitoBean;

// import com.clienteservidor.animeserver.animeserver.dao.userdao.UserDAOImpl;
// import com.clienteservidor.animeserver.animeserver.models.UserModel;

// import jakarta.persistence.EntityManager;
// import jakarta.persistence.TypedQuery;
// import jakarta.transaction.Transactional;

// @SpringBootTest
// @ExtendWith(MockitoExtension.class)
// public class UsersDAOImplTest {

//   @InjectMocks
//   private UserDAOImpl userDAOImpl;

//   @MockitoBean
//   private EntityManager entityManager;

//   @Test
//   @Transactional
//   public void testSave() {

//     UserModel user = new UserModel();
//     user.setNome("Teste");
//     user.setCpf("12345678901");
//     user.setSexo("M");
//     user.setEmail("test@test.com");
//     user.setPassword("123456");
//     user.setDataNascimento("01/01/2000");
//     user.setTelefone("123456789");

//     doNothing().when(entityManager).persist(any(UserModel.class));

//     UserModel savedUser = userDAOImpl.save(user);

//     assertEquals(user, savedUser);
//     verify(entityManager).persist(user);
//   }

//   @Test
//   @Transactional
//   public void testFindById() {
//     Long userId = 1L;
//     UserModel user = new UserModel();
//     user.setId(userId);
//     user.setNome("Teste");
//     user.setCpf("12345678901");
//     user.setSexo("M");
//     user.setEmail("test@test.com");
//     user.setPassword("123456");
//     user.setDataNascimento("01/01/2000");
//     user.setTelefone("123456789");

//     when(entityManager.find(UserModel.class, userId)).thenReturn(user);

//     Optional<UserModel> foundUser = userDAOImpl.findById(userId);

//     assertTrue(foundUser.isPresent());
//     assertEquals(user, foundUser.get());
//     verify(entityManager).find(UserModel.class, userId);
//   }

//   @Test
//   @Transactional
//   public void testFindByName() {
//     String name = "Teste";
//     UserModel user = new UserModel();
//     user.setNome("Teste");
//     user.setCpf("12345678901");
//     user.setSexo("M");
//     user.setEmail("test@test.com");
//     user.setPassword("123456");
//     user.setDataNascimento("01/01/2000");
//     user.setTelefone("123456789");

//     when(entityManager.createQuery("SELECT u FROM UserModel u WHERE u.name = :name", UserModel.class))
//         .thenReturn(mock(TypedQuery.class));
//     when(entityManager.createQuery("SELECT u FROM UserModel u WHERE u.name = :name", UserModel.class)
//         .setParameter("name", name)
//         .getResultStream()
//         .findFirst())
//         .thenReturn(Optional.of(user));

//     Optional<UserModel> foundUser = userDAOImpl.findByName(name);

//     assertTrue(foundUser.isPresent());
//     assertEquals(user, foundUser.get());
//     verify(entityManager).createQuery("SELECT u FROM UserModel u WHERE u.name = :name", UserModel.class);
//   }

// }
