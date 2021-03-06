package com.waracle.cakemgr.service;

import com.waracle.cakemgr.dto.CakeDto;
import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.repository.CakeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.waracle.cakemgr.util.CakeTestUtils.aCakeEntityWith;
import static com.waracle.cakemgr.util.CakeTestUtils.assertCakes;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CakeServiceTest {

    @Autowired
    private CakeService cakeService;

    @MockBean
    private CakeRepository cakeRepository;

    @Test
    public void getAllCakes_willReturnCakesFromRepository() {

        List<CakeEntity> cakeEntities = Arrays.asList(
                aCakeEntityWith(1L, "Cake 1", "Delicious but ugly", "image1"),
                aCakeEntityWith(2L, "Cake 2", "Bland", "image2"),
                aCakeEntityWith(3L, "Cake 3", "Disgusting but beautiful", "image3")
        );
        when(cakeRepository.findAll()).thenReturn(cakeEntities);

        List<CakeDto> cakeDtos = cakeService.getAllCakes();

        assertCakes(cakeEntities, cakeDtos);
    }


}
