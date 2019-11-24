package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmmsRespData {
    private String file_id;
    private String url;
    private String deleteUrl;
}
