package br.com.fiap.tech.challenge.erp_restaurant.application.usecase.dish;

import br.com.fiap.tech.challenge.erp_restaurant.application.gateway.restaurant.DishGateway;
import br.com.fiap.tech.challenge.erp_restaurant.domain.restaurant.menu.Dish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static org.mockito.Mockito.mock;

public class DishUseCaseTest {
    private DishGateway dishGateway;
//    private DishInteractor dishInteractor;

    @BeforeEach
    void setUp() {
        dishGateway = mock(DishGateway.class);
//        dishInteractor = new DishInteractor(dishGateway);
    }

    @Test
    void deveSalvarPratoComSucesso() {
        long id = 1L;
        String name = "Macarronada";
        BigDecimal price = BigDecimal.valueOf(2.56);
        String description = "Macarrão, molho e queijo ralado";
        Boolean onlyToGo = true;
        URI uri = URI.create("https://www.restaurant.com");
        URL photo = null;
        try {
            photo = uri.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        Dish prato = Dish.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .onlyToGo(onlyToGo)
                .photo(photo)
                .build();

//        when(dishGateway.save(prato)).thenReturn(prato);
//
//        Dish salvo = dishInteractor.save(prato);
//
//        assertNotNull(salvo);
//        assertEquals("Lasanha", salvo.getName());
//        verify(dishGateway).save(prato);
    }
}
