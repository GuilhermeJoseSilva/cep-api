package com.br.service;

import com.br.domain.Cliente;
import com.br.dto.ClienteAtualizacaoRequest;
import com.br.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final String VIA_CEP_URL = "https://viacep.com.br/ws/";

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public Cliente cadastrar(String nome, String cep) {
        var cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCep(cep);

        // Chamada ao ViaCEP para obter informações adicionais
        Cliente infoViaCep = obterInformacoesViaCep(cep);

        if (infoViaCep != null) {
            cliente.setLogradouro(infoViaCep.getLogradouro());
            cliente.setComplemento(infoViaCep.getComplemento());
            cliente.setBairro(infoViaCep.getBairro());
            cliente.setLocalidade(infoViaCep.getLocalidade());
            cliente.setUf(infoViaCep.getUf());
            cliente.setIbge(infoViaCep.getIbge());
            cliente.setGia(infoViaCep.getGia());
            cliente.setDdd(infoViaCep.getDdd());
            cliente.setSiafi(infoViaCep.getSiafi());
        }

        return repository.save(cliente);
    }

    public Optional<Cliente> obterPorId(Long id) {
        return repository.findById(id);
    }

    public List<Cliente> obterTodos() {
        return repository.findAll();
    }

    public Optional<Cliente> obterPorCep(String cep) {
        return repository.findByCep(cep);
    }

    public Cliente atualizarCliente(Long id, ClienteAtualizacaoRequest atualizacaoRequest) {
        Optional<Cliente> clienteExistente = repository.findById(id);

        if (clienteExistente.isPresent()) {
            var cliente = clienteExistente.get();
            cliente.setNome(atualizacaoRequest.getNome());
            cliente.setCep(atualizacaoRequest.getCep());

            // Atualização das informações do ViaCEP
            Cliente infoViaCep = obterInformacoesViaCep(atualizacaoRequest.getCep());

            if (infoViaCep != null) {
                cliente.setLogradouro(infoViaCep.getLogradouro());
                cliente.setComplemento(infoViaCep.getComplemento());
                cliente.setBairro(infoViaCep.getBairro());
                cliente.setLocalidade(infoViaCep.getLocalidade());
                cliente.setUf(infoViaCep.getUf());
                cliente.setIbge(infoViaCep.getIbge());
                cliente.setGia(infoViaCep.getGia());
                cliente.setDdd(infoViaCep.getDdd());
                cliente.setSiafi(infoViaCep.getSiafi());
            }

            return repository.save(cliente);
        } else {
            return null;
        }
    }

    public Optional<Cliente> buscarPorNome(String nome){
        return repository.findByNome(nome);
    }

    public void excluirCliente(Long id) {
        repository.deleteById(id);
    }

    private Cliente obterInformacoesViaCep(String cep) {
        String url = VIA_CEP_URL + cep + "/json";
        return restTemplate.getForObject(url, Cliente.class);
    }
}

