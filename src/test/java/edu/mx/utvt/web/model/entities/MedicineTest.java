package edu.mx.utvt.web.model.entities;

import edu.mx.utvt.web.model.enums.RouteMedication;
import edu.mx.utvt.web.model.repositories.MedicineRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Rollback(value = false)

public class MedicineTest {
    @Autowired
    private MedicineRepository medicineRepository;

    @Test
    @DisplayName("Crear Medicinas")
    void createMedicineTest(){
        Medicine medicine = null;
        medicine = medicineRepository.save(Medicine.builder().name("Diclofenaco").description("Medicamento para dolores").administration("Oral").routeMedication(RouteMedication.ORAL_ADMINISTRATION).build());
        Assertions.assertNotNull(medicine);
        log.info("Medicina Creada satisfactoriamente: " + medicine.toString());
    }

    @Test
    @DisplayName("Listar Medicamentos")
    void listAllMedicines(){
        log.info("Buscando todas las medicinas");
        List<Medicine> medicineList = medicineRepository.findAll();
        log.info("Medicinas encontradas: " + medicineRepository.count());
        Assertions.assertNotNull(medicineList);

        for (Medicine medicine : medicineList){
            log.info("Nombre: " + medicine.getName());
            log.info("Descripcion: " + medicine.getDescription());
        }
    }

    @Test
    @DisplayName("Actualizar Medicamentos")
    void updateMedicineTest(){
        Long medicineId = 2L;
        Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
        Assertions.assertTrue(optionalMedicine.isPresent());
        log.info("Medicina encontrada a Actualizar: " + medicineRepository.findById(medicineId));
        Medicine medicine = optionalMedicine.get();
        medicine.setRouteMedication(RouteMedication.ORAL_ADMINISTRATION);
        log.info("Medicina Actualizada: " + medicine.getName());
    }

    @Test
    @DisplayName("Borrar Medicina")
    void deleteMedicineTest(){
        log.info("Eliminar Medicamentos");
        Long medicineId = 1L;
        Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
        Medicine medicine = optionalMedicine.get();
        medicineRepository.delete(medicine);
        Assertions.assertFalse(medicineRepository.existsById(medicineId));
        log.info("Medicina Borrada Exitosamente");
    }
}
