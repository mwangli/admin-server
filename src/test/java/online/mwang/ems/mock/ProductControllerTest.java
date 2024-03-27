package online.mwang.ems.mock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import online.mwang.ems.pojo.entity.Product;
import online.mwang.ems.pojo.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mwangli
 * @date 2020/12/15 17:44
 **/
@Rollback
@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private MockHttpSession session;
    private Product product;
    private List<Long> ids;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        session = new MockHttpSession();
        User user = new User();
        user.setName("test");
        session.setAttribute("user", user);

        Product product = new Product();
        product.setId(1L);
        this.product = product;

        ArrayList<Long> ids = new ArrayList<>();
        ids.add(1L);
        this.ids = ids;
    }

    @Test
    public void testListProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
                .content(JSON.toJSONString(product))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(1))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testSaveProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
                .content(JSON.toJSONString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/product/delete")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
                .content(JSONObject.toJSONString(ids)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(1))
                .andDo(MockMvcResultHandlers.print());
    }
}