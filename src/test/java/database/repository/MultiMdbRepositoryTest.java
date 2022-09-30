package database.repository;

import model.Lot;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiMdbRepositoryTest {

    private MisakaRepository<Integer, User> misakaRepository;

    private MisakaRepository<String, Lot> lotRepository;

    @BeforeEach
    public void prepare(){
        this.misakaRepository = new MdbRepository<>(User.class);
        this.misakaRepository.deleteAll();

        this.lotRepository = new MdbRepository<>(Lot.class);
        this.lotRepository.deleteAll();
    }

    @Test
    public void test_use_multi_repository(){
        this.misakaRepository.insert(1, new User(1,"admin"));
        this.lotRepository.insert("MDB20001", new Lot("MDB20001"));

        User user = this.misakaRepository.findById(1).get();
        Lot lot = this.lotRepository.findById("MDB20001").get();

        assertEquals(1, user.getId());
        assertEquals("MDB20001", lot.getLotNo());
    }

    @Test
    public void test_use_local_multi_repository(){
        MisakaRepository<Integer, User> misakaRepository = new MdbRepository<>(User.class);
        MisakaRepository<String, Lot> lotRepository = new MdbRepository<>(Lot.class);

        misakaRepository.insert(1, new User(1,"admin"));
        lotRepository.insert("MDB20001", new Lot("MDB20001"));

        User user = misakaRepository.findById(1).get();
        Lot lot = lotRepository.findById("MDB20001").get();

        assertEquals(1, user.getId());
        assertEquals("MDB20001", lot.getLotNo());
    }


}