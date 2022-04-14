package my.store.assignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import my.store.assignment.repository.KeyValueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureWebTestClient
class ApplicationIT {

    private ObjectMapper mapper;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private KeyValueRepository keyValueRepository;

    @Test
    void shouldReturnNotFound() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        when(keyValueRepository.get(anyString())).thenReturn(Optional.empty());
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/store/unknownKey")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(404);
    }
}
