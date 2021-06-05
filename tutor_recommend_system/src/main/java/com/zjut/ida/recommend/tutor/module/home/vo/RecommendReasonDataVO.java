package com.zjut.ida.recommend.tutor.module.home.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author wly
 * @date 2021/5/12 15:18
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class RecommendReasonDataVO {
    private String name;
    private Integer category;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RecommendReasonDataVO that = (RecommendReasonDataVO) o;

        return new EqualsBuilder().append(name, that.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(name).toHashCode();
    }
}
