package online.mwang.ems.service;

import online.mwang.ems.commom.enums.StatusEnum;
import online.mwang.ems.commom.utils.UpdateUtil;
import online.mwang.ems.pojo.bean.PageInfo;
import online.mwang.ems.pojo.bean.ResultInfo;
import online.mwang.ems.pojo.entity.User;
import online.mwang.ems.repo.UserRepo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Author mwangli
 * @Date 2020/12/11 15:55
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResultInfo<List<User>> listUser(User user) {
        user.setStatus(StatusEnum.AVAILABLE.getIndex());
        Integer pageIndex = Optional.ofNullable(user.getPageIndex()).orElse(1);
        Integer pageSize = Optional.ofNullable(user.getPageSize()).orElse(10);
        Integer total = (int) userRepo.count(Example.of(user));
        List<User> userList = userRepo.findAll(Example.of(user), Sort.by(Sort.Order.desc("createTime")))
                .parallelStream().skip((pageIndex - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        return ResultInfo.success(userList, PageInfo.of(pageIndex, pageSize, total));
    }

    public ResultInfo<User> saveUser(User user) {
        if (user.getId() != null && userRepo.existsById(user.getId())) {
            User user1 = userRepo.findById(user.getId()).get();
            UpdateUtil.copyNullProperties(user1, user);
        } else {
            user.setCreateTime(new Date());
        }
        user.setModifyTime(new Date());
        return ResultInfo.success(userRepo.save(user));
    }

    public ResultInfo<Integer> deleteUser(List<Long> ids) {
        AtomicInteger count = new AtomicInteger();
        ids.forEach(id ->
                userRepo.findById(id).ifPresent(user -> {
                    user.setStatus(StatusEnum.DELETED.getIndex());
                    userRepo.save(user);
                    count.getAndIncrement();
                }));
        return ResultInfo.success(count.get());
    }
}
