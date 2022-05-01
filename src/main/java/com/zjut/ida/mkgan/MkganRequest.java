package com.zjut.ida.mkgan;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Casterx on 2022/3/27.
 */
@Data
public class MkganRequest {
    List<Integer> user_inputs;
    List<List<Integer>> item_inputs;
}