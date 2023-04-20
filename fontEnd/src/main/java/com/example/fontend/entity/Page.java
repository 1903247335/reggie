package com.example.fontend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Page {
    private int currentPage;
    private int pageSize;
    private String name;
}
