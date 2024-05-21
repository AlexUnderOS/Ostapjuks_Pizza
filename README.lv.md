# Cash Register Emulator

*[English](README.md) ∙ [Latviešu](README.lv.md) ∙ [Русский](README.ru.md)*

### Kases aparāta emulators!

![logo](readme_resources/restaurant_register.png)

## Mērķi
- [x] Datu bāze (DB)
- [x] Lietotāja saskarne (UI)
- [x] Rediģējams produktu saraksts

---

## Instrukcijas

### Pieteikšanās sistēmā
Izmantojiet esošo kontu, piemēram: **(lietotājvārds: `admin`, parole: `admin`).

![Administrator Login](readme_resources/log_in.png)

### Administratora prioritāte
Pēc pieteikšanās ar administratora kontu jums tiek piešķirtas administratora tiesības.

![Administratora privilēģijas](readme_resources/admin_priority.png)

### Konta maiņa
Jūs varat mainīt savu kontu, piemēram, uz: **Alex** (lietotājvārds: `Alex`, parole: `root`) - šis konts ir bez administratora tiesībām.
![Mainīt kontu](readme_resources/switch_account.png)

### Lietotāja prioritāte
Ja esat pieteicies ar parasto kontu, jums ir lietotāja tiesības.

![Lietotāja tiesības](readme_resources/user_priority.png)

### Jaunu kontu izveide
Jūs varat izveidot jaunu kontu. Katram izveidotajam kontam būs lietotāja tiesības.

![Reģistrēt jaunu kontu](readme_resources/register_new_account.png)

---

### Produktu pārvaldība

#### Produktu skatīšana un pārvaldīšana
Cilne "Produkti" ļauj apskatīt esošos produktus, izveidot jaunus produktus un dzēst produktus.

![Produktu skats](readme_resources/product_view.png)

#### Produkta dzēšana
Šeit aprakstīts, kā dzēst izceltu produktu:

![Produkta dzēšana](readme_resources/delete_product.png)

#### Jauna produkta izveide
Izpildiet norādījumus:
1. Sastāvdaļas uzskaitiet, izmantojot komatus. Piemēram: vistas gaļa, ābols, ābols, bumbieris, vistas gaļa (vistas gaļa: 2, ābols: 2, bumbieris: 1).
2. Pēc produkta informācijas aizpildīšanas noteikti saglabājiet izmaiņas!

![Produkta izveide](readme_resources/product_creator.png)

---

### Sastāvdaļu pārvaldība

#### Sastāvdaļu skatīšana un pārvaldība
Cilnē "Ingredients" varat apskatīt esošo sastāvdaļu sarakstu, mainīt to daudzumu un dzēst.

![Sastāvdaļu skats](readme_resources/ingredient_view.png)

#### Jaunas sastāvdaļas izveide
1. Ievadiet sastāvdaļas nosaukumu.
2. Norādiet daudzumu.
3. Noklikšķiniet uz “Izveidot jaunu sastāvdaļu” (1. un 2. laukam jābūt aizpildītiem).
4. Atjauniniet izmaiņas.

![Sastāvdaļas izveide](readme_resources/new_ingredient.png)

#### Sastāvdaļas dzēšana
Šeit ir aprakstīts, kā izdzēst sastāvdaļu:

![Sastāvdaļas dzēšana](readme_resources/delete_ingredient.png)

#### Sastāvdaļas daudzuma maiņa
1. Ievadiet jaunu daudzumu.
2. Saglabājiet izmaiņas.

![Mainīt daudzumu](readme_resources/update_selected_ingredient.png)

---

### Izrakstīšanās

Kad esat atjaunojis krājumus un izveidojis produktus, varat doties pie kases. Pievienojiet produktus grozam (0 - nav grozā).

![Pievienot grozam](readme_resources/new_order_chooses.png)

### Produktu apmaksa
Produktu saraksts tiek parādīts augšpusē. Izvēloties produktu, varat skatīt detalizētu informāciju par produktu.

![Maksājums](readme_resources/new_order_pay_list_and_total_price.png)

### Atlaižu karte
Jūs varat izmantot atlaižu karti, lai saņemtu 15% atlaidi no kopējās cenas.

![Atlaižu karte](readme_resources/new_order_discount_card.png)

### Maksājuma process
1. Atveriet lietotāja kartes emulatoru.
2. Nokopējiet kodu.
3. Ievietojiet kodu teksta lodziņā.
4. Noklikšķiniet uz pogas “Apstiprināt”.

![Kartes emulators un apstiprināšana](readme_resources/card_emulator_and_confirming.png)

5. Apstipriniet tulkojumu.

![Apstiprināt pārskaitījumu](readme_resources/confirm_transfer.png)

### Čeku izveide
Pēc veiksmīga maksājuma tiek izveidots jauns čeks. Čeks tiek saglabāts projekta mapē.

![Čeka atrašanās vieta](readme_resources/receipt_location.png)

---

## Autori
- [AlexOsta](https://github.com/AlexUnderOS)