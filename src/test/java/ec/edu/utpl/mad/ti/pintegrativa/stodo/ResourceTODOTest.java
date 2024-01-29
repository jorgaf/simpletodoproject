package ec.edu.utpl.mad.ti.pintegrativa.stodo;

import ec.edu.utpl.mad.ti.pintegrativa.stodo.domain.TODO;
import io.helidon.metrics.api.MetricsFactory;
import io.helidon.microprofile.testing.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@HelidonTest
public class ResourceTODOTest {
    @Inject
    private MetricRegistry registry;

    @Inject
    private WebTarget target;


    @BeforeAll
    static void clear() {
        MetricsFactory.closeAll();
    }

    @Test
    void testGetById() {
        TODO todoObject = target.path("todo/1")
                .request()
                .get(TODO.class);

        assertThat(todoObject.getDescription(), is("Enviar tarea de programación integrativa"));
    }

    @Test
    void testHealth() {
        Response response = target
                .path("health")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    void testAll() {
        Response response = target
                .path("todo/all")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    void testCreatFailed() {
        Entity<TODO> newTodo = Entity.entity(new TODO(), MediaType.APPLICATION_JSON);
        Response response = target
                .path("todo")
                .request()
                .post(newTodo);
        assertThat(response.getStatus(), is(400));
    }
}
