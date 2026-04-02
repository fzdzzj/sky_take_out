package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Api(tags = "套餐相关接口")
public class SetmealController {
@Autowired
private SetmealService setmealService;

/**
 * 新增套餐
 *
 * @param setmealDTO 套餐信息
 * @return 通用返回结果
 */
@PostMapping
@ApiOperation("新增套餐")
@CacheEvict(cacheNames = "setmealCache", key = "#setmealDTO.categoryId") // key: setmealCache::100
public Result save(@RequestBody SetmealDTO setmealDTO) {
    setmealService.saveWithDish(setmealDTO);
    return Result.success();
}

/**
 * 批量删除套餐
 *
 * @param ids 套餐ID集合
 * @return 通用返回结果
 */
@DeleteMapping
@ApiOperation("批量删除套餐")
@CacheEvict(cacheNames = "setmealCache", allEntries = true)
public Result delete(@RequestParam List<Long> ids) {
    setmealService.deleteBatch(ids);
    return Result.success();
}

/**
 * 修改套餐
 *
 * @param setmealDTO 套餐信息
 * @return 通用返回结果
 */
@PutMapping
@ApiOperation("修改套餐")
@CacheEvict(cacheNames = "setmealCache", allEntries = true)
public Result update(@RequestBody SetmealDTO setmealDTO) {
    setmealService.update(setmealDTO);
    return Result.success();
}

/**
 * 套餐起售 / 停售
 *
 * @param status 状态 1-起售 0-停售
 * @param id     套餐ID
 * @return 通用返回结果
 */
@PostMapping("/status/{status}")
@ApiOperation("套餐起售停售")
@CacheEvict(cacheNames = "setmealCache", allEntries = true)
public Result startOrStop(@PathVariable Integer status, Long id) {
    setmealService.startOrStop(status, id);
    return Result.success();
}}