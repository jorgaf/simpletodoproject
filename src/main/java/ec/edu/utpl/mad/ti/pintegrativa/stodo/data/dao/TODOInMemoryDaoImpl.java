package ec.edu.utpl.mad.ti.pintegrativa.stodo.data.dao;

import ec.edu.utpl.mad.ti.pintegrativa.stodo.domain.TODO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class TODOInMemoryDaoImpl implements TODODao {

    private ConcurrentHashMap<Long, TODO> inMemoryDB;
    private static final AtomicLong longAutoInc = new AtomicLong(0);

    public TODOInMemoryDaoImpl() {
        inMemoryDB = new ConcurrentHashMap<>();
        inMemoryDB.put(longAutoInc.incrementAndGet(), new TODO("Enviar tarea de programación integrativa"));
    }

    @Override
    public void create(TODO todo) {
        inMemoryDB.put(longAutoInc.incrementAndGet(), todo);
    }

    @Override
    public TODO read(Long id) {
        return inMemoryDB.get(id);
    }

    @Override
    public void update(Long id, TODO todo) {
        inMemoryDB.replace(id, todo);
    }

    @Override
    public void delete(Long id) {
        inMemoryDB.remove(id);

    }

    @Override
    public List<TODO> readAll() {
        return inMemoryDB.values().stream().toList();
    }

    @Override
    public boolean validate(Long id) {
        return inMemoryDB.containsKey(id);
    }
}
