package br.com.mercado_souto.api.category;

import br.com.mercado_souto.model.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    private String name;

    public Category build(){
        return Category.builder()
                        .name(name)
                        .build();
    }
}
