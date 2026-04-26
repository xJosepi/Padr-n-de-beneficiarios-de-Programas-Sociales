package pe.edu.upeu.repository;

import pe.edu.upeu.model.Beneficiario;
import java.util.ArrayList;
import java.util.List;

public class BeneficiarioRepository {
    // Esto asegura que siempre usemos la misma lista en todo el programa (Patrón Singleton)
    public static BeneficiarioRepository instance = new BeneficiarioRepository();

    public static BeneficiarioRepository getInstance() {
        if(instance == null) {
            instance = new BeneficiarioRepository();
        }
        return instance;
    }

    // Aquí se guardan los datos
    List<Beneficiario> beneficiarios = new ArrayList<>();

    // Métodos para Crear, Leer, Actualizar y Eliminar
    public void save(Beneficiario b) { beneficiarios.add(b); }
    public List<Beneficiario> findAll() { return beneficiarios; }
    public void update(Beneficiario b, int index) { beneficiarios.set(index, b); }
    public void delete(int index) { beneficiarios.remove(index); }
}