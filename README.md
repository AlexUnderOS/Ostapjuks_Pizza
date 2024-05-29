<<<<<<< HEAD
<<<<<<< HEAD
# Сash register for pizzerias
### A program that simulates a real cash register.

>> ## Goals
>> - [ ] database
>> - [ ] secure passwords
>> - [ ] completed UI
>> - [ ] added all products
>> - [ ] Depending on the product, the type for it is configured.
=======
# CASH REGISTER EMULATOR
### A program that simulates a real cash register.

![alt text](https://www.solteq.com/hs-fs/hubfs/solteqcom/Global/Product-images/Product-image-Commerce-Cloud-880x800-restaurant-cafe.png?width=880&height=800&name=Product-image-Commerce-Cloud-880x800-restaurant-cafe.png)

>> ## Goals
>> - [X] DB
>> - [X] UI
>> - [X] Editable Product List


>>>>>>> feat/db
=======
# Cash Register Emulator

*[English](README.md) ∙ [Latviešu](README.lv.md) ∙ [Русский](README.ru.md)*

### A Cash Register Simulator

![logo](readme_resources/restaurant_register.png)

## Goals
- [x] Database (DB)
- [x] User Interface (UI)
- [x] Editable Product List

---

## Instructions

### Logging In
Use an existing account, for example: **admin** (login: `admin`, password: `admin`).

![Admin login](readme_resources/log_in.png)

### Admin Privileges
When logged in with an administrator account, you will have admin privileges.

![Admin privileges](readme_resources/admin_priority.png)

### Switching Accounts
You can switch accounts, for example to: **Alex** (login: `Alex`, password: `root`) — this account does not have admin privileges.

![Switch account](readme_resources/switch_account.png)

### User Privileges
When logged in with a regular account, you will have user privileges.

![User privileges](readme_resources/user_priority.png)

### Creating a New Account
You can create a new account. Each created account will have user privileges.

![Register new account](readme_resources/register_new_account.png)

---

### Working with Products

#### Viewing and Managing Products
The "Products" tab allows you to view existing products, create new ones, and delete them.

![Product view](readme_resources/product_view.png)

#### Deleting a Product
Here is how to delete a selected product:

![Delete product](readme_resources/delete_product.png)

#### Creating a New Product
Follow the prompts:
1. List ingredients separated by commas. For example: chicken, apple, apple, pear, chicken (Chicken: 2, Apple: 2, Pear: 1).
2. Be sure to save changes after filling in the product information!

![Product creator](readme_resources/product_creator.png)

---

### Working with Ingredients

#### Viewing and Managing Ingredients
In the "Ingredients" tab, you can view the list of existing ingredients, change their quantities, and delete them.

![Ingredient view](readme_resources/ingredient_view.png)

#### Creating a New Ingredient
1. Enter the ingredient name.
2. Specify the quantity.
3. Click "Create New Ingredient" (fields 1 and 2 must be filled).
4. Save the changes.

![Create ingredient](readme_resources/new_ingredient.png)

#### Deleting an Ingredient
Here is how to delete an ingredient:

![Delete ingredient](readme_resources/delete_ingredient.png)

#### Changing Ingredient Quantity
1. Enter the new quantity.
2. Save the changes.

![Update ingredient quantity](readme_resources/update_selected_ingredient.png)

---

### Placing an Order

After restocking and creating products, you can proceed to place an order. Add products to the cart (0 - not in the cart).

![Add to cart](readme_resources/new_order_chooses.png)

### Paying for Products
The top section shows the list of products. By selecting a product, you can view detailed information about it.

![Pay for products](readme_resources/new_order_pay_list_and_total_price.png)

### Discount Card
You can apply a discount card for 15% off the total price.

![Discount card](readme_resources/new_order_discount_card.png)

### Payment Process
1. Open the user card emulator.
2. Copy the code.
3. Paste the code into the text field.
4. Click the "Confirm" button.

![Card emulator and confirming](readme_resources/card_emulator_and_confirming.png)

5. Confirm the transfer.

![Confirm transfer](readme_resources/confirm_transfer.png)

### Creating a Receipt
After successful payment, a new receipt is created. The receipt is saved in the project folder.

![Receipt location](readme_resources/receipt_location.png)

---

## Authors
- [AlexOsta](https://github.com/AlexUnderOS)
>>>>>>> feat/receipts
