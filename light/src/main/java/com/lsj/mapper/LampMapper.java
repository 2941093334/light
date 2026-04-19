package com.lsj.mapper;

import com.lsj.entity.Lamp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LampMapper {

    @Insert("INSERT INTO lamp(brand, model, power, color_temperature) VALUES(#{brand}, #{model}, #{power}, #{colorTemperature})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Lamp lamp);

    @Delete("<script>DELETE FROM lamp WHERE id IN <foreach collection='ids' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int deleteByIds(@Param("ids") List<Long> ids);

    @Update("<script>UPDATE lamp<set><if test='brand != null'>brand=#{brand},</if><if test='model != null'>model=#{model},</if><if test='power != null'>power=#{power},</if><if test='colorTemperature != null'>color_temperature=#{colorTemperature},</if></set>WHERE id=#{id}</script>")
    int update(Lamp lamp);

    @Select("SELECT * FROM lamp WHERE id = #{id}")
    Lamp selectById(Long id);

    @Select("SELECT * FROM lamp")
    List<Lamp> selectAll();

    @Select("<script>SELECT * FROM lamp<where><if test='brand != null and brand != \"\"'>AND brand=#{brand}</if><if test='model != null and model != \"\"'>AND model=#{model}</if></where></script>")
    List<Lamp> selectByCondition(@Param("brand") String brand, @Param("model") String model);
}
