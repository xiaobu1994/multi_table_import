package com.xiaobu.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author  xiaobu
 * @date   on  2020/12/2 14:49
 * @version JDK1.8.0_171
 * @description 
 */
@Entity
@Data
@Table(name="random_code")
public class RandomCode {
    private Long id;



}