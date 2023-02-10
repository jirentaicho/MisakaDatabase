package database;

import database.repository.MdbRepository;
import database.repository.MisakaRepository;
import model.Toaru;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MisakaDatabaseTest {

    MisakaDatabase database;

    MisakaRepository<Integer, Toaru> misakaSimpleRepository;

    @BeforeEach
    public void setup(){
        // setup database
        this.database = MisakaDatabase.getDatabase();

        Map<Integer, Toaru> map = new HashMap<>();
        map.put(1,new Toaru(1,"Misaka Mikoto"));
        map.put(2,new Toaru(2,"Shokuho Misaki"));
        map.put(3,new Toaru(3,"Sirai Kuroko"));
        database.setupTableMap(Toaru.class, map);

        // setup repository
        misakaSimpleRepository = new MdbRepository<>(Toaru.class);
    }

    @Test
    public void test_findUser(){
        Toaru sirai = this.misakaSimpleRepository.findById(3).get();
        assertEquals("Sirai Kuroko",sirai.name);
    }

    @Test
    public void test_findAllUser(){
        List<Toaru> users = this.misakaSimpleRepository.findAll();
        assertEquals(3, users.size());
    }

    @Test
    public void test_insertUser(){
        Toaru saten = new Toaru(4, "Saten Ruiko");
        this.misakaSimpleRepository.insert(saten.id, saten);
        Toaru findSaten = this.misakaSimpleRepository.findById(4).get();
        assertEquals(4, findSaten.id);
        assertEquals("Saten Ruiko" , findSaten.name);
        this.misakaSimpleRepository.deleteById(4);
    }

    @Test
    public void test_updateUser(){
        Toaru misaka = this.misakaSimpleRepository.findById(1).get();
        misaka.name = "Bili Bili";
        // この段階では反映されていない
        Toaru updatedmisaka = this.misakaSimpleRepository.findById(1).get();
        assertNotEquals("Bili Bili" , updatedmisaka.name);
        // この段階で反映される
        this.misakaSimpleRepository.update(misaka.id, misaka);
        updatedmisaka = this.misakaSimpleRepository.findById(1).get();
        assertEquals("Bili Bili" , updatedmisaka.name);
    }

}