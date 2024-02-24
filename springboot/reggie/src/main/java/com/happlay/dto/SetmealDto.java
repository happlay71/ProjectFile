package com.happlay.dto;

import com.happlay.entity.Setmeal;
import com.happlay.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
