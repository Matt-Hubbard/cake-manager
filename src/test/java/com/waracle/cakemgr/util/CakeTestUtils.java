package com.waracle.cakemgr.util;

import com.waracle.cakemgr.dto.CakeDto;
import com.waracle.cakemgr.entity.CakeEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CakeTestUtils {

    public static CakeEntity aCakeEntityWith(
            Long id, String title, String description, String image
    ) {
        CakeEntity cakeEntity = new CakeEntity();
        cakeEntity.setId(id);
        cakeEntity.setTitle(title);
        cakeEntity.setDescription(description);
        cakeEntity.setImage(image);
        return cakeEntity;
    }

    public static CakeDto aCakeDtoWith(
            Long id, String title, String description, String image
    ) {
        CakeDto cakeDto = new CakeDto();
        cakeDto.setId(id);
        cakeDto.setTitle(title);
        cakeDto.setDescription(description);
        cakeDto.setImage(image);
        return cakeDto;
    }

    public static void assertCake(CakeEntity cakeEntity, CakeDto cakeDto) {
        assertEquals(cakeEntity.getId(), cakeDto.getId());
        assertEquals(cakeEntity.getTitle(), cakeDto.getTitle());
        assertEquals(cakeEntity.getDescription(), cakeDto.getDescription());
        assertEquals(cakeEntity.getImage(), cakeDto.getImage());
    }

    public static void assertCakes(List<CakeEntity> cakeEntities, List<CakeDto> cakeDtos) {
        assertEquals(cakeEntities.size(), cakeDtos.size());
        for (int i = 0; i < cakeEntities.size(); i++) {
            assertCake(cakeEntities.get(i), cakeDtos.get(i));
        }
    }
}
