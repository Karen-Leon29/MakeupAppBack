package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.Configuration;
import com.dorysoft.mackeupApp.repository.IRepositoryConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceConfiguration {
    @Autowired
    private IRepositoryConfiguration configurationRepository;

    // Método para obtener la configuración (dado que solo hay un registro)
    public Configuration getConfiguration() {
        return configurationRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Configuration not found"));
    }

    // Método para actualizar la configuración
    public Configuration updateConfiguration(Configuration newConfig) {
        // Dado que solo hay un registro, buscamos el existente y lo actualizamos.
        Configuration existingConfig = getConfiguration();
        existingConfig.setPrincipalImageUrl(newConfig.getPrincipalImageUrl());
        existingConfig.setFormatImageUrl(newConfig.getFormatImageUrl());
        existingConfig.setPrincipalText(newConfig.getPrincipalText());
        return configurationRepository.save(existingConfig);
    }
}
