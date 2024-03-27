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
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "`order`")
@EqualsAndHashCode(callSuper = true)
@TypeDef(name = "json", typeClass = JsonStringType.class)

public class Order extends PageInfo implements Serializable {
    private static final long serialVersionUID = -6190380362423105548L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private Long productId;

    private Long customerId;

    private String logistics;

    private String address;

    private String phone;

    private Integer orderStatus;

    private Date createTime;

    private Date modifyTime;

    private Integer status;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private String extraInfo;
}