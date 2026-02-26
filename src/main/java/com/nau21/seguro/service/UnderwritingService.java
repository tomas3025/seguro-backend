package com.nau21.seguro.service;

import com.nau21.seguro.entity.Cliente;
import com.nau21.seguro.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class UnderwritingService {

    private final ClienteRepository clienteRepository;

    public UnderwritingService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente avaliarESalvar(String nome, int idade) {

        String risco;
        if (idade < 18) {
            risco = "Recusado";
        } else if (idade <= 60) {
            risco = "Normal";
        } else {
            risco = "Elevado";
        }

        Cliente cliente = new Cliente(nome, idade, risco);
        return clienteRepository.save(cliente);
    }
    public String calcularRisco(int idade) {
        if (idade < 18) return "Recusado";
        if (idade <= 60) return "Normal";
        return "Elevado";
    }
}