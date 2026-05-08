package com.unicesumar.observa_aep.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email) {
}
