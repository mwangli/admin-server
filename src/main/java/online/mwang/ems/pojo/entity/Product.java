package online.mwang.ems.pojo.entity;

import com.alibaba.fastjson.JSONObject;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import online.mwang.ems.pojo.bean.PageInfo;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mwangli
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(callSuper = true)
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Product extends PageInfo implements Serializable {
    private static final long serialVersionUID = 5321306525719369483L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String name;

    private Long categoryId;

    private String categoryDesc;

    private Integer favorCount;

    private String description;

    private BigDecimal price;

    private String size;

    private Integer productStatus;

    private Date createTime;

    private Date modifyTime;

    private Integer status;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private JSONObject tags;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private JSONObject images;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private JSONObject extraInfo;
}