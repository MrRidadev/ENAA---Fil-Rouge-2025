package org.example.backend.controller;

import org.example.backend.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/client")
@RestController
public class ClientController {

  private ClientRepository clientRepository;
  public ClientController(ClientRepository clientRepository) {
      this.clientRepository = clientRepository;
  }

  @GetMapping("/countClient")
    public int countClient() {
      return clientRepository.countClient();
  }
}
