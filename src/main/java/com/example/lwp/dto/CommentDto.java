package com.example.lwp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {
    private String text;
    private String email;
    private long cid;
    private long pid;
}
