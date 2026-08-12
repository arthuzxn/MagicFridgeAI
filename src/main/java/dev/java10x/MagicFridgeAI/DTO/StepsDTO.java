package dev.java10x.MagicFridgeAI.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepsDTO {

    private String type;
    private String signature;
    private List<ContentDTO> content;
}
