package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.vo.DishItemVO;

import java.util.List;

public interface SetmealService {

    List<Setmeal>list(Setmeal setmeal);

    List<DishItemVO>getDishItemById(Long id);

    void update(SetmealDTO setmealDTO);

    void deleteBatch(List<Long> ids);

    void startOrStop(Integer status, Long id);

    void saveWithDish(SetmealDTO setmealDTO);
}
