package com.qywk.pojo.ao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author qlh
 * @date 2024/03/21 8:08
 * @description 中医检测问题集
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemAO {
    private String problem;
    private String option;
}
