package kamysh.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement
public class ErrorDTO {
    String message;
    String error;
}
