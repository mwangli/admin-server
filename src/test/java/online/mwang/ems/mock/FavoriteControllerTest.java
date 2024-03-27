package online.mwang.ems.mock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import online.mwang.ems.pojo.entity.Favorite;
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
public class FavoriteControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private MockHttpSession session;
    private Favorite favorite;
    private List<Long> ids;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        session = new MockHttpSession();
        User user = new User();
        user.setName("test");
        session.setAttribute("user", user);

        Favorite favorite = new Favorite();
        favorite.setId(1L);
        this.favorite = favorite;

        ArrayList<Long> ids = new ArrayList<>();
        ids.add(1L);
        this.ids = ids;
    }


    @Test
    public void testSaveFavorite() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/favorite/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
                .content(JSON.toJSONString(favorite)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDeleteFavorite() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/favorite/delete")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
                .content(JSONObject.toJSONString(ids)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(1))
                .andDo(MockMvcResultHandlers.print());
    }
}