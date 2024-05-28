package br.com.fiap.traskBackAPI.dto;

import br.com.fiap.traskBackAPI.model.CollectPoint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CollectPointDTO(
        Long id,

        @NotBlank(message = "Name is mandatory")
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        String name,

        @NotBlank(message = "Address is mandatory")
        @Size(max = 255, message = "Address cannot exceed 255 characters")
        String address,

        @NotBlank(message = "Trash type is mandatory")
        @Size(max = 50, message = "Trash type cannot exceed 50 characters")
        String trashType,

        @NotNull(message = "Latitude is mandatory")
        Double latitude,

        @NotNull(message = "Longitude is mandatory")
        Double longitude
) {

}
