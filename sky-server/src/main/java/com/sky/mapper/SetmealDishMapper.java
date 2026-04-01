package com.sky.mapper;

import com.sky.vo.DishItemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SetmealDishMapper {
    List<DishItemVO> getDishItemBySetmealId(Long id);
}
