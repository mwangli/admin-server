package online.mwang.ems.pojo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mwangli
 * @date 2020/12/16 14:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultInfo<T> {

    private T data;
    private String code;
    private String message;
    private PageInfo pageInfo;

    public static <T> ResultInfo<T> success(T data) {
        return new ResultInfo<T>(data, "OK", "success", null);
    }

    public static <T> ResultInfo<T> success(T data, PageInfo pageInfo) {
        return new ResultInfo<T>(data, "OK", "success", pageInfo);
    }

    public static <T> ResultInfo<T> fail(String message) {
        return new ResultInfo<T>(null, "ERR", message, null);
    }
}
