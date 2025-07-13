package br.com.fiap.tech.challenge.erp_restaurant.application.interactor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.tech.challenge.erp_restaurant.application.exception.AuthenticationException;
import br.com.fiap.tech.challenge.erp_restaurant.application.gateway.UserGateway;
import br.com.fiap.tech.challenge.erp_restaurant.domain.user.User;

@ExtendWith(MockitoExtension.class)
class AuthInteractorTest {

	@Mock
	private UserGateway userGateway;

	@InjectMocks
	private AuthInteractor authInteractor;

	private User mockUserRequest;
	private User mockFoundUser;

	@BeforeEach
	void setUp() {
		mockUserRequest = User.builder().name("test").email("test@test.com").login("teste-user").password("123")
				.build();
		mockFoundUser = User.builder().id(1l).name("test").email("test@test.com").login("teste-user").password("123")
				.build();
	}

	@Test
	@DisplayName("Deve autenticar o usuário com sucesso quando credenciais corretas")
	void shouldAuthenticateUserSuccessfullyWhenCredentialsAreCorrect() {

		when(userGateway.findByEmailOrLogin(mockUserRequest.getEmail(), mockUserRequest.getLogin()))
				.thenReturn(mockFoundUser);

		User authenticatedUser = authInteractor.authenticate(mockUserRequest);

		assertNotNull(authenticatedUser);
		assertEquals(mockFoundUser.getLogin(), authenticatedUser.getLogin());
		assertEquals(mockFoundUser.getEmail(), authenticatedUser.getEmail());
		verify(userGateway, times(1)).findByEmailOrLogin(mockUserRequest.getEmail(), mockUserRequest.getLogin());
	}

	@Test
	@DisplayName("Deve lançar AuthenticationException quando a senha estiver incorreta")
	void shouldThrowAuthenticationExceptionWhenPasswordIsIncorrect() {
		User userWithDifferentPassword = User.builder().name("wrong").email("test@wrong.com").login("wrong-user")
				.password("321").build();
		when(userGateway.findByEmailOrLogin(mockUserRequest.getEmail(), mockUserRequest.getLogin()))
				.thenReturn(userWithDifferentPassword);

		AuthenticationException exception = assertThrows(AuthenticationException.class, () -> {
			authInteractor.authenticate(mockUserRequest);
		});

		assertEquals("not authorized to enter here", exception.getMessage());
		verify(userGateway, times(1)).findByEmailOrLogin(mockUserRequest.getEmail(), mockUserRequest.getLogin());
	}

}
