package database.repository;

import database.MisakaDatabase;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MdbRepositoryTest {

    private MisakaRepository<Integer,User> misakaRepository;

    @BeforeEach
    public void prepare(){
        this.misakaRepository = new MdbRepository<>(User.class);
        this.misakaRepository.deleteAll();
    }

    @Test
    public void test_insert(){
        MisakaRepository<Integer,User> repository = new MdbRepository<>(User.class);
        User user = this.getUser(1,"user");
        repository.insert(user.getId(),user);
        assertEquals(1,repository.findAll().size());
        assertEquals(1,repository.findById(1).get().getId());
    }
    @Test
    public void test_insertAll(){
        MisakaRepository<Integer,User> repository = new MdbRepository<>(User.class);
        repository.insertMap(this.getSampleMap());
        assertEquals(3,repository.findAll().size());
        assertEquals(1,repository.findById(1).get().getId());
        assertEquals(2,repository.findById(2).get().getId());
        assertEquals(3,repository.findById(3).get().getId());
    }
    @Test
    public void test_update(){
        MisakaDatabase database = MisakaDatabase.getDatabase();
        database.setupTableMap(User.class,this.getSampleMap());

        MisakaRepository<Integer,User> repository = new MdbRepository<>(User.class);
        User user = repository.findById(1).get();
        assertFalse(user.getDeleteFlag());

        User newUser = new User(user.getId(),user.getName());
        newUser.setDelete();
        repository.update(newUser.getId(),newUser);

        user = repository.findById(1).get();
        assertTrue(user.getDeleteFlag());
    }

    @Test
    public void test_failure_update(){
        User user = new User(1,"master");
        boolean result = this.misakaRepository.update(user.getId(),user);
        assertEquals(false, result);

        Optional<User> opt = this.misakaRepository.findById(user.getId());
        assertEquals(false, opt.isPresent());
    }

    @Test
    public void test_update_all(){
        MisakaRepository<Integer,User> repository = new MdbRepository<>(User.class);
        repository.insertMap(this.getSampleMap());
        List<User> users = repository.findAll();

        List<User> newUsers = users
                .stream()
                .map(i -> new User(i.getId(),i.getName()))
                .collect(Collectors.toList());

        newUsers.get(0).setDelete();
        newUsers.get(1).setDelete();
        newUsers.get(2).setDelete();

        repository.updateMap(this.toMap(newUsers));

        List<User> updateUsers = repository.findAll();

        assertTrue(updateUsers.get(0).getDeleteFlag());
        assertTrue(updateUsers.get(1).getDeleteFlag());
        assertTrue(updateUsers.get(2).getDeleteFlag());
    }

    @Test
    public void test_add_initrecord(){
        MisakaDatabase database = MisakaDatabase.getDatabase();
        database.setupTableMap(User.class,this.getSampleMap());

        Map<Integer,User> map = Map.of(4,new User(4,"boss"));
        database.setupTableMap(User.class,map);

        User user = this.misakaRepository.findById(1).get();
        assertEquals(1,user.getId());

        MisakaRepository<Integer,User> newRepository = new MdbRepository<>(User.class);
        User user2 = newRepository.findById(1).get();
        assertEquals(1,user2.getId());
    }

    @Test
    public void test_delete(){
        MisakaRepository<Integer,User> repository = new MdbRepository<>(User.class);

        repository.insertMap(this.getSampleMap());
        Optional<User> opt = repository.findById(2);
        assertTrue(opt.isPresent());

        repository.deleteById(2);
        opt = repository.findById(2);
        assertFalse(opt.isPresent());
    }

    private User getUser(int id, String name){
        return new User(id,name);
    }

    private Map<Integer,User> toMap(List<User> list){
        return list.stream().collect(Collectors.toMap(User::getId, i -> i));
    }

    private Map<Integer,User> getSampleMap(){
        return this.toMap(List.of(this.getUser(1,"user"),
                this.getUser(2,"admin"),
                this.getUser(3,"tenant")));
    }

}