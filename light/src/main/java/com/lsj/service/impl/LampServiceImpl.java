package com.lsj.service.impl;


import com.lsj.entity.Lamp;
import com.lsj.mapper.LampMapper;
import com.lsj.service.LampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LampServiceImpl implements LampService {

    @Autowired
    private LampMapper lampMapper;

    @Override
    public Lamp addLamp(Lamp lamp) {
        lampMapper.insert(lamp);
        return lamp;
    }

    @Override
    public boolean deleteLamps(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        return lampMapper.deleteByIds(ids) > 0;
    }

    @Override
    public boolean updateLamp(Lamp lamp) {
        return lampMapper.update(lamp) > 0;
    }

    @Override
    public Lamp getLampById(Long id) {
        return lampMapper.selectById(id);
    }

    @Override
    public List<Lamp> getAllLamps() {
        return lampMapper.selectAll();
    }

    @Override
    public List<Lamp> getLampsByCondition(String brand, String model) {
        return lampMapper.selectByCondition(brand, model);
    }
}
