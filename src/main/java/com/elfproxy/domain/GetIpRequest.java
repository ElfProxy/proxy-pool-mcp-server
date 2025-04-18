package com.elfproxy.domain;

import lombok.Data;

/**
 * obtain ip request parameters
 *
 * @author ElfProxy
 */
@Data
public class GetIpRequest {
    /**
     * country region code
     */
    private String country = "us";
    /**
     * Agreement： socket5 1  https 0
     */
    private Integer protocol = 0;

    /**
     * data formatting
     */
    private String format = "2";

    /**
     * Separator
     */
    private String semicolon = "1";

    /**
     * IP mode: 0: Rotation 1: Stickiness
     */
    private Integer ipMode = 1;


    /**
     * Time type: M minutes
     */
    private Integer time = 20;

    /**
     * Minutes m hours h
     */
    private String type = "m";

    /**
     * Generate quantity
     */
    private Integer count = 1;

    /**
     * apiKey
     */
    private String apiKey;

    /**
     * API generation method (1- account password 2- whitelist)
     */
    private Integer linkType = 1;

    /**
     * Package type (0: pay as you go (default) 1: pay per day)
     */
    private Integer packageType = 0;

    /**
     * Dynamic proxy type 0- Dynamic residential (default) 1- Dynamic computer room
     */
    private Integer dynamicType = 0;
}
