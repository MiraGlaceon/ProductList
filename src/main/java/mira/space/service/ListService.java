package mira.space.service;

import mira.space.model.List;

import java.util.HashMap;

public interface ListService {
    List create(List list);
    HashMap<String, List> getKcalRatio();
}
