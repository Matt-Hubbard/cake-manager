package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.dto.CakeDto;
import com.waracle.cakemgr.service.CakeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.waracle.cakemgr.util.CakeTestUtils.aCakeDtoWith;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CakeControllerTest {


    @Autowired
    private CakeController cakeController;

    @MockBean
    private CakeService cakeService;

    @Test
    public void getAllCakes_willReturnCakesFromRepository() {

        List<CakeDto> expectedCakeDtos = Arrays.asList(
                aCakeDtoWith(1L, "Cake 1", "Delicious but ugly", "image1"),
                aCakeDtoWith(2L, "Cake 2", "Bland", "image2"),
                aCakeDtoWith(3L, "Cake 3", "Disgusting but beautiful", "image3")
        );
        when(cakeService.getAllCakes()).thenReturn(expectedCakeDtos);

        CollectionModel<CakeDto> cakeDtos = cakeController.getAllCakes();
        assertEquals(expectedCakeDtos, new ArrayList<>(cakeDtos.getContent()));
    }
}
