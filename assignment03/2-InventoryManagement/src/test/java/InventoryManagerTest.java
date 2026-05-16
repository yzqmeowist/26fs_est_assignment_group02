import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryManagerTest {

    private InventoryDatabaseConnector databaseConnector;
    private InventoryManager inventoryManager;

    @BeforeEach
    void setUp() {
        databaseConnector = mock(InventoryDatabaseConnector.class);
        inventoryManager = new InventoryManager(databaseConnector);
    }

    @Test
    void getLowStockProductsReturnsEmptyListWhenThereAreNoProducts() {
        when(databaseConnector.getAllProducts()).thenReturn(List.of());

        List<Product> result = inventoryManager.getLowStockProducts();

        assertTrue(result.isEmpty());
        verify(databaseConnector).getAllProducts();
        verify(databaseConnector).close();
    }

    @Test
    void getLowStockProductsReturnsEmptyListWhenNoProductsAreLowStock() {
        Product product1 = new Product("1", "Keyboard", "Electronics", 10, 50.0);
        Product product2 = new Product("2", "Monitor", "Electronics", 15, 200.0);

        when(databaseConnector.getAllProducts()).thenReturn(List.of(product1, product2));

        List<Product> result = inventoryManager.getLowStockProducts();

        assertTrue(result.isEmpty());
        verify(databaseConnector).getAllProducts();
        verify(databaseConnector).close();
    }

    @Test
    void getLowStockProductsReturnsAllProductsWhenAllProductsAreLowStock() {
        Product product1 = new Product("1", "Mouse", "Electronics", 3, 20.0);
        Product product2 = new Product("2", "USB Cable", "Electronics", 9, 5.0);

        when(databaseConnector.getAllProducts()).thenReturn(List.of(product1, product2));

        List<Product> result = inventoryManager.getLowStockProducts();

        assertEquals(List.of(product1, product2), result);
        verify(databaseConnector).getAllProducts();
        verify(databaseConnector).close();
    }

    @Test
    void getLowStockProductsReturnsOnlyProductsBelowTen() {
        Product lowStock = new Product("1", "Mouse", "Electronics", 9, 20.0);
        Product exactlyTen = new Product("2", "Keyboard", "Electronics", 10, 50.0);
        Product enoughStock = new Product("3", "Monitor", "Electronics", 11, 200.0);

        when(databaseConnector.getAllProducts()).thenReturn(
                List.of(lowStock, exactlyTen, enoughStock)
        );

        List<Product> result = inventoryManager.getLowStockProducts();

        assertEquals(List.of(lowStock), result);
        verify(databaseConnector).getAllProducts();
        verify(databaseConnector).close();
    }

    @Test
    void getLowStockProductsClosesDatabaseWhenGetAllProductsFails() {
        when(databaseConnector.getAllProducts()).thenThrow(new RuntimeException("Database failure"));

        assertThrows(RuntimeException.class,
                () -> inventoryManager.getLowStockProducts());

        verify(databaseConnector).getAllProducts();
        verify(databaseConnector).close();
    }



    // getProductsByCategory
    @Test
    void getProductsByCategoryRejectsNullCategory() {
        assertThrows(IllegalArgumentException.class,
                () -> inventoryManager.getProductsByCategory(null));

        verifyNoInteractions(databaseConnector);
    }

    @Test
    void getProductsByCategoryRejectsEmptyCategory() {
        assertThrows(IllegalArgumentException.class,
                () -> inventoryManager.getProductsByCategory(""));

        verifyNoInteractions(databaseConnector);
    }

    @Test
    void getProductsByCategoryRejectsBlankCategory() {
        assertThrows(IllegalArgumentException.class,
                () -> inventoryManager.getProductsByCategory("   "));

        verifyNoInteractions(databaseConnector);
    }

    @Test
    void getProductsByCategoryReturnsProductsFromDatabase() {
        Product mouse = new Product("1", "Mouse", "Electronics", 5, 20.0);

        when(databaseConnector.getProductsByCategory("Electronics"))
                .thenReturn(List.of(mouse));

        List<Product> result = inventoryManager.getProductsByCategory("Electronics");

        assertEquals(List.of(mouse), result);
        verify(databaseConnector).getProductsByCategory("Electronics");
        verify(databaseConnector).close();
    }

    @Test
    void getProductsByCategoryClosesDatabaseWhenQueryFails() {
        when(databaseConnector.getProductsByCategory("Electronics"))
                .thenThrow(new RuntimeException("Database failure"));

        assertThrows(RuntimeException.class,
                () -> inventoryManager.getProductsByCategory("Electronics"));

        verify(databaseConnector).getProductsByCategory("Electronics");
        verify(databaseConnector).close();
    }

    @Test
    void getProductsByCategoryReturnsEmptyListWhenDatabaseReturnsNoProducts() {
        when(databaseConnector.getProductsByCategory("Books"))
                .thenReturn(List.of());

        List<Product> result = inventoryManager.getProductsByCategory("Books");

        assertTrue(result.isEmpty());
        verify(databaseConnector).getProductsByCategory("Books");
        verify(databaseConnector).close();
    }

    @Test
    void getProductsByCategoryUsesRequestedCategory() {
        when(databaseConnector.getProductsByCategory("Clothing"))
                .thenReturn(List.of());

        inventoryManager.getProductsByCategory("Clothing");

        verify(databaseConnector).getProductsByCategory("Clothing");
        verify(databaseConnector).close();
    }
}