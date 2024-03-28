package online.mwang.ems.pojo.entity;

import com.alibaba.fastjson.JSONObject;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import online.mwang.ems.pojo.bean.PageInfo;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author mwangli
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(callSuper = true)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Category extends PageInfo implements Serializable {

    private static final long serialVersionUID = 2732895907102710368L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer categoryStatus;

    private Date createTime;

    private Date modifyTime;

    private Integer status;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private JSONObject extraInfo;
}