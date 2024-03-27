package online.mwang.ems.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.mwang.ems.pojo.bean.ResultInfo;
import online.mwang.ems.pojo.entity.User;
import online.mwang.ems.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author mwangli
 * @Date 2020/12/11 15:47
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/list")
    @ApiOperation("查询用户信息")
    ResultInfo<List<User>> listAll(@RequestBody User user) {
        return userService.listUser(user);
    }

    @PostMapping("/save")
    @ApiOperation("保存用户信息")
    ResultInfo<User> saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除用户信息")
    ResultInfo<Integer> deleteUser(@RequestBody List<Long> ids) {
        return userService.deleteUser(ids);
    }
}
