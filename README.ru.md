# Cash Register Emulator

*[English](README.md) ∙ [Latviešu](README.lv.md) ∙ [Русский](README.ru.md)*

### Симулятор кассогого аппарата

![logo](readme_resources/restaurant_register.png)

## Цели
- [x] База данных (DB)
- [x] Пользовательский интерфейс (UI)
- [x] Редактируемый список продуктов

---

## Инструкция

### Вход в систему
Используем существующий аккаунт, например: **admin** (логин: `admin`, пароль: `admin`).

![Вход администратора](readme_resources/log_in.png)

### Приоритет Администратора
После входа под учетной записью администратора, вы получаете права администратора.

![Права администратора](readme_resources/admin_priority.png)

### Смена учетной записи
Вы можете сменить учетную запись, например на: **Alex** (логин: `Alex`, пароль: `root`) — эта учетная запись без прав администратора.

![Смена учетной записи](readme_resources/switch_account.png)

### Приоритет Пользователя
При входе под обычной учетной записью, вы имеете права пользователя.

![Права пользователя](readme_resources/user_priority.png)

### Создание нового аккаунта
Вы можете создать новый аккаунт. Каждый созданный аккаунт будет иметь права пользователя.

![Регистрация нового аккаунта](readme_resources/register_new_account.png)

---

### Работа с продуктами

#### Просмотр и управление продуктами
Вкладка "Продукты" позволяет вам просматривать существующие продукты, создавать новые и удалять их.

![Просмотр продуктов](readme_resources/product_view.png)

#### Удаление продукта
Вот как удалить выделенный продукт:

![Удаление продукта](readme_resources/delete_product.png)

#### Создание нового продукта
Следуйте подсказкам:
1. Перечислите ингредиенты через запятую. Например: курица, яблоко, яблоко, груша, курица (Курица: 2, Яблоко: 2, Груша: 1).
2. Обязательно сохраните изменения после заполнения информации о продукте!

![Создание продукта](readme_resources/product_creator.png)

---

### Работа с ингредиентами

#### Просмотр и управление ингредиентами
Во вкладке "Ингредиенты" можно просматривать список существующих ингредиентов, изменять их количество и удалять их.

![Просмотр ингредиентов](readme_resources/ingredient_view.png)

#### Создание нового ингредиента
1. Введите имя ингредиента.
2. Укажите количество.
3. Нажмите "Создать новый ингредиент" (необходимо заполнить поля 1 и 2).
4. Обновите изменения.

![Создание ингредиента](readme_resources/new_ingredient.png)

#### Удаление ингредиента
Вот как удалить ингредиент:

![Удаление ингредиента](readme_resources/delete_ingredient.png)

#### Изменение количества ингредиента
1. Введите новое количество.
2. Сохраните изменения.

![Изменение количества](readme_resources/update_selected_ingredient.png)

---

### Оформление заказа

После пополнения запасов и создания продуктов, можно приступить к оформлению заказа. Добавляем продукты в корзину (0 — не в корзине).

![Добавление в корзину](readme_resources/new_order_chooses.png)

### Оплата продуктов
В верхней части показан список продуктов. Выбирая продукт, можно посмотреть подробную информацию о нем.

![Оплата](readme_resources/new_order_pay_list_and_total_price.png)

### Скидочная карта
Вы можете применить скидочную карту на 15% от общей стоимости.

![Скидочная карта](readme_resources/new_order_discount_card.png)

### Процесс оплаты
1. Откройте эмулятор карты пользователя.
2. Скопируйте код.
3. Вставьте код в текстовое поле.
4. Нажмите кнопку "Confirm".

![Эмулятор карты и подтверждение](readme_resources/card_emulator_and_confirming.png)

5. Подтвердите перевод.

![Подтверждение перевода](readme_resources/confirm_transfer.png)

### Создание чека
После успешной оплаты создается новый чек. Чек сохраняется в папке проекта.

![Расположение чека](readme_resources/receipt_location.png)

---

## Авторы
- [AlexOsta](https://github.com/AlexUnderOS)