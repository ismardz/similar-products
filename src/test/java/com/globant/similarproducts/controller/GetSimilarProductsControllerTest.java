package com.globant.similarproducts.controller;

import com.globant.similarproducts.service.GetSimilarProductsService;
import com.globant.similarproducts.service.model.GetSimilarProductsOutput;
import com.globant.similarproducts.service.model.SimilarProductDetail;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest(GetSimilarProductsController.class)
class GetSimilarProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetSimilarProductsService getSimilarProductsService;

    @Test
    void getSimilarProduct_returnsOkWithList() throws Exception {
        SimilarProductDetail detail1 = new SimilarProductDetail("2", "Product 2", 100.0, true);
        SimilarProductDetail detail2 = new SimilarProductDetail("3", "Product 3", 100.0, true);
        List<SimilarProductDetail> details = List.of(detail1, detail2);
        Mockito.when(getSimilarProductsService.getSimilarProducts(anyString()))
                .thenReturn(new GetSimilarProductsOutput(details));

        mockMvc.perform(get("/product/1/similar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists());
    }

    @Test
    void getSimilarProduct_returnsOkWithEmptyList() throws Exception {
        Mockito.when(getSimilarProductsService.getSimilarProducts(anyString()))
                .thenReturn(new GetSimilarProductsOutput(List.of()));

        mockMvc.perform(get("/product/1/similar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }
}

