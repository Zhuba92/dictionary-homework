package entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class Word {
    private String name;
    private String Definition;

    public String getName() {
        return name;
    }

}
