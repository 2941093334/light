package com.lsj.controller;


import com.lsj.common.Result;
import com.lsj.entity.Lamp;
import com.lsj.service.LampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lamp")
public class LampController {

    @Autowired
    private LampService lampService;

    @PostMapping
    public Result<Lamp> addLamp(@RequestBody Lamp lamp) {
        Lamp addedLamp = lampService.addLamp(lamp);
        return Result.success(addedLamp);
    }

    @DeleteMapping
    public Result<Void> deleteLamps(@RequestBody List<Long> ids) {
        boolean success = lampService.deleteLamps(ids);
        if (success) {
            return Result.success();
        } else {
            return Result.error("删除失败");
        }
    }

    @PutMapping
    public Result<Void> updateLamp(@RequestBody Lamp lamp) {
        if (lamp.getId() == null) {
            return Result.error("ID不能为空");
        }
        boolean success = lampService.updateLamp(lamp);
        if (success) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }
    }

    @GetMapping("/{id}")
    public Result<Lamp> getLampById(@PathVariable Long id) {
        Lamp lamp = lampService.getLampById(id);
        if (lamp != null) {
            return Result.success(lamp);
        } else {
            return Result.error("灯具不存在");
        }
    }

    @GetMapping
    public Result<List<Lamp>> getAllLamps() {
        List<Lamp> lamps = lampService.getAllLamps();
        return Result.success(lamps);
    }

    @GetMapping("/search")
    public Result<List<Lamp>> searchLamps(@RequestParam(required = false) String brand,
                                          @RequestParam(required = false) String model) {
        List<Lamp> lamps = lampService.getLampsByCondition(brand, model);
        return Result.success(lamps);
    }
}
