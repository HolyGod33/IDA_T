package com.zjut.ida.mkgan;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Casterx on 2022/3/27.
 */
@Data
public class MkganResults {
    private List<List<BigDecimal>> outputs;
}
