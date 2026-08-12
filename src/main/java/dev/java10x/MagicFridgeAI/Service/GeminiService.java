package dev.java10x.MagicFridgeAI.Service;
import dev.java10x.MagicFridgeAI.DTO.FoodItemDTO;
import dev.java10x.MagicFridgeAI.DTO.GeminiResponseDTO;
import dev.java10x.MagicFridgeAI.DTO.StepsDTO;
import dev.java10x.MagicFridgeAI.Model.FoodItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Finishings;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GeminiService {

    private final WebClient webClient;
    @Value("${gemini-api-key}")
    private String apiKey;

    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe(List<FoodItemDTO> foodItemDTOList){
        String ingredientes = foodItemDTOList.stream()
                .map(item-> String.format("Nome: %s, Quantidade: %s, Categoria: %s, Validade: %s",
                        item.getNome(), item.getQuantidade(), item.getCategoriaFoodItem(), item.getDataValidade()))
                .collect(Collectors.joining("/n"));

        String prompt = "Sugira uma receita de acordo com os ingredientes presentes em minha geladeira: "+ ingredientes;

        Map<String, Object> requestBody = Map.of(
                "model", "gemini-3.5-flash",
                "input", prompt
        );

        return webClient
                .post()
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .header("x-goog-api-key", apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(GeminiResponseDTO.class)
                .map(GeminiResponseDTO::getSteps)
                .map(stepsDTOS ->
                                stepsDTOS.stream()
                                        .filter(stepsDTO -> stepsDTO.getType().equals("model_output"))
                                        .findAny().orElse(null)
                        )
                .map(stepsDTO -> stepsDTO.getContent().get(0).getText());

    }
}
