package org.mybatis.example.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * t_user
 * @author 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TUser implements Serializable {
    private Integer id;

    private String userName;

    private String sex;

    private String pwd;

    private String note;

    private Integer available;

    private Date createdate;

    /**
     * 删除状态
     */
    private Boolean deleteStatus;

    private static final long serialVersionUID = 1L;
}