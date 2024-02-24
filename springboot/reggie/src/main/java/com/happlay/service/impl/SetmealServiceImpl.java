package com.happlay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happlay.common.CustomException;
import com.happlay.dto.SetmealDto;
import com.happlay.entity.Setmeal;
import com.happlay.entity.SetmealDish;
import com.happlay.mapper.SetmealMapper;
import com.happlay.service.SetmealDishService;
import com.happlay.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    public SetmealDishService setmealDishService;

    @Transactional  // 保持事物的一致性
    public void saveWithDish(SetmealDto setmealDto) {
        // setmeal 保存套餐的基本信息，执行insert
        this.save(setmealDto);

        // setmeal_dish 保存套餐和菜品的关联信息，执行insert
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐，同时删除套餐和菜品的关联数据
     * @param ids
     */
    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        // 查询套餐状态，确定是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);

        // 调用接口方法
        int count = this.count(queryWrapper);
        if (count > 0) {
            // 若不能删除，抛出异常
            throw new CustomException("套餐正在售卖中，不能删除");
        }

        // 若可以删除，先删除套餐表中的数据-setmeal
        this.removeByIds(ids);

        // 删除关系表中的数据
        LambdaQueryWrapper<SetmealDish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(dishLambdaQueryWrapper);
    }
}
