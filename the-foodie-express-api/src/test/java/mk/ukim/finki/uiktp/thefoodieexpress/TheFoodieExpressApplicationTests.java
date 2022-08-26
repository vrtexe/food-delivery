package mk.ukim.finki.uiktp.thefoodieexpress;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class TheFoodieExpressApplicationTests {
    
    @Autowired
    private MockMvc mockMvc;

//	@Test
//	void contextLoads() {
//	}
    
    @Test
    void load() throws Exception {
        var content = this.mockMvc.perform(get("/v3/api-docs.yaml"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        
        var file = new PrintWriter(new FileOutputStream("./src/main/resources/openapi-docs.yml"));
        file.write(content);
        file.flush();
        file.close();
    }
    
}
