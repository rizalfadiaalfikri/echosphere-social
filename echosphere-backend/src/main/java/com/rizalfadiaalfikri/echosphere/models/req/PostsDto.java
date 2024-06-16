package com.rizalfadiaalfikri.echosphere.models.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsDto {
    private String caption;

    private String image;

    private String video;

    private Long usersId;
}
