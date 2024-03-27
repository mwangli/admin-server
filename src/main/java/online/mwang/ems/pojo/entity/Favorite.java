package online.mwang.ems.pojo.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author mwangli
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode()
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Favorite implements Serializable {

    private static final long serialVersionUID = 7631591509697614463L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long customerId;
}