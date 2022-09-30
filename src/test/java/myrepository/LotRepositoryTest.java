package myrepository;

import model.Lot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LotRepositoryTest {

    @BeforeEach
    public void prepare(){
        LotRepository<String, Lot> repository = new LotRepository<>(Lot.class);
        List<Lot> list = List.of(
                new Lot("MDB1"),
                new Lot("MDB2"),
                new Lot("MDB3"),
                new Lot("MDB4"),
                new Lot("MDB5"),
                new Lot("MDB6"),
                new Lot("MDB7"),
                new Lot("MDB8"),
                new Lot("MDB9")
        );
        Map<String,Lot> lotMap = list.stream().collect(Collectors.toMap(Lot::getLotNo, i -> i));
        repository.insertMap(lotMap);
    }

    @Test
    public void test_where_logic(){
        LotRepository<String, Lot> repository = new LotRepository<>(Lot.class);
        Set<String> searchList = Set.of(
          "MDB1","MDB5","MDB7"
        );
        List<Lot> lots = repository.findLots(searchList);
        assertEquals(3,lots.size());
    }
}