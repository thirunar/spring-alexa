package com.alexaframework.springalexa.intent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class IntentMetaData {

    private String name;

    private String response;


}
