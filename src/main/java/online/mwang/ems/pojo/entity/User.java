package online.mwang.ems.pojo.entity;

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
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@TypeDef(name = "json", typeClass = JsonStringType.class)
@DynamicUpdate
@DynamicInsert
public class User extends PageInfo implements Serializable {

    private static final long serialVersionUID = -1784872896631662867L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String openUserId;

    private String name;

    private String phone;

    private String gender;

    private Date register;

    private String description;

    private Integer userStatus;

    private Date createTime;

    private Date modifyTime;

    private Integer status;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private String openUserInfo;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private String address;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private String extraInfo;

}