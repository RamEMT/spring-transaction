package com.smart.transaction.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Emilia
 * @Since 2020.11.04 20:31
 */
public interface CarsMapper {
    /**
     * @param userId
     * @param ids
     */
    List<Integer> updateCars(@Param("userId") Integer userId, @Param("ids") List<Integer> ids);
}
