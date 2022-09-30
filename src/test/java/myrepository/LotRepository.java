package myrepository;

import database.repository.MdbRepository;
import model.Lot;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LotRepository<I,T> extends MdbRepository<I,T> {

    public LotRepository(Class<T> clazz) {
        super(clazz);
    }

    public List<Lot> findLots(Set<String> lotNoList){
        Map<I,T> tableMap = this.table.getMap();
        return tableMap.values()
                .stream()
                .map(i -> (Lot)i)
                .filter(i -> lotNoList.contains(i.getLotNo()))
                .collect(Collectors.toList());
    }

}
