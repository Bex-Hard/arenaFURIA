package projeto.arenaFuria.entity.model;

import jdk.jshell.Snippet;
import lombok.*;
import projeto.arenaFuria.entity.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    private String senderName;
    private String receiver;
    private String message;
    private String date;
    private Status status;
}
