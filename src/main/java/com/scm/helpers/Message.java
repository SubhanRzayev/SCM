package com.scm.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {

    private String content;
    @Builder.Default
    private MessageType type = MessageType.blue;
}
