package com.Transaccion;

// Importar la implementación del servicio ClienteServiceImp
import com.Transaccion.service.ClienteServiceImp;

// Importar la clase modelo Clientes, que representa a un cliente
import com.Transaccion.model.Clientes;

// Importar la interfaz del repositorio ClienteRepository, que maneja operaciones de datos de clientes
import com.Transaccion.repository.ClienteRepository;

// Importar la interfaz del servicio ClienteService (si es necesario para futuras referencias)
import com.Transaccion.service.ClienteService;

// Importar las anotaciones necesarias de JUnit para la creación de pruebas
import org.junit.jupiter.api.BeforeEach; // Para ejecutar configuración antes de cada prueba
import org.junit.jupiter.api.Test; // Para marcar un método como una prueba unitaria

// Importar las anotaciones de Mockito, que permite la creación de objetos simulados
import org.mockito.InjectMocks; // Para inyectar mocks en el objeto que se está probando
import org.mockito.Mock; // Para crear un mock de una clase específica
import org.mockito.MockitoAnnotations; // Para inicializar los mocks anotados

import java.util.ArrayList; // Para utilizar la lista dinámica ArrayList
import java.util.List; // Para utilizar la interfaz List
import java.util.Optional; // Para utilizar la clase Optional que representa un valor que puede estar presente o no

// Importar las afirmaciones de JUnit que se utilizan para verificar resultados
import static org.junit.jupiter.api.Assertions.*;
// Importar los métodos de Mockito para simular comportamientos de métodos
import static org.mockito.Mockito.*;

// Definición de la clase de prueba
public class ClienteServiceImpTest {

    private Clientes clientes; // Variable para almacenar la instancia de Clientes que se usará en las pruebas

    @Mock // Indica que este es un objeto simulado
    private ClienteRepository clienteRepository; // Simulación del repositorio que maneja las operaciones de cliente

    @InjectMocks // Indica que se debe inyectar el mock en este objeto
    private ClienteServiceImp clienteService; // Servicio que se está probando, que utiliza el repositorio simulado

    // Este método se ejecuta antes de cada prueba para preparar el entorno de prueba
    @BeforeEach
    void setup() {
        // Inicializa los mocks que se han declarado con la anotación @Mock
        MockitoAnnotations.openMocks(this);

        // Crea una nueva instancia de Clientes que se utilizará en las pruebas
        clientes = new Clientes();
        // Establece valores para las propiedades del cliente simulado
        clientes.setCustomerId(1L); // Establece el ID del cliente long
        clientes.setEmail("MMM@GMAIL.COM"); // Establece el correo electrónico del cliente
        clientes.setCustomerName("Juan"); // Establece el nombre del cliente
        clientes.setPhoneNumber("123456789"); // Establece el número de teléfono del cliente
    }

    // Método que prueba el comportamiento del método findAll() del servicio
    @Test
    void findAll() {
        // Crea una lista para almacenar instancias de Clientes y añade el cliente simulado
        List<Clientes> lst = new ArrayList<>();
        lst.add(clientes);

        // Define el comportamiento del mock: cuando se llame a findAll(), devolverá la lista creada
        when(clienteRepository.findAll()).thenReturn(lst);

        // Llama al método findAll() del servicio y guarda el resultado en una lista temporal
        List<Clientes> tmpList = clienteService.findAll();

        // Verifica que el tamaño de la lista devuelta sea 1 (es decir, que contenga un cliente)
        assertEquals(1, tmpList.size());
    }

    // Método que prueba el comportamiento del método findById() del servicio
    @Test
    void findById() {
        // Define el comportamiento del mock: cuando se llame a findById(1L), devolverá el cliente simulado
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clientes));

        // Llama al método findById() del servicio y guarda el resultado en una variable
        Clientes result = clienteService.findById(1L);

        // Verifica que el resultado no sea nulo (es decir, que se ha encontrado un cliente)
        assertNotNull(result);
        // Verifica que el nombre del cliente devuelto sea "Juan"
        assertEquals("Juan", result.getCustomerName());
        // Verifica que el método findById() del repositorio fue llamado una vez con el argumento 1L
        verify(clienteRepository, times(1)).findById(1L);
    }

    // Método que prueba el comportamiento del método save() del servicio
    @Test
    void save() {
        // Crea una nueva instancia de Clientes que se va a guardar
        Clientes clienteToSave = new Clientes();
        clienteToSave.setCustomerId(1L);
        clienteToSave.setCustomerName("Juan");
        clienteToSave.setEmail("juan@example.com");
        clienteToSave.setPhoneNumber("123456789");

        // Define el comportamiento del mock: cuando se llame a save(), devolverá el cliente que se está guardando
        when(clienteRepository.save(clienteToSave)).thenReturn(clienteToSave);

        // Llama al método save() del servicio y guarda el resultado
        Clientes savedCliente = clienteService.save(clienteToSave);

        // Verifica que el resultado no sea nulo
        assertNotNull(savedCliente);
        // Verifica que el nombre del cliente guardado sea "Juan"
        assertEquals("Juan", savedCliente.getCustomerName());
        // Verifica que el método save() del repositorio fue llamado una vez con el cliente que se está guardando
        verify(clienteRepository, times(1)).save(clienteToSave);//1 VEZ PARA EVITAR DUPLICADOS
    }

    //Agregar este método a la clase ClienteServiceImpTest
    @Test
    void update() {
        // Prepara un cliente para actualizar
        Clientes clienteToUpdate = new Clientes();
        clienteToUpdate.setCustomerId(1L);
        clienteToUpdate.setCustomerName("Juan Actualizado");
        clienteToUpdate.setEmail("juan_actualizado@example.com");
        clienteToUpdate.setPhoneNumber("987654321");

        // Define el comportamiento del mock: cuando se llame a findById(1L), devolverá el cliente existente
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clientes));

        // Define el comportamiento del mock: cuando se llame a save(), devolverá el cliente actualizado
        when(clienteRepository.save(clientes)).thenReturn(clientes);

        // Llama al método update() del servicio
        Clientes resultado = clienteService.update(1L, clienteToUpdate);

        // Verifica que el resultado no sea nulo
        assertNotNull(resultado);
        // Verifica que el nombre del cliente actualizado sea "Juan Actualizado"
        assertEquals("Juan Actualizado", resultado.getCustomerName());
        // Verifica que el método findById() del repositorio fue llamado una vez
        verify(clienteRepository, times(1)).findById(1L);
        // Verifica que el método save() del repositorio fue llamado una vez
        verify(clienteRepository, times(1)).save(clientes);
    }
    @Test
    void delete() {
        Long idToDelete = 1L;

        // Define el comportamiento del mock: cuando se llame a existsById(idToDelete), devolverá true
        when(clienteRepository.existsById(idToDelete)).thenReturn(true);

        // Llama al método delete() del servicio
        clienteService.delete(idToDelete);

        // Verifica que el método deleteById() del repositorio fue llamado una vez con el ID correcto
        verify(clienteRepository, times(1)).deleteById(idToDelete);
    }
    @Test
    void delete_ClientNotFound() {
        Long idToDelete = 1L;

        // Define el comportamiento del mock: cuando se llame a existsById(idToDelete), devolverá false
        when(clienteRepository.existsById(idToDelete)).thenReturn(false);

        // Llama al método delete() del servicio y verifica que se lanza una excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.delete(idToDelete);
        });

        // Verifica que el mensaje de la excepción sea el esperado
        assertEquals("Cliente not found", exception.getMessage());

        // Verifica que el método deleteById() del repositorio NO fue llamado
        verify(clienteRepository, never()).deleteById(idToDelete);
    }
    // Agregar pruebas para el método findByName
    @Test
    void findByName_ClienteFound() {
        String nameToFind = "Juan"; // Nombre que buscamos

        // Define el comportamiento del mock: cuando se llame a findByCustomerNameContainingIgnoreCase(nameToFind), devolverá el cliente simulado
        when(clienteRepository.findByCustomerNameContainingIgnoreCase(nameToFind)).thenReturn(Optional.of(clientes));

        // Llama al método findByName() del servicio
        Clientes foundCliente = clienteService.findByName(nameToFind);

        // Verifica que el cliente encontrado no sea nulo
        assertNotNull(foundCliente);
        // Verifica que el nombre del cliente encontrado sea el esperado
        assertEquals("Juan", foundCliente.getCustomerName());

        // Verifica que el método findByCustomerNameContainingIgnoreCase() del repositorio fue llamado una vez
        verify(clienteRepository, times(1)).findByCustomerNameContainingIgnoreCase(nameToFind);
    }

    @Test
    void findByName_ClienteNotFound() {
        String nameToFind = "Pedro"; // Nombre que no existe en la base de datos

        // Define el comportamiento del mock: cuando se llame a findByCustomerNameContainingIgnoreCase(nameToFind), devolverá un Optional vacío
        when(clienteRepository.findByCustomerNameContainingIgnoreCase(nameToFind)).thenReturn(Optional.empty());

        // Llama al método findByName() del servicio y verifica que se lanza una excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.findByName(nameToFind);
        });

        // Verifica que el mensaje de la excepción sea el esperado
        assertEquals("Cliente not found", exception.getMessage());

        // Verifica que el método findByCustomerNameContainingIgnoreCase() del repositorio fue llamado una vez
        verify(clienteRepository, times(1)).findByCustomerNameContainingIgnoreCase(nameToFind);
    }


}
