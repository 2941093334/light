package com.lsj.service;

import com.lsj.entity.Lamp;

import java.util.List;

public interface LampService {

    Lamp addLamp(Lamp lamp);

    boolean deleteLamps(List<Long> ids);

    boolean updateLamp(Lamp lamp);

    Lamp getLampById(Long id);

    List<Lamp> getAllLamps();

    List<Lamp> getLampsByCondition(String brand, String model);
}
