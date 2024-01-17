package com.pinterestclonebackend.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentAwsDTO {
    private String contentTitle;

    private String contentUrl;

    private String contentSummary;

    private String contentKey;
}
