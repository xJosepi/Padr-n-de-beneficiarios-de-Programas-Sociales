package pe.edu.upeu.service;

import pe.edu.upeu.model.Beneficiario;
import pe.edu.upeu.repository.BeneficiarioRepository;
import java.util.List;

public class BeneficiarioServiceImp implements BeneficiarioService {

    // Conectamos con el repositorio
    BeneficiarioRepository repo = BeneficiarioRepository.getInstance();

    private static BeneficiarioService instance = new BeneficiarioServiceImp();

    public static BeneficiarioService getInstance() {
        if(instance == null) {
            instance = new BeneficiarioServiceImp();
        }
        return instance;
    }

    @Override
    public void save(Beneficiario b) { repo.save(b); }

    @Override
    public List<Beneficiario> findAll() { return repo.findAll(); }

    @Override
    public void update(Beneficiario b, int index) { repo.update(b, index); }

    @Override
    public void delete(int index) { repo.delete(index); }
}