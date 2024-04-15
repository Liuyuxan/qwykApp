package com.qywk.pojo.vo;


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
public class ProblemVO {
    private String problem;
    private List<String> optionBox;
}
