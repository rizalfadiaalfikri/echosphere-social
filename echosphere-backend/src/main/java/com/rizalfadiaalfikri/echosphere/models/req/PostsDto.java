package com.rizalfadiaalfikri.echosphere.models.req;

import com.rizalfadiaalfikri.echosphere.models.entity.Users;

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

    private Users users;
}
