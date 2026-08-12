package dev.java10x.MagicFridgeAI.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeminiResponseDTO {

    private List<StepsDTO> steps;
}
