package kamysh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
public class ResultListDto {
    private List<?> results;

    public List<?> getResults() {
        return results;
    }
}