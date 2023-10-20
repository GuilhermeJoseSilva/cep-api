package com.br.controller;

import com.br.domain.Cliente;
import com.br.dto.ClienteAtualizacaoRequest;
import com.br.dto.ClienteRequest;
import com.br.service.ClienteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteRequest clienteRequest){
        var cliente = clienteService.cadastrar(clienteRequest.getNome(), clienteRequest.getCep());
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterPorId(@PathVariable Long id){
        Optional<Cliente> cliente = clienteService.obterPorId(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/todos")
    public ResponseEntity<List<Cliente>> obterTodosClientes() {
        List<Cliente> clientes = clienteService.obterTodos();
        return ResponseEntity.ok(clientes);
    }


    @GetMapping("/porCep/{cep}")
    public ResponseEntity<Cliente> obterClienteCep(@PathVariable String cep){
        Optional<Cliente> cliente = clienteService.obterPorCep(cep);

        if ((cliente.isPresent())){
            return ResponseEntity.ok(cliente.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/porNome/{nome}")
    public ResponseEntity<Cliente> obterClienteNome(@PathVariable String nome){
        Optional<Cliente> cliente = clienteService.buscarPorNome(nome);

        if ((cliente.isPresent())){
            return ResponseEntity.ok(cliente.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody ClienteAtualizacaoRequest atualizacaoRequest){
        var clienteAtualizado = clienteService.atualizarCliente(id, atualizacaoRequest);

        if (clienteAtualizado != null) {
            return ResponseEntity.ok(clienteAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id){
        clienteService.excluirCliente(id);
        return ResponseEntity.ok().build();
    }
}
