package online.mwang.ems.pojo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mwangli
 * @date 2020/12/16 15:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {

    private Integer pageIndex;
    private Integer pageSize;
    private Integer total;

    public static PageInfo of(Integer pageIndex, Integer pageSize, Integer total) {
        return new PageInfo(pageIndex, pageSize, total);
    }
}
