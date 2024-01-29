package ec.edu.utpl.mad.ti.pintegrativa.stodo.data.dao;

import ec.edu.utpl.mad.ti.pintegrativa.stodo.domain.TODO;

import java.util.List;

public interface TODODao {
    void create(TODO todo);
    TODO read(Long id);
    void update(Long id, TODO todo);
    void delete(Long id);
    List<TODO> readAll();

    boolean validate(Long id);
}
