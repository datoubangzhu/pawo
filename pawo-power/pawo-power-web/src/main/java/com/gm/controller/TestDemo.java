package com.gm.controller;

import lombok.Data;

import java.io.Serializable;


@Data
class TestDemo implements Serializable {

        private double id;
        private String name;
        private String sex;
        private String id_card;
        private int age;
        private String stature;
        private String weight;


}
