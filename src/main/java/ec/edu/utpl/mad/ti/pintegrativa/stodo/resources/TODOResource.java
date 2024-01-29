package ec.edu.utpl.mad.ti.pintegrativa.stodo.resources;

import ec.edu.utpl.mad.ti.pintegrativa.stodo.data.dao.TODOInMemoryDaoImpl;
import ec.edu.utpl.mad.ti.pintegrativa.stodo.domain.TODO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Objects;

@Path("/todo")
public class TODOResource {

    //private static TODOInMemoryDaoImpl todoDB = new TODOInMemoryDaoImpl();
    @Inject
    private TODOInMemoryDaoImpl todoDB;

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TODO> getAll() {
        return todoDB.readAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        if(todoDB.validate(id)) {
            return Response
                    .status(Response.Status.OK)
                    .entity(todoDB.read(id))
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") Long id) {
        if(todoDB.validate(id)) {
            todoDB.delete(id);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(TODO newTodo) {
        if(Objects.nonNull(newTodo.getDescription())) {
            todoDB.create(newTodo);
            return Response
                    .status(Response.Status.CREATED)
                    .build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();

    }

   @Path("/{id}")
   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, TODO newTodo) {
        if(todoDB.validate(id)) {
            todoDB.update(id, newTodo);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } else {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }


}
