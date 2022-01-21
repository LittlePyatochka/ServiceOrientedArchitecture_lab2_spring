
package kamysh.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement
@Builder
public class HealthCountDto {
    private int healthCount;
}