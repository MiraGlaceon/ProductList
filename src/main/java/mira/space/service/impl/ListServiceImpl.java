package mira.space.service.impl;

import mira.space.model.List;
import mira.space.model.Product;
import mira.space.model.repository.ListRepository;
import mira.space.service.ListService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

@Service
public class ListServiceImpl implements ListService {

    @Autowired
    ListRepository listRepository;

    @Override
    public List create(List list) {
        if (list != null
                && list.getId() != null
                && Strings.isNotBlank(list.getName())) {
            List newList = new List();
            newList.setId(list.getId());
            newList.setName(list.getName());
            newList.setProducts(new HashSet<>());
            return newList;
        }
        return null;
    }

    @Override
    public HashMap<String, List> getKcalRatio() {
        java.util.List<List> lists = listRepository.findAll();
        if (lists.isEmpty()) {
            return null;
        }

        HashMap<String, List> kcalRatio = new HashMap<>();
        for (List list : lists) {
            int kcalCount = list.getProducts().stream().mapToInt(Product::getKcal).sum();
            kcalRatio.put("Kcal for list №" + list.getId() + ": " + kcalCount, list);
        }
        return kcalRatio;
    }
}
