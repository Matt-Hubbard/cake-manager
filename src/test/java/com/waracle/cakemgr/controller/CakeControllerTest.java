package com.waracle.cakemgr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.dto.CakeDto;
import com.waracle.cakemgr.service.CakeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.waracle.cakemgr.util.CakeTestUtils.aCakeDtoWith;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CakeControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CakeService cakeService;

    @Test
    @WithMockUser
    public void getAllCakes_returnCakesFromService() throws Exception {

        List<CakeDto> expectedCakeDtos = Arrays.asList(
                aCakeDtoWith(1L, "Cake 1", "Delicious but ugly", "image1"),
                aCakeDtoWith(2L, "Cake 2", "Bland", "image2"),
                aCakeDtoWith(3L, "Cake 3", "Disgusting but beautiful", "image3")
        );
        when(cakeService.getAllCakes()).thenReturn(expectedCakeDtos);
        mockMvc.perform(get("/cakes").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.cakeDtoList.[0]").value(expectedCakeDtos.get(0)))
                .andExpect(jsonPath("$._embedded.cakeDtoList.[1]").value(expectedCakeDtos.get(1)))
                .andExpect(jsonPath("$._embedded.cakeDtoList.[2]").value(expectedCakeDtos.get(2)));
    }

    @Test
    @WithMockUser
    public void createCake_callsService_andReturnCake() throws Exception {
        //given
        CakeDto cakeDtoInput = aCakeDtoWith(1L, "Cake 1", "Delicious but ugly", "image1");
        when(cakeService.createCake(eq(cakeDtoInput))).thenReturn(cakeDtoInput);
        //when
        mockMvc.perform(
                post("/cakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(cakeDtoInput))
                        .with(csrf())

        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(cakeDtoInput))
        ;
    }

    @Test
    @WithMockUser
    public void createCake_returnsBadRequest_whenTitleIsNull() throws Exception {
        //given
        CakeDto cakeDtoInput = aCakeDtoWith(null, null, "Delicious but ugly", "image1");
        when(cakeService.createCake(eq(cakeDtoInput))).thenReturn(cakeDtoInput);
        //when
        mockMvc.perform(
                post("/cakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(cakeDtoInput))
                        .with(csrf())

        )
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @WithMockUser
    public void createCake_returnsBadRequest_whenTitleIsEmpty() throws Exception {
        //given
        CakeDto cakeDtoInput = aCakeDtoWith(null, "", "Delicious but ugly", "image1");
        when(cakeService.createCake(eq(cakeDtoInput))).thenReturn(cakeDtoInput);
        //when
        mockMvc.perform(
                post("/cakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(cakeDtoInput))
                        .with(csrf())

        )
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @WithMockUser
    public void createCake_returnsBadRequest_whenDescriptionIsNull() throws Exception {
        //given
        CakeDto cakeDtoInput = aCakeDtoWith(null, "Cake 1", null, "image1");
        when(cakeService.createCake(eq(cakeDtoInput))).thenReturn(cakeDtoInput);
        //when
        mockMvc.perform(
                post("/cakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(cakeDtoInput))
                        .with(csrf())

        )
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @WithMockUser
    public void createCake_returnsBadRequest_whenDescriptionIsEmpty() throws Exception {
        //given
        CakeDto cakeDtoInput = aCakeDtoWith(null, "Cake 1", "", "image1");
        when(cakeService.createCake(eq(cakeDtoInput))).thenReturn(cakeDtoInput);
        //when
        mockMvc.perform(
                post("/cakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(cakeDtoInput))
                        .with(csrf())

        )
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @WithMockUser
    public void createCake_returnsBadRequest_whenImageIsNull() throws Exception {
        //given
        CakeDto cakeDtoInput = aCakeDtoWith(null, "Cake 1", "Delicious but ugly", null);
        when(cakeService.createCake(eq(cakeDtoInput))).thenReturn(cakeDtoInput);
        //when
        mockMvc.perform(
                post("/cakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(cakeDtoInput))
                        .with(csrf())

        )
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @WithMockUser
    public void createCake_returnsBadRequest_whenImageIsEmpty() throws Exception {
        //given
        CakeDto cakeDtoInput = aCakeDtoWith(null, "Cake 1", "Delicious but ugly", "");
        when(cakeService.createCake(eq(cakeDtoInput))).thenReturn(cakeDtoInput);
        //when
        mockMvc.perform(
                post("/cakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(cakeDtoInput))
                        .with(csrf())

        )
                .andExpect(status().isBadRequest())
        ;
    }

}
