package com.happlay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happlay.entity.DishFlavor;
import com.happlay.mapper.DishFlavorMapper;
import com.happlay.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
