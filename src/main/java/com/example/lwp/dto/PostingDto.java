package com.example.lwp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostingDto {
    private long category;
    private String title;
    private String content;
    private String value;
    private String keyword;
    private long pid;
}
