package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergey on 8/19/17.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cello implements Serializable {

    String bow;
    String spire;
    List<String> strings;

}
