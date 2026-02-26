package com.nau21.seguro.controller;

import com.nau21.seguro.dto.ClienteRequest;
import com.nau21.seguro.dto.ClienteUpdateRequest;
import com.nau21.seguro.entity.Cliente;
import com.nau21.seguro.repository.ClienteRepository;
import com.nau21.seguro.service.UnderwritingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SeguroController {

    private final UnderwritingService underwritingService;
    private final ClienteRepository clienteRepository;

    public SeguroController(UnderwritingService underwritingService, ClienteRepository clienteRepository) {
        this.underwritingService = underwritingService;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/avaliar")
    public Cliente avaliar(@RequestBody ClienteRequest request) {
        return underwritingService.avaliarESalvar(
                request.getNome(),
                request.getIdade()
        );
    }
    @GetMapping("/clientes")
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> obterPorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }
    
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {

        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> atualizar(
            @PathVariable Long id,
            @RequestBody ClienteUpdateRequest request) {

        return clienteRepository.findById(id).map(cliente -> {

            cliente.setNome(request.getNome());
            cliente.setIdade(request.getIdade());
            cliente.setRisco(underwritingService.calcularRisco(request.getIdade()));

            Cliente guardado = clienteRepository.save(cliente);
            return ResponseEntity.ok(guardado);

        }).orElse(ResponseEntity.notFound().build());
    }
}