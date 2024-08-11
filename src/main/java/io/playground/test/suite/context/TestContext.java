package io.playground.test.suite.context;

import lombok.*;

import java.util.List;

@Builder
@Data
@Setter(AccessLevel.NONE)
public class TestContext {
    
    private String name;
    
    private List<String> types;
}
