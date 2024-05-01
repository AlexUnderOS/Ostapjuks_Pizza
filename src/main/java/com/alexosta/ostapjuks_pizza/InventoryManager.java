package com.alexosta.ostapjuks_pizza;

import java.util.List;

public interface InventoryManager {
    // Методы для работы с продуктами
    Product getProductById(int id);
    List<Product> getAllProducts();
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int id);
    void updateProductAmount(int amountInStock);

    // Методы для работы с категориями
    Category getCategoryById(int id);
    List<Category> getAllCategories();
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int id);

    // Методы для работы с платежами
    PaymentMethod getPaymentMethodById(int id);
    List<PaymentMethod> getAllPaymentMethods();
    void addPaymentMethod(PaymentMethod paymentMethod);
    void updatePaymentMethod(PaymentMethod paymentMethod);
    void deletePaymentMethod(int id);
}

