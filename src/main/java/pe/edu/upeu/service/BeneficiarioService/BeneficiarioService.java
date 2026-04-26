package pe.edu.upeu.service;

import pe.edu.upeu.model.Beneficiario;
import java.util.List;

public interface BeneficiarioService {
    void save(Beneficiario b);
    List<Beneficiario> findAll();
    void update(Beneficiario b, int index);
    void delete(int index);
}
