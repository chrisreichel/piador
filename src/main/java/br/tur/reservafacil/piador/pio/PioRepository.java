package br.tur.reservafacil.piador.pio;

import java.util.List;

public interface PioRepository {
    void save(Pio pio);
    List<Pio> findByUsername(String username);
}
