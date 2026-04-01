package com.sky.controller.user;

import com.sky.entity.Category;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/category")
@Api(tags="用户端分类接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public Result<List<Category>>list(Integer type){
        List<Category>list=categoryService.list(type);
        return Result.success(list);
    }
}
